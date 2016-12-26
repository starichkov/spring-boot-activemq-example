package org.starichkov.java.spring.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.starichkov.java.spring.jms.config.MessagingConfiguration;
import org.starichkov.java.spring.jms.messages.SampleMessage;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Vadim Starichkov
 * @since 23.12.2016 13:28
 */
@SpringBootApplication
public class SenderApplication extends MessagingConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(SenderApplication.class);

    private JmsTemplate jmsTopicTemplate;
    private JmsTemplate jmsQueueTemplate;

    @Autowired
    @Qualifier("jmsTopicTemplate")
    public void setJmsTopicTemplate(JmsTemplate jmsTopicTemplate) {
        this.jmsTopicTemplate = jmsTopicTemplate;
    }

    @Autowired
    @Qualifier("jmsQueueTemplate")
    public void setJmsQueueTemplate(JmsTemplate jmsQueueTemplate) {
        this.jmsQueueTemplate = jmsQueueTemplate;
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                LOGGER.info("Sending a sample messages...");

                new Timer().schedule(new TimerTask() {
                    int counter = 1;

                    @Override
                    public void run() {
                        jmsTopicTemplate.convertAndSend(DEFAULT_TOPIC_NAME,
                                new SampleMessage("From Timer", "Message #" + counter + " from Timer."));
                        counter++;
                    }
                }, 0, 1000);

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        jmsQueueTemplate.convertAndSend(DEFAULT_QUEUE_NAME,
                                new SampleMessage("The Force", "Let the force be with you!"));
                    }
                }, 5000);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SenderApplication.class, args);
    }
}
