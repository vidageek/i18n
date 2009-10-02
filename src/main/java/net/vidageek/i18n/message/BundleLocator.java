package net.vidageek.i18n.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.vidageek.i18n.el.log.Logger;

/**
 * @author jonasabreu
 * 
 */
final public class BundleLocator {

    private static final Logger log = new Logger(BundleLocator.class);

    public MessageProducer findBundle(final Language language) {

        Properties properties = new Properties();
        try {
            InputStream stream = MessageFactory.class.getResourceAsStream("/messages.properties");
            if (stream == null) {
                log.warn("Could not find i18n properties message.properties");
            } else {
                properties.load(stream);
            }
        } catch (IOException e) {
            log.warn("Could not read i18n properties message.properties", e);
        }

        return new MessageProducer(properties);
    }

}
