package net.tackley.were;

import net.tackley.weretwit.moon.CalendarBasedMoon;
import net.tackley.weretwit.moon.Moon;
import org.junit.Test;
import twitter4j.Status;
import twitter4j.Twitter;

import java.util.List;

public class TweetRetrieveTest {
    @Test
    public void shouldGetListOfTweets() throws Exception {
        Moon moon = new CalendarBasedMoon();
        Twitter twit = new Twitter();
        List<Status> timeline = twit.getUserTimeline("tackers");


        for (Status status : timeline) {
            System.out.printf("\"%s\" tweeted at %s (phase %d)\n", status.getText(), status.getCreatedAt(),
                    moon.getPhaseOfMoon(status.getCreatedAt()));
        }
    }


}
