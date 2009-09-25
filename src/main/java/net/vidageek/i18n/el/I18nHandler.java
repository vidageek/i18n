package net.vidageek.i18n.el;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.vidageek.i18n.el.log.Logger;

/**
 * @author jonasabreu
 * 
 */
final public class I18nHandler {

    private static final Logger log = new Logger(I18nHandler.class);

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
            InputStream stream = I18nHandler.class.getResourceAsStream("/messages.properties");
            if (stream == null) {
                log.warn("Could not find i18n properties message.properties");
            } else {
                properties.load(stream);
            }
        } catch (IOException e) {
            log.warn("Could not read i18n properties message.properties", e);
        }

        return properties.getProperty(i18nKey, "??? " + i18nKey + " ???");
    }
}
