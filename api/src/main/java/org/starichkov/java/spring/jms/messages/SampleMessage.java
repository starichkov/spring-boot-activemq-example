package org.starichkov.java.spring.jms.messages;

/**
 * @author Vadim Starichkov
 * @since 23.12.2016 13:33
 */
public class SampleMessage {
    private String title;
    private String body;

    public SampleMessage() {
    }

    public SampleMessage(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "SampleMessage{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
