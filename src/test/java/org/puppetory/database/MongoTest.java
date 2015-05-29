package org.puppetory.database;

import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.util.JSON;
import org.apache.commons.configuration.Configuration;
import org.bson.BsonArray;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.puppetory.factories.ConfigFactory;
import org.puppetory.factories.DbFactory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MongoTest {

    MongoDatabase database;

    @Before
    public void setup() throws Exception{
        Configuration config = (new ConfigFactory()).getObject();

        DbFactory dbFactory = new DbFactory();
        dbFactory.setConfig(config);

        this.database = dbFactory.getObject();
    }

    @Ignore
    @Test
    public void databaseConnectionTest() {

        MongoCollection<Document> server = database.getCollection("server");
        MongoCollection<Document> serverGerneralized = database.getCollection("server_generalized");

        FindIterable<Document> documents = server.find();

        for(Document document : documents){
            Document generalizedDocument = new Document();

            Set<String> keyset = document.keySet();
            Set<String> generalizedFacts = new HashSet<String>();

            groupMultiValues(document, generalizedDocument, generalizedFacts);
            groupSingeValues(document, generalizedDocument, generalizedFacts);
            findHiddenJson(document, generalizedDocument, generalizedFacts);

            for(String key : keyset){
                if(key.equals("_id")) {
                    continue;
                }
                if(generalizedFacts.contains(key) == false){
                    generalizedDocument.put(key, document.get(key));
                }
            }

            Document filter = new Document();
            filter.put("serialnumber", document.getString("serialnumber"));

            serverGerneralized.findOneAndUpdate(filter, new Document("$set", generalizedDocument), new FindOneAndUpdateOptions().upsert(true).maxTime(5, TimeUnit.MINUTES));

            //upsert("server_generalized", generalizedDocument);
        }
    }

    private void upsert(String collectionName, Document document){

        MongoCollection<Document> collection = database.getCollection(collectionName);

        Document filter = new Document();
        filter.put("serialnumber", document.getString("serialnumber"));

        FindIterable<Document> documents = collection.find(filter);

        MongoCursor<Document> iter = documents.iterator();

        if(iter.hasNext()){
            while(iter.hasNext()){
                Document existingDocument = iter.next();

                ObjectId id = existingDocument.getObjectId("_id");

                document.put("_id", id);

                Document insertFilter = new Document();
                filter.put("_id", id);

                collection.findOneAndUpdate(insertFilter, document);
            }
        } else {
            collection.insertOne(document);
        }
    }

    private void groupMultiValues(Document document, Document generalizedDocument, Set<String> generalizedFacts){
        Set<String> keyset = document.keySet();
        Set<String> groupableFacts = findGroupableFacts(keyset, "_count", generalizedFacts);

        for(String fact : groupableFacts){

            List<Document> structuredFacts = new LinkedList<Document>();

            for(String key : keyset){
                if(key.matches(fact + "\\d+")){
                    String value = document.getString(key);
                    int splitIndex = value.indexOf(';');
                    if(splitIndex > 0){
                        String factKey = value.substring(0, splitIndex);
                        String factValue = value.substring(splitIndex + 1);

                        Document structuredFact = new Document();
                        structuredFact.put("name", factKey);
                        structuredFact.put("value", factValue);

                        structuredFacts.add(structuredFact);
                        generalizedFacts.add(key);
                    }
                }
            }

            generalizedDocument.put(fact, structuredFacts);
            generalizedFacts.add(fact);
        }
    }

    private void groupSingeValues(Document document, Document generalizedDocument, Set<String> generalizedFacts){
        Set<String> keyset = document.keySet();
        Set<String> groupableFacts = findGroupableFacts(keyset, "count", generalizedFacts);

        for(String fact : groupableFacts){

            BsonArray factList = new BsonArray();

            for(String key : keyset){
                if(key.matches(fact + "\\d+")){
                    String value = document.getString(key);
                    factList.add(new BsonString(value));
                    generalizedFacts.add(key);
                }
            }

            generalizedDocument.put(fact, factList);
        }
    }

    private void findHiddenJson(Document document, Document generalizedDocument, Set<String> generalizedFacts){
        Set<String> hiddenJson = new HashSet<>();
        hiddenJson.add("partitions");
        hiddenJson.add("processors");
        hiddenJson.add("os");
        hiddenJson.add("system_uptime");
        
        for(String fact : hiddenJson){
            String value = document.getString(fact);
            
            String jsonValue = value.replaceAll("=>", ":");

            DBObject jsonObject = (DBObject) JSON.parse(jsonValue);

            generalizedDocument.put(fact, jsonObject);
            generalizedFacts.add(fact);
        }

    }

    private Set<String> findGroupableFacts(Set<String> allkeys, String endsWith, Set<String> generalizedFacts){

        Set<String> factkeys = new HashSet<String>();

        for(String key : allkeys){
            if(key.endsWith(endsWith)){
                String suffix = key.substring(0, key.length() - endsWith.length());
                if(generalizedFacts.contains(key) == false){
                    factkeys.add(suffix);
                    generalizedFacts.add(key);
                }
            }
        }


        return factkeys;
    }
}
