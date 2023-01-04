package org.starichkov.java.spring.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.starichkov.java.spring.config.ProducerJmsConfig;

/**
 * @author Vadim Starichkov
 * @since 27.12.2016 17:19
 */
@RestController
public class ProducerRestController {

    private final ProducerJmsConfig jms;

    @Autowired
    public ProducerRestController(ProducerJmsConfig jms) {
        this.jms = jms;
    }

    @PostMapping("/topic")
    public void sendTopic() {
        jms.sendTopic();
    }

    @PostMapping("/queue")
    public void sendQueue() {
        jms.sendQueue();
    }
}
