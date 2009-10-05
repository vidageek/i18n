package net.vidageek.i18n.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import net.vidageek.i18n.el.log.Logger;

/**
 * @author jonasabreu
 * 
 */
final public class MessageProducerLocator {

    private static final Logger log = new Logger(MessageProducerLocator.class);
    private final ConcurrentHashMap<Language, Properties> bundles;

    private final Object lock = new Object();

    public MessageProducerLocator() {
        log.debug("created MessageProducerLocator");
        bundles = new ConcurrentHashMap<Language, Properties>();
    }

    public MessageProducer findProducer(final Language language) {

        Properties properties = getBundle(language);

        return new MessageProducer(properties);
    }

    private Properties getBundle(final Language language) {
        log.debug("getting bundle for language " + language);
        Properties properties = bundles.get(language);
        if (properties == null) {
            synchronized (lock) {
                properties = bundles.get(language);
                if (properties == null) {
                    properties = readProperties(buildFileName(language));
                    bundles.put(language, properties);
                }
            }
        }
        return properties;
    }

    private Properties readProperties(final String fileName) {

        log.debug("Reading file " + fileName);

        Properties properties = new Properties();

        try {
            InputStream stream = MessageFactory.class.getResourceAsStream(fileName);
            if (stream == null) {
                log.warn("Could not find i18n properties " + fileName + ". Using default language.");
                properties = getBundle(new NotSetLanguage());
            } else {
                properties.load(stream);
            }
        } catch (IOException e) {
            log.warn("Could not find i18n properties " + fileName + ". Using default language.", e);
            properties = getBundle(new NotSetLanguage());
        }
        return properties;
    }

    private String buildFileName(final Language language) {
        if (NotSetLanguage.class.isAssignableFrom(language.getClass())) {
            return "/messages.properties";
        }
        return "/messages_" + language.code() + ".properties";
    }

}