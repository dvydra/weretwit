package net.tackley.weretwit.utils;

import com.google.inject.name.Named;
import com.google.inject.name.Names;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;


public class PropertiesModuleTest {

    @Test
    public void shouldLoadPropertiesFileFromClasspath() {
        PropertiesModule propertiesModule = new PropertiesModule();
        propertiesModule.configure();

        Named named = Names.named("twitter.username");
        assertEquals(named.value(), "dvydra");
    }
}
