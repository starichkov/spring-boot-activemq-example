package org.starichkov.java.spring.jms.consumers.topics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.starichkov.java.spring.jms.messages.SampleMessage;

import static org.starichkov.java.spring.jms.config.Constants.DEFAULT_TOPIC_NAME;

/**
 * @author Vadim Starichkov
 * @since 26.12.2016 17:18
 */
@Component
public class TopicSampleMessageConsumer2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicSampleMessageConsumer2.class);

    @JmsListener(destination = DEFAULT_TOPIC_NAME, containerFactory = "sampleMessageFactory")
    public void receiveMessage(SampleMessage message) {
        LOGGER.info("Topic Consumer 2: received < {} >", message);
    }
}
