package net.tackley.weretwit.tweet;

import java.util.Date;

public class TweetVO {
    private String text;
    private Date createdAt;
    private String phaseInHex;

    public TweetVO(String text, Date createdAt, String phaseInHex) {
        this.text = text;
        this.createdAt = createdAt;
        this.phaseInHex = phaseInHex;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPhaseInHex() {
        return phaseInHex;
    }

    public void setPhaseInHex(String phaseInHex) {
        this.phaseInHex = phaseInHex;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
