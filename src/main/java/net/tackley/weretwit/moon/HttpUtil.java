package net.tackley.weretwit.moon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    public HttpUtil() {
    }

    public String getPage(String homeURL) {
        StringBuffer content = new StringBuffer();
        try {
            // Construct data

            // Send data
            URL url = new URL(homeURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                content.append(line);
            }
            conn.disconnect();
        } catch (Exception e) {

        }
        return content.toString();
    }
}
