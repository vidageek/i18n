package net.vidageek.i18n.el.log;

import org.apache.log4j.Logger;

/**
 * @author jonasabreu
 * 
 */
final public class DefaultLoggerWraper implements LoggerWraper {

    private final Logger logger;

    public DefaultLoggerWraper(final Class<?> type) {
        logger = Logger.getLogger(type);
    }

    public void warn(final String message, final Throwable t) {
        logger.warn(message, t);

    }

    public void warn(final String message) {
        logger.warn(message);
    }

}
