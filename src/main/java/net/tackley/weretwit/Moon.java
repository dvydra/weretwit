package net.tackley.weretwit;

import java.util.Calendar;
import java.util.Date;

public interface Moon {
    public int getPhaseOfMoon(Calendar cal);

    public int getPhaseOfMoon(Date date);
}
