package org.starichkov.java.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Vadim Starichkov
 * @since 27.12.2016 15:46
 */
@SpringBootApplication
public class SenderApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SenderApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SenderApplication.class, args);
    }
}
