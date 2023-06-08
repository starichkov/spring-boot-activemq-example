package dev.starichkov.java.spring.jms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.*;

import javax.jms.ConnectionFactory;

/**
 * @author Vadim Starichkov
 * @since 23.12.2016 14:58
 */
@Configuration
public class MessagingConfiguration {

    /**
     * Serialize message content to json using TextMessage
     */
    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("_type_");
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }

    @Bean("jmsQueueTemplate")
    public JmsTemplate jmsQueueTemplate(ConnectionFactory connectionFactory,
                                        MessageConverter messageConverter) {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean("jmsTopicTemplate")
    public JmsTemplate jmsTopicTemplate(ConnectionFactory connectionFactory,
                                        MessageConverter messageConverter) {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory);
        template.setMessageConverter(messageConverter);
        template.setPubSubDomain(true);
        return template;
    }
}
