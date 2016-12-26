package org.starichkov.java.spring.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.destination.DestinationResolver;
import org.starichkov.java.spring.jms.config.MessagingConfiguration;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @author Vadim Starichkov
 * @since 23.12.2016 13:31
 */
@SpringBootApplication
@EnableJms
public class ReceiverApplication extends MessagingConfiguration {

    /**
     * Creates custom destination resolver:<br/>
     * <ul>
     * <li>If destination name ends with "Queue" - creates queue destination.</li>
     * <li>If destination name ends with "Topic" - creates topic destination.</li>
     * <li>Throws RuntimeException otherwise.</li>
     * </ul>
     * <p>
     * Another way to implement such behaviour - use two factories, one for queue and one for topic,
     * setting {@code factory.setPubSubDomain(true)} for topic's factory.
     *
     * @return custom destination resolver
     * @throws RuntimeException if destination name does not end with "Queue" or "Topic"
     */
    @Bean
    public DestinationResolver destinationResolver() throws RuntimeException {
        return new DestinationResolver() {
            @Override
            public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain) throws JMSException {
                if (destinationName.endsWith("Queue")) {
                    return session.createQueue(destinationName);
                } else if (destinationName.endsWith("Topic")) {
                    return session.createTopic(destinationName);
                }
                throw new RuntimeException("Naming convention not respected for destination " + destinationName);
            }
        };
    }

    @Bean
    public JmsListenerContainerFactory<?> sampleMessageFactory(DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setDestinationResolver(destinationResolver());
        configurer.configure(factory, connectionFactory());
        return factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }
}
