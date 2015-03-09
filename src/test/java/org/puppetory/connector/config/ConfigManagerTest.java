package org.puppetory.connector.config;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 03.03.15
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
public class ConfigManagerTest {

    private ConfigManager configManager;

    @Before
    public void setup() throws IOException {
        configManager = new ConfigManager();
    }

    @Test
    public void testGetConfigWithKey() throws IOException {
        String serverUrl = configManager.getConfig(ConfigManager.KEYS.PUPPET_SERVER_URL);
        assertEquals(serverUrl, ConfigTestConstants.PUPPET_SERVER_URL);
    }

    @Test
    public void testGetConfigWithString() throws IOException {
        String serverUrl = configManager.getConfig(ConfigManager.KEYS.PUPPET_SERVER_URL.toString());
        assertEquals(serverUrl, ConfigTestConstants.PUPPET_SERVER_URL);
    }

    @Test
    @Ignore
    public void readProperties(){
        Set<String> configKeys = configManager.getConfigKeys();

        assertTrue(configKeys.contains(ConfigManager.KEYS.PUPPET_SERVER_URL.toString()));

        for (String key : configKeys){
            if(key.startsWith("navigation")){
                continue;
            }

            configKeys.remove(key);
        }

        for (String key : configKeys){
            if(key.matches("navigation.\\d+.topic")){
                System.out.println(key);
            }
        }
    }
}
