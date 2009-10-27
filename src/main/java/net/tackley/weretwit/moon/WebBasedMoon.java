package net.tackley.weretwit.moon;


import com.google.inject.Inject;
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

    public int getPhaseOfMoon(Date date) {

        String dateString = parseStringFrom(date);
        String s = httpUtil.getPage(String.format("http://www.trynt.com/moon-phase-api/v1/?d=%s&fo=json&f=0", dateString));
        JSONObject o;
        try {
            o = new JSONObject(s);
            o.get("trynt.moon-phase.moon-phase-position");
            catch(JSONException
            e){
                //
            }
            return 0;
        }

    private String parseStringFrom(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(date);

    }
}
