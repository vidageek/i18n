package net.vidageek.i18n.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import net.vidageek.i18n.el.log.Logger;

/**
 * @author jonasabreu
 * 
 */
final public class MessageProducer {

    private final Logger log = new Logger(MessageProducer.class);

    private final Map<Language, Properties> bundles;

    private final Object lock = new Object();

    private final FileNameCreator creator;

    public MessageProducer(final FileNameCreator creator) {
        this.creator = creator;
        bundles = new ConcurrentHashMap<Language, Properties>();
        bundles.put(new NotSetLanguage(), readProperties(creator.createFileNameFor(new NotSetLanguage())));
    }

    public String getMessage(final String i18nKey, final Language language) {
        return getBundle(language).getProperty(i18nKey, "??? " + i18nKey + " ???");
    }

    private Properties getBundle(final Language language) {
        log.debug("getting bundle for language " + language);
        Properties properties = bundles.get(language);
        if (properties == null) {
            synchronized (lock) {
                properties = bundles.get(language);
                if (properties == null) {
                    properties = readProperties(buildFileName(language));
                    // use fallback
                    if (!properties.propertyNames().hasMoreElements()) {
                        properties = bundles.get(new NotSetLanguage());
                    }
                }
                bundles.put(language, properties);
            }
        }
        return properties;
    }

    private Properties readProperties(final String fileName) {
        log.debug("Reading file " + fileName);
        Properties properties = new Properties();

        try {
            InputStream stream = MessageProducer.class.getResourceAsStream(fileName);
            if (stream == null) {
                log.warn("Could not find i18n properties " + fileName + ".");
            } else {
                properties.load(new InputStreamReader(stream, "UTF-8"));
            }
        } catch (IOException e) {
            log.warn("Problem loading properties " + fileName + ". Using default language.", e);
        }
        return properties;
    }

    private String buildFileName(final Language language) {
        return creator.createFileNameFor(language);

    }

}
