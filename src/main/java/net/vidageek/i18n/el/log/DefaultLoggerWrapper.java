package net.vidageek.i18n.el.log;

import org.apache.log4j.Logger;

/**
 * @author jonasabreu
 * 
 */
final public class DefaultLoggerWrapper implements LoggerWrapper {

    private final Logger logger;

    public DefaultLoggerWrapper(final Class<?> type) {
        logger = Logger.getLogger(type);
    }

    public void warn(final String message, final Throwable t) {
        logger.warn(message, t);

    }

    public void warn(final String message) {
        logger.warn(message);
    }

    public void debug(final String message) {
        logger.debug(message);
    }

    public void fatal(final String message) {
        logger.fatal(message);
    }

}
