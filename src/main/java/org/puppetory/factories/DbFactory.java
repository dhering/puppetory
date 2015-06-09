package org.puppetory.factories;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * This factory creates a singleton of a {@link MongoDatabase} by reading the configuration file.
 *
 * Set a prefix to store multiple database connections in one configuration file.
 */
public class DbFactory implements FactoryBean<MongoDatabase> {

    private Configuration config;
    private String propertyPrefix;

    public DbFactory() {
        this.config = null;
        this.propertyPrefix = "";
    }

    public Configuration getConfig() {
        return config;
    }

    @Autowired
    public void setConfig(Configuration config) {
        this.config = config;
    }

    public String getPropertyPrefix() {
        return propertyPrefix;
    }

    @Autowired
    public void setPropertyPrefix(String propertyPrefix) {
        this.propertyPrefix = propertyPrefix;
    }

    @Override
    public MongoDatabase getObject() throws Exception {

        String host = config.getString(propertyPrefix + "db.host", ServerAddress.defaultHost());
        int    port = config.getInt(propertyPrefix + "db.port", ServerAddress.defaultPort());
        String database = config.getString(propertyPrefix + "db.database", "puppetory");
        String user = config.getString(propertyPrefix + "db.user");
        String password = config.getString(propertyPrefix + "db.password");

        ServerAddress serverAddress = new ServerAddress(host, port);

        MongoCredential credentials = getCredentials(user, password, database);
        MongoClient mongoClient;

        if(credentials == null){
            mongoClient = new MongoClient(serverAddress);
        } else {
            mongoClient = new MongoClient(serverAddress, Arrays.asList(credentials));
        }

        return mongoClient.getDatabase(database);
    }

    /**
     * create credentials if possible
     *
     * @param user
     * @param password
     * @param database
     * @return credentials or null
     */
    private MongoCredential getCredentials(String user, String password, String database){

        if(user != null && user.isEmpty() == false
                && password != null && password.isEmpty() == false){

            return MongoCredential.createCredential(user , database, password.toCharArray());
        }

        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return MongoDatabase.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
