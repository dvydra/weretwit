package net.tackley.weretwit.controller;

import com.google.inject.*;
import net.tackley.MyMainModule;
import net.tackley.weretwit.Moon;
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

    @Inject
    public MainController() {
        Injector i = Guice.createInjector(Stage.DEVELOPMENT, new MyMainModule());
        this.moon = i.getInstance(Moon.class);
        System.out.println("Constructed!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        Twitter twit = new Twitter();
        try {
            List<Status> timeline = twit.getUserTimeline("tackers");

            for (Status status : timeline) {
                String text = status.getText();
                Date createdAt = status.getCreatedAt();
                int phaseOfMoon = moon.getPhaseOfMoon(createdAt);
                writer.printf("\"%s\" tweeted at %s (phase %d)\n", text, createdAt, phaseOfMoon);
            }

        } catch (TwitterException e) {
            throw new ServletException(e);
        }


    }

}
