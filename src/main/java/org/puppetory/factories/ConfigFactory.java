package org.puppetory.factories;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service("configuration")
public class ConfigFactory implements FactoryBean<Configuration>, ResourceLoaderAware {

    ResourceLoader resourceLoader;
    Log logger;

            String filename;
    String[] searchPaths;

    public ConfigFactory() {
        filename = "puppetory.properties";
        searchPaths = new String[] {"file:/etc/", "file:config/"};

        logger = LogFactory.getLog(ConfigFactory.class);
    }

    public File getPropertyFile() throws FileNotFoundException {

        for(String path : searchPaths){
            Resource resource = resourceLoader.getResource(path + filename);
            if(resource.exists()){
                try {
                    File propertyFile = resource.getFile();
                    if(propertyFile.exists() && propertyFile.isFile()){
                        return propertyFile;
                    }
                } catch (IOException e) {
                    logger.debug("Unable to access properties file: '" + path + filename + "'");
                }
            }
        }

        String paths = "";
        for(String path : searchPaths){
            paths += path.isEmpty() ? path + filename : ',' + path + filename;
        }

        throw new FileNotFoundException("Property file not found. Please store any property" +
                " file at one of the following paths: [" + paths + "]");
    }

    @Override
    public Configuration getObject() throws Exception {
        return new PropertiesConfiguration(getPropertyFile());
    }

    @Override
    public Class<?> getObjectType() {
        return Configuration.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
