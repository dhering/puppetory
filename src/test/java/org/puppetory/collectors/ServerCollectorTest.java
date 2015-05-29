package org.puppetory.collectors;

import com.mongodb.client.MongoDatabase;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.bson.Document;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.puppetory.collectors.impl.ServerCollector;
import org.puppetory.data.api.DbModelMapper;
import org.puppetory.data.api.Repository;
import org.puppetory.data.impl.MongoRepository;
import org.puppetory.data.impl.PuppetMongoMapper;
import org.puppetory.dummies.FactDummies;
import org.puppetory.dummies.RepositoryDummies;
import org.puppetory.factories.ConfigFactory;
import org.puppetory.factories.DbFactory;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Fact;
import org.puppetory.model.api.Filter;
import org.puppetory.model.api.Inventory;
import org.puppetory.model.impl.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 26.05.15
 * Time: 21:31
 * To change this template use File | Settings | File Templates.
 */
public class ServerCollectorTest {

    private Component referenceComponente;
    private final Document referenceDocument = Document.parse("{" +
            "name: 'dummyFact', " +
            "foo: 'bar', " +
            "subfacts: {name: 'dummyFact', foo: 'bar'}," +
            "processorcount: 2," +
            "processor0: 'cpu-1'," +
            "processor1: 'cpu-2'," +
            "software_count: 2," +
            "software0: 'program-1;1.0'," +
            "software1: 'program-2;2.0'," +
            "system_uptime: '{seconds=>5428, hours=>1, days=>0, uptime=>\\'1:30 hours\\'}'" +
        "}");




    private Inventory inventory;
    private MongoDatabase database;
    private Configuration configuration;
    private DbModelMapper dbModelMapper;

    private ArgumentCaptor<String> argumentCollectionName;
    private ArgumentCaptor<Component> argumentComponent;

    private ServerCollector collector;

    @Before
    public void setUp() throws Exception{

        this.argumentCollectionName = ArgumentCaptor.forClass(String.class);
        this.argumentComponent = ArgumentCaptor.forClass(Component.class);

        this.inventory = mock(Inventory.class);

        this.database = RepositoryDummies.mockupMongoDatabaser(referenceDocument);

        Map properties = new HashMap();
        properties.put("server.collector.source.collection","server");
        properties.put("server.collector.target.collection","server_unit_test");
        this.configuration = new MapConfiguration(properties);

        this.dbModelMapper = new PuppetMongoMapper();


        List<Fact> facts = new LinkedList<Fact>();
        facts.addAll(FactDummies.getStructuredFacts());
        facts.add(new MultipleValueFact("processor", new String[]{"cpu-1","cpu-2"}));

        List<Fact> softwareFacts = new LinkedList<Fact>();
        softwareFacts.add(new PersistentTextualFact("program-1","1.0"));
        softwareFacts.add(new PersistentTextualFact("program-2","2.0"));
        facts.add(new ListedFact("software",softwareFacts));

        List<Fact> uptimeFacts = new LinkedList<Fact>();
        uptimeFacts.add(new TextualFact("seconds","5428"));
        uptimeFacts.add(new TextualFact("hours","1"));
        uptimeFacts.add(new TextualFact("days","0"));
        uptimeFacts.add(new TextualFact("uptime","1:30 hours"));
        facts.add(new StructuredFact("system_uptime", uptimeFacts));

        this.referenceComponente = new ComponentImpl(facts);

        collector = new ServerCollector();
        collector.setInventory(inventory);
        collector.setDatabase(database);
        collector.setConfiguration(configuration);
        collector.setDbModelMapper(dbModelMapper);
        collector.afterPropertiesSet();
    }

    @Test
    public void testCollect(){
        collector.collect();

        verify(inventory).upsert(argumentCollectionName.capture(), argumentComponent.capture(), any(Filter.class));
        String collectionName = argumentCollectionName.getValue();
        Component component = argumentComponent.getValue();

        assertEquals("server_unit_test", collectionName);
        assertEquals(referenceComponente, component);
    }

    @Ignore
    @Test
    public void integrationTest() throws Exception {

        Configuration config = new ConfigFactory().getObject();
        DbFactory dbFactory = new DbFactory();
        dbFactory.setConfig(config);

        MongoDatabase database = dbFactory.getObject();
        PuppetMongoMapper mapper = new PuppetMongoMapper();

        Repository repository = new MongoRepository(database, mapper);
        Inventory inventory = new InventoryImpl(repository);

        ServerCollector collector = new ServerCollector();
        collector.setInventory(inventory);
        collector.setDatabase(database);
        collector.setConfiguration(configuration);
        collector.setDbModelMapper(dbModelMapper);
        collector.afterPropertiesSet();

        collector.collect();
    }
}
