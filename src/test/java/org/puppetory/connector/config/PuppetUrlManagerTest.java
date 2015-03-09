package org.puppetory.connector.config;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 03.03.15
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */
public class PuppetUrlManagerTest {

    @Test
    public void testQueryUrl() throws IOException {
        PuppetUrlManager puppetUrlManager = new PuppetUrlManager(new ConfigManager());
        String queryUrl = puppetUrlManager.getQueryBaseUrl();

        assertEquals(queryUrl, ConfigTestConstants.PUPPET_QUERY_URL);
    }
}
