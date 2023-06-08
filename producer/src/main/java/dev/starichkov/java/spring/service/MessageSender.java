package dev.starichkov.java.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import dev.starichkov.java.spring.jms.messages.SampleMessage;

import java.util.concurrent.atomic.AtomicLong;

import static dev.starichkov.java.spring.jms.config.Constants.DEFAULT_QUEUE_NAME;
import static dev.starichkov.java.spring.jms.config.Constants.DEFAULT_TOPIC_NAME;

/**
 * @author Vadim Starichkov
 * @since 08.06.2023 15:36
 */
@Service
public class MessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);

    private final JmsTemplate jmsTopicTemplate;

    private final JmsTemplate jmsQueueTemplate;

    private AtomicLong topicMessageCounter;

    @Autowired
    public MessageSender(@Qualifier("jmsTopicTemplate") JmsTemplate jmsTopicTemplate,
                         @Qualifier("jmsQueueTemplate") JmsTemplate jmsQueueTemplate) {
        this.jmsTopicTemplate = jmsTopicTemplate;
        this.jmsQueueTemplate = jmsQueueTemplate;
        this.topicMessageCounter = new AtomicLong(1);
    }

    public void sendTopic() {
        LOGGER.info("Sending message to topic...");
        jmsTopicTemplate.convertAndSend(DEFAULT_TOPIC_NAME,
                new SampleMessage("From Timer", "Message #" + topicMessageCounter.getAndIncrement() + " from topic."));
    }

    public void sendQueue() {
        LOGGER.info("Sending message to queue...");
        jmsQueueTemplate.convertAndSend(DEFAULT_QUEUE_NAME,
                new SampleMessage("The Force", "Let the force be with you!"));
    }
}
