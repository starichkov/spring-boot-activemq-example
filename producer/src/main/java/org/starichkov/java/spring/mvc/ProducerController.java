package org.starichkov.java.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vadim Starichkov
 * @since 27.12.2016 15:39
 */
@Controller
public class ProducerController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
