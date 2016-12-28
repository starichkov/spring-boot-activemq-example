package org.starichkov.java.spring.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.starichkov.java.spring.jms.config.MessagingConfiguration;
import org.starichkov.java.spring.jms.messages.SampleMessage;

/**
 * @author Vadim Starichkov
 * @since 23.12.2016 13:28
 */
@Configuration
public class ProducerConfigurationJMS extends MessagingConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerConfigurationJMS.class);

    private JmsTemplate jmsTopicTemplate;
    private JmsTemplate jmsQueueTemplate;
    private int messageCounter;

    @Autowired
    @Qualifier("jmsTopicTemplate")
    private void setJmsTopicTemplate(JmsTemplate jmsTopicTemplate) {
        this.jmsTopicTemplate = jmsTopicTemplate;
    }

    @Autowired
    @Qualifier("jmsQueueTemplate")
    private void setJmsQueueTemplate(JmsTemplate jmsQueueTemplate) {
        this.jmsQueueTemplate = jmsQueueTemplate;
    }

    public void sendTopic() {
        LOGGER.info("Sending message to topic...");
        jmsTopicTemplate.convertAndSend(DEFAULT_TOPIC_NAME,
                new SampleMessage("From Timer", "Message #" + messageCounter + " from topic."));
        messageCounter++;
    }

    public void sendQueue() {
        LOGGER.info("Sending message to queue...");
        jmsQueueTemplate.convertAndSend(DEFAULT_QUEUE_NAME,
                new SampleMessage("The Force", "Let the force be with you!"));
    }
}
