package net.tackley.weretwit.controller;

import com.google.inject.*;
import net.tackley.MyMainModule;
import net.tackley.weretwit.moon.Moon;
import net.tackley.weretwit.tweet.TweetVO;
import net.tackley.weretwit.utils.PropertiesModule;
import net.tackley.weretwit.utils.PropertiesProvider;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class MainController extends VelocityViewServlet {
    private Moon moon;
    private PropertiesProvider propertiesProvider;
    private Twitter twit;

    @Inject
    public MainController() {
        Injector i = Guice.createInjector(Stage.DEVELOPMENT, new MyMainModule(), new PropertiesModule());
        this.moon = i.getInstance(Moon.class);
        this.propertiesProvider = i.getInstance(PropertiesProvider.class);
        this.twit = new Twitter(propertiesProvider.getUsername(), propertiesProvider.getPassword());
    }

    @Override
    protected Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) throws java.lang.Exception {
        Template tpl = super.getTemplate("test.vm");

        String[] pathBits = request.getRequestURI().split("/");
        String username = pathBits[pathBits.length - 1];
        ArrayList tweets = new ArrayList();

        try {
            List<Status> timeline = twit.getUserTimeline(username);
            for (Status status : timeline) {
                String text = status.getText();
                Date createdAt = status.getCreatedAt();
                double phaseOfMoon = moon.getPhaseOfMoon(createdAt);
                String phaseInHex = Integer.toHexString((int) (255 * phaseOfMoon));
                tweets.add(new TweetVO(text, createdAt, phaseInHex));
            }

        } catch (TwitterException e) {
            throw new ServletException(e);
        }


        context.put("tweets", tweets);
        StringWriter writer = new StringWriter();
        tpl.merge(context, writer);
        return tpl;
    }

    //@Override
    public void doGetz(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

}
