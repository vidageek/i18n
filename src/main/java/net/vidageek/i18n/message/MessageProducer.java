package net.vidageek.i18n.message;

import java.util.Properties;

/**
 * @author jonasabreu
 * 
 */
final public class MessageProducer {

    private final Properties properties;

    public MessageProducer(final Properties properties) {
        this.properties = properties;
    }

    public String getMessage(final String i18nKey) {
        return properties.getProperty(i18nKey, "??? " + i18nKey + " ???");
    }

}
