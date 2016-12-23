package org.starichkov.java.spring.jms.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.starichkov.java.spring.jms.messages.SampleMessage;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * @author Vadim Starichkov
 * @since 23.12.2016 14:58
 */
@Configuration
public class MessagingConfiguration {
    public static final String DEFAULT_BROKER_URL = "auto://localhost:61616";
    public static final String QUEUE_NAME = "messageBox";

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        return connectionFactory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter() {
            @Override
            protected JavaType getJavaTypeForMessage(Message message) throws JMSException {
                return TypeFactory.defaultInstance().constructType(SampleMessage.class);
            }
        };
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(QUEUE_NAME);
        template.setMessageConverter(jacksonJmsMessageConverter());
        return template;
    }
}
