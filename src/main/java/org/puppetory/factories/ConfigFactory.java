package org.puppetory.factories;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;

@Service("configuration")
public class ConfigFactory implements FactoryBean<Configuration>, ResourceLoaderAware {

    ResourceLoader resourceLoader;

    String filename;
    String[] searchPaths;

    public ConfigFactory() {
        filename = "puppetory.properties";
        searchPaths = new String[] {"/etc/", "config/"};
    }

    public File getPropertyFile() throws FileNotFoundException {

        //Resource resource = resourceLoader.getResource("");

        for(String path : searchPaths){
            File propertyFile = new File(path + filename);
            if(propertyFile.exists() && propertyFile.isFile()){
                return propertyFile;
            }


        }

        throw new FileNotFoundException("Property file not found");
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
