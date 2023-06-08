package dev.starichkov.java.spring.jms.consumers.queues;

import dev.starichkov.java.spring.jms.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import dev.starichkov.java.spring.jms.messages.SampleMessage;

/**
 * @author Vadim Starichkov
 * @since 23.12.2016 13:32
 */
@Component
public class QueueSampleMessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueSampleMessageConsumer.class);

    @JmsListener(destination = Constants.DEFAULT_QUEUE_NAME, containerFactory = "sampleMessageFactory")
    public void receiveMessage(SampleMessage message) {
        LOGGER.info("Queue Consumer: received < {} >", message);
    }
}
