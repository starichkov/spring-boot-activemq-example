package org.starichkov.java.spring.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.starichkov.java.spring.jms.config.MessagingConfiguration;
import org.starichkov.java.spring.jms.messages.SampleMessage;

/**
 * @author Vadim Starichkov
 * @since 23.12.2016 13:32
 */
@Component
public class QueueSampleMessageReceiver extends BaseSampleMessageReceiver {

    @JmsListener(destination = MessagingConfiguration.DEFAULT_QUEUE_NAME, containerFactory = "sampleMessageFactory")
    public void receiveMessage(SampleMessage message) {
        logger.info("Received < " + message + " >");
    }
}
