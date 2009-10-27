package net.tackley.weretwit.utils;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesProvider {

    @Inject
    @Named("twitter.username")
    private String username;
    @Inject
    @Named("twitter.password")
    private String password;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Properties loadProperties(String name) {
        Properties properties = new Properties();
        InputStream is = this.getClass().getResourceAsStream(name);
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
