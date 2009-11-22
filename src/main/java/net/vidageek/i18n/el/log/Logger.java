package net.vidageek.i18n.el.log;

/**
 * @author jonasabreu
 * 
 */
final public class Logger {

    private final LoggerWrapper logger;
    private static boolean foundLog4j;

    static {
        try {
            Class.forName("org.apache.log4j.Logger");
            foundLog4j = true;
        } catch (ClassNotFoundException e) {
            // Just because there is no good way to tell if a class is on
            // the classpath. Sometimes java is sooooooooooo exciting.
            foundLog4j = false;
        }
    }

    public Logger(final Class<?> type) {
        if (foundLog4j) {
            logger = new DefaultLoggerWrapper(type);
        } else {
            logger = new NoActionLoggerWrapper();
        }
    }

    public void warn(final String message, final Throwable t) {
        logger.warn(message, t);
    }

    public void warn(final String message) {
        logger.warn(message);
    }

    public void debug(final String string) {
        logger.debug(string);

    }

    public void fatal(final String string) {
        logger.fatal(string);
    }

}
