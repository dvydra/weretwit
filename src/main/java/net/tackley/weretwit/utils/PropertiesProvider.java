package net.tackley.weretwit.utils;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PropertiesProvider {

    @Inject @Named("twitter.username") private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(@Named("twitter.password") String password) {
        this.password = password;
    }



    public String getUsername() {
            return username;
    }


}
