package org.starichkov.java.spring.jms.config;

//import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.destination.DestinationResolver;

import javax.jms.ConnectionFactory;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 04.01.2023 19:02
 */
@Configuration
@EnableJms
public class JmsConfig {

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
        return (session, destinationName, pubSubDomain) -> {
            if (destinationName.endsWith("Queue")) {
                assert session != null;
                return session.createQueue(destinationName);
            } else if (destinationName.endsWith("Topic")) {
                assert session != null;
                return session.createTopic(destinationName);
            }
            throw new RuntimeException("Naming convention not respected for destination " + destinationName);
        };
    }

    @Bean
    public JmsListenerContainerFactory<?> sampleMessageFactory(DefaultJmsListenerContainerFactoryConfigurer configurer,
                                                               ConnectionFactory connectionFactory
    ) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setDestinationResolver(destinationResolver());
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
