package org.puppetory.connector.config;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 03.03.15
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class ConfigManager {

    Properties properties;

    public ConfigManager() throws IOException {
        properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("puppetory.properties"));
    }

    public String getConfig(String key){
        return properties.getProperty(key);
    }
    public String getConfig(KEYS key){
        return properties.getProperty(key.toString());
    }

    public Set<String> getConfigKeys(){

        Set<String> keys = new HashSet<String>();
        Enumeration<?> propertyNames = properties.propertyNames();

        while(propertyNames.hasMoreElements()){
            keys.add(propertyNames.nextElement().toString());
        }
        return keys;
    }

    public static enum KEYS{
        PUPPET_SERVER_URL("puppet.server.url"),
        PUPPET_API_VERSION("puppet.server.version");

        private String key;

        private KEYS(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return key;
        }
    }
}
