package org.puppetory.connector.query;

import org.junit.Before;
import org.junit.Test;
import org.puppetory.connector.config.ConfigManager;
import org.puppetory.connector.config.ConfigTestConstants;
import org.puppetory.connector.config.PuppetUrlManager;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SimpleQueryTest {

    private final static String QUERY_LINUX_NODE = "node/linux";
    private PuppetUrlManager puppetUrlManager;

    @Before
    public void setup() throws IOException {
        puppetUrlManager = new PuppetUrlManager(new ConfigManager());
    }

    @Test
    public void testQuery() {
        SimpleQuery simpleQuery = new SimpleQuery(QUERY_LINUX_NODE, puppetUrlManager);

        assertEquals(simpleQuery.getQuery(), ConfigTestConstants.PUPPET_QUERY_URL + QUERY_LINUX_NODE);
    }
}
