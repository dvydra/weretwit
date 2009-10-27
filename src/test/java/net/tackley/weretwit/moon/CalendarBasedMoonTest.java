package net.tackley.weretwit.moon;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.GregorianCalendar;

public class CalendarBasedMoonTest {

    @Test
    public void shouldReturnFullMoonFor_10_16_1976() {
        Moon moon = new CalendarBasedMoon();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(1976, 10, 16);

        double phaseOfMoon = moon.getPhaseOfMoon(gregorianCalendar.getTime());
        assertEquals(phaseOfMoon, 7, 0);
    }
}
