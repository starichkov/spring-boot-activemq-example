package dev.starichkov.java.spring.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.starichkov.java.spring.service.MessageSender;

/**
 * @author Vadim Starichkov
 * @since 27.12.2016 17:19
 */
@RestController
public class ProducerRestController {

    private final MessageSender jms;

    @Autowired
    public ProducerRestController(MessageSender jms) {
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
