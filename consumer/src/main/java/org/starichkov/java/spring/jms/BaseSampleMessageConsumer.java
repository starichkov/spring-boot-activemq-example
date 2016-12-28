package org.starichkov.java.spring.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vadim Starichkov
 * @since 26.12.2016 17:56
 */
abstract class BaseSampleMessageConsumer {
    final Logger logger = LoggerFactory.getLogger(getReceiverName());

    private String getReceiverName() {
        return getClass().getName();
    }
}
