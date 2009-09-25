package net.vidageek.i18n.el;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author jonasabreu
 * 
 */
final public class I18nHandler {

    private static final Logger log = Logger.getLogger(I18nHandler.class);

    private final String i18nKey;

    public I18nHandler(final String i18nKey) {
        this.i18nKey = i18nKey;
    }

    public String i18nKey() {
        return i18nKey;
    }

    @Override
    public String toString() {
        Properties properties = new Properties();
        try {
            properties.load(I18nHandler.class.getResourceAsStream("/messages.properties"));
        } catch (IOException e) {
            log.info("Could not find i18n properties message.properties", e);
        }
        return properties.getProperty(i18nKey);
    }
}
