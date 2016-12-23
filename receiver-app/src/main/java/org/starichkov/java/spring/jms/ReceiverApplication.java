package org.starichkov.java.spring.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.starichkov.java.spring.jms.config.MessagingConfiguration;

/**
 * @author Vadim Starichkov
 * @since 23.12.2016 13:31
 */
@SpringBootApplication
@EnableJms
public class ReceiverApplication extends MessagingConfiguration {

    @Bean
    public JmsListenerContainerFactory<?> messageFactory(DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory());
        return factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }
}
