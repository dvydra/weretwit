package net.tackley.weretwit.moon;


import com.google.inject.Inject;
import net.tackley.weretwit.utils.HttpUtil;
import twitter4j.org.json.JSONException;
import twitter4j.org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WebBasedMoon implements Moon {
    private HttpUtil httpUtil;


    @Inject
    public WebBasedMoon(HttpUtil httpUtil) {
        this.httpUtil = new HttpUtil();
    }

    public double getPhaseOfMoon(Date date) {

        String dateString = parseStringFrom(date);
        String s = httpUtil.getPage(String.format("http://www.trynt.com/moon-phase-api/v1/?d=%s&fo=json&f=0", dateString));
        double moonPhase = 0;
        try {
            moonPhase = new JSONObject(s).getJSONObject("trynt").getJSONObject("moon-phase").getDouble("moon-phase-position");
        }
        catch (JSONException
                e) {
            e.printStackTrace();
        }
        return moonPhase;
    }

    private String parseStringFrom(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(date);

    }
}
