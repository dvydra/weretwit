package net.tackley.weretwit.controller;

import com.google.inject.*;
import net.tackley.MyMainModule;
import net.tackley.weretwit.moon.Moon;
import net.tackley.weretwit.utils.PropertiesModule;
import net.tackley.weretwit.utils.PropertiesProvider;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Singleton
public class MainController extends HttpServlet {
    private Moon moon;
    private PropertiesProvider propertiesProvider;

    @Inject
    public MainController() {
        Injector i = Guice.createInjector(Stage.DEVELOPMENT, new MyMainModule(), new PropertiesModule());
        this.moon = i.getInstance(Moon.class);
        this.propertiesProvider = i.getInstance(PropertiesProvider.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Twitter twit = new Twitter(propertiesProvider.getUsername(), propertiesProvider.getPassword());
        try {
            List<Status> timeline = twit.getUserTimeline("dvydra");
            writer.printf("<html><body><ul>");
            for (Status status : timeline) {
                String text = status.getText();
                Date createdAt = status.getCreatedAt();
                double phaseOfMoon = moon.getPhaseOfMoon(createdAt);
                String phaseinHex = Integer.toHexString((int) (255 * phaseOfMoon));
                writer.printf("<li style='background-color:#%s0000'>\"%s\" tweeted at %s</li>\n", phaseinHex, text, createdAt);
            }
            writer.printf("</ul></body></html>");

        } catch (TwitterException e) {
            throw new ServletException(e);
        }


    }

}
