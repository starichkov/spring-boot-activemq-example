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
public class Receiver {
    @JmsListener(destination = MessagingConfiguration.QUEUE_NAME, containerFactory = "messageFactory")
    public void receiveMessage(SampleMessage message) {
        System.out.println("Received < " + message + " >");
    }
}
