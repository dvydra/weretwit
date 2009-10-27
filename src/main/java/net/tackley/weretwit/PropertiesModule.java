package net.tackley.weretwit;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesModule extends AbstractModule {
    protected void configure() {
        try {
            Properties twitterProperties = loadProperties("net/tackley/weretwit/utils/twitter.properties");
            Names.bindProperties(binder(), twitterProperties);
        } catch (RuntimeException e) {
            addError("Could not configure database properties", e);
        }
    }

    private static Properties loadProperties(String name) {
        Properties properties = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("twitter.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException dontCare) {
                }
            }
        }
        return properties;
    }
}
