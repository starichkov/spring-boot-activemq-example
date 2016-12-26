package org.starichkov.java.spring.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.starichkov.java.spring.jms.messages.SampleMessage;

import static org.starichkov.java.spring.jms.config.MessagingConfiguration.DEFAULT_TOPIC_NAME;

/**
 * @author Vadim Starichkov
 * @since 26.12.2016 17:17
 */
@Component
public class TopicSampleMessageReceiver1 extends BaseSampleMessageReceiver {

    @JmsListener(destination = DEFAULT_TOPIC_NAME, containerFactory = "sampleMessageFactory")
    public void receiveMessage(SampleMessage message) {
        logger.info("Received < " + message + " >");
    }
}
