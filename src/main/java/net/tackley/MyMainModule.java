package net.tackley;

import com.google.inject.AbstractModule;
import net.tackley.weretwit.moon.CalendarBasedMoon;
import net.tackley.weretwit.moon.Moon;
import net.tackley.weretwit.moon.WebBasedMoon;

public class MyMainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Moon.class).to(WebBasedMoon.class);
    }
}
