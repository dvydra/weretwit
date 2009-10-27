package net.tackley.weretwit.moon;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import static org.mockito.MockitoAnnotations.Mock;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class WebBasedMoonTest {
    @Mock
    private HttpUtil httpUtil;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldReturnFullMoonFor_10_16_1976() {

        Moon moon = new WebBasedMoon(httpUtil);
        GregorianCalendar gregorianCalendar = new GregorianCalendar(Calendar.getInstance().getTimeZone());
        gregorianCalendar.set(1976, 9, 16);

        int phaseOfMoon = moon.getPhaseOfMoon(gregorianCalendar.getTime());
        assertEquals(phaseOfMoon, 7);
    }
}
