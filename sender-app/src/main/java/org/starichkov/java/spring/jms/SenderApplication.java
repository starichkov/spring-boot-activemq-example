package org.starichkov.java.spring.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.starichkov.java.spring.jms.config.MessagingConfiguration;
import org.starichkov.java.spring.jms.messages.SampleMessage;

/**
 * @author Vadim Starichkov
 * @since 23.12.2016 13:28
 */
@SpringBootApplication
public class SenderApplication extends MessagingConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(SenderApplication.class);

    public static void main(String[] args) {
        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(SenderApplication.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        LOGGER.info("Sending a sample message...");

        jmsTemplate.convertAndSend("messageBox",
                new SampleMessage("The Force", "Let the force be with you!"));
    }
}
