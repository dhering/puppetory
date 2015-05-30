package org.puppetory.factories;

import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigFactoryTest {

    public ConfigFactory factory;

    @Before
    public void setUp(){
        factory = new ConfigFactory();
    }

    @Ignore
    @Test
    public void testFactory() throws Exception {
        Configuration configuration = factory.getObject();

        assertEquals("localhost", configuration.getString("db.host"));
    }

    @Test
    public void testGetObjectType() throws Exception {
        assertEquals(Configuration.class, factory.getObjectType());
    }

    @Test
    public void testSsSingleton() throws Exception {
        assertEquals(true, factory.isSingleton());
    }
}
