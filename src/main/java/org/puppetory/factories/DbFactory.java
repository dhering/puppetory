package org.puppetory.factories;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

//@Profile("production")
@Service("database")
public class DbFactory implements FactoryBean<MongoDatabase> {

    Configuration config;

    @Autowired
    public DbFactory(Configuration Configuration) {
        this.config = Configuration;
    }

    @Override
    public MongoDatabase getObject() throws Exception {

        String host = config.getString("db.host", ServerAddress.defaultHost());
        int    port = config.getInt("db.port", ServerAddress.defaultPort());
        String database = config.getString("db.database", "puppetory");
        String user = config.getString("db.user");
        String password = config.getString("db.password");

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
     * @return
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
