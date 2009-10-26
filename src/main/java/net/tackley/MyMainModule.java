package net.tackley;

import com.google.inject.AbstractModule;
import net.tackley.weretwit.CalendarBasedMoon;
import net.tackley.weretwit.Moon;

public class MyMainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Moon.class).to(CalendarBasedMoon.class);
    }
}
