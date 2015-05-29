package org.puppetory.collectors.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.configuration.Configuration;
import org.bson.Document;
import org.puppetory.collectors.api.Collector;
import org.puppetory.data.api.DbModelMapper;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Fact;
import org.puppetory.model.api.Inventory;
import org.puppetory.model.impl.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 26.05.15
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class ServerCollector implements Collector, InitializingBean{

    private Inventory inventory;
    private MongoDatabase database;
    private Configuration configuration;
    private DbModelMapper dbModelMapper;

    private String uniqueQualifier = "uuid";
    private String sourceCollectionName;
    private String targetCollectionName;

    String[] hiddenJson = new String[] {"partitions","processors","os","system_uptime"};

    public ServerCollector() {}

    @Autowired
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Autowired
    @Qualifier("serverCollectorDatabase")
    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }

    @Autowired
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Autowired
    public void setDbModelMapper(DbModelMapper dbModelMapper) {
        this.dbModelMapper = dbModelMapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.sourceCollectionName = configuration.getString("server.collector.source.collection", "server");
        this.targetCollectionName = configuration.getString("server.collector.target.collection", "server_generalized");
    }

    public String getUniqueQualifier() {
        return uniqueQualifier;
    }

    public void setUniqueQualifier(String uniqueQualifier) {
        this.uniqueQualifier = uniqueQualifier;
    }

    @Override
    public void collect() {
        {
            System.out.println("# collecting servers #");

            MongoCollection<Document> server = database.getCollection(sourceCollectionName);

            FindIterable<Document> documents = server.find();

            for(Document document : documents){
                Component component = new ComponentImpl();

                Set<String> factNames = document.keySet();
                Set<String> generalizedFacts = new HashSet<String>();

                groupMultiValues(document, component, generalizedFacts);
                groupSingeValues(document, component, generalizedFacts);
                findHiddenJson(document, component, generalizedFacts);

                for(String factName : factNames){
                    if(factName.equals("_id")) {
                        continue;
                    }
                    if(generalizedFacts.contains(factName) == false){
                        Object factValue = document.get(factName);
                        if(factValue != null){
                            Fact fact = dbModelMapper.getFactFromDocument(document, factName);
                            if(fact != null){
                                component.addFact(fact);
                                generalizedFacts.add(fact.getName());
                            }
                        }
                    }
                }

                Document docFilter = new Document();
                docFilter.put(uniqueQualifier, document.getString(uniqueQualifier));

                FilterImpl filter = new FilterImpl();
                filter.setQuery(docFilter.toJson());

                inventory.upsert(targetCollectionName, component, filter);
            }
        }
    }

    private void groupMultiValues(Document document, Component component, Set<String> generalizedFacts){
        Set<String> keyset = document.keySet();
        Set<String> groupableFacts = findGroupableFacts(keyset, "_count", generalizedFacts);

        for(String factName : groupableFacts){

            List<Fact> structuredFacts = new LinkedList<Fact>();


            for(String key : keyset){
                if(key.matches(factName + "\\d+")){
                    String value = document.getString(key);
                    int splitIndex = value.indexOf(';');
                    if(splitIndex > 0){
                        String factKey = value.substring(0, splitIndex);
                        String factValue = value.substring(splitIndex + 1);

                        structuredFacts.add(new PersistentTextualFact(factKey, factValue));
                        generalizedFacts.add(key);
                    }
                }
            }

            component.addFact(new ListedFact(factName, structuredFacts));
            generalizedFacts.add(factName);
        }
    }

    /**
     * Some facts have multiple values but only one name, so this method is converting the {@link Document}
     * into a {@link MultipleValueFact}.
     *
     * All facts are added to the given component.
     *
     * @param document
     * @param component
     * @param generalizedFacts
     */
    private void groupSingeValues(Document document, Component component, Set<String> generalizedFacts){
        Set<String> keyset = document.keySet();
        Set<String> groupableFacts = findGroupableFacts(keyset, "count", generalizedFacts);

        for(String factName : groupableFacts){

            List<String> factList = new LinkedList<String>();

            for(String key : keyset){
                if(key.matches(factName + "\\d+")){
                    String value = document.getString(key);
                    factList.add(value);
                    generalizedFacts.add(key);
                }
            }

            component.addFact(new MultipleValueFact(factName, factList.toArray(new String[factList.size()])));
        }
    }

    /**
     * Some facts are of the type {@link String} but contains JSON data as value. This method is parsing
     * this value and tries to extract a {@link Document} to convert it to structured facts.
     *
     * All facts are added to the given component.
     *
     * @param document
     * @param component
     * @param generalizedFacts
     */
    private void findHiddenJson(Document document, Component component, Set<String> generalizedFacts){

        for(String factName : hiddenJson){
            String value = document.getString(factName);

            if (value != null && value.isEmpty() == false) {
                String jsonValue = value.replaceAll("=>", ":");

                Document strukturedFact = Document.parse(jsonValue);

                List<Fact> facts = dbModelMapper.getFactsFromDocument(strukturedFact);
                component.addFact(new StructuredFact(factName, facts));
                generalizedFacts.add(factName);
            }
        }

    }

    /**
     * Returns a subset of fact names which are ending with the given prefix.
     * All exiting names in the set of generalized facts will be ignored.
     *
     * @param allFactNames
     * @param endsWith
     * @param generalizedFacts
     * @return
     */
    private Set<String> findGroupableFacts(Set<String> allFactNames, String endsWith, Set<String> generalizedFacts){

        Set<String> factkeys = new HashSet<String>();

        for(String factName : allFactNames){
            if(factName.endsWith(endsWith)){
                String suffix = factName.substring(0, factName.length() - endsWith.length());

                // check if the key is already used
                // if not, add the key to the result list
                if(generalizedFacts.contains(factName) == false){
                    factkeys.add(suffix);
                    generalizedFacts.add(factName);
                }
            }
        }


        return factkeys;
    }
}
