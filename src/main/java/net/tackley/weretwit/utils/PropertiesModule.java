package net.tackley.weretwit.utils;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.util.Properties;

public class PropertiesModule extends AbstractModule {
    protected void configure() {
        PropertiesProvider propertiesProvider = new PropertiesProvider();
        try {
            Properties twitterProperties = propertiesProvider.loadProperties("/net/tackley/weretwit/utils/twitter.properties");
            bindConstant().annotatedWith(Names.named("twitter.username")).to(twitterProperties.getProperty("twitter.username"));
            bindConstant().annotatedWith(Names.named("twitter.password")).to(twitterProperties.getProperty("twitter.password"));
        } catch (RuntimeException e) {
            addError("Could not configure twitter properties", e);
        }
    }


}
