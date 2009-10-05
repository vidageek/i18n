package net.vidageek.i18n.el;

import net.vidageek.i18n.el.log.Logger;
import net.vidageek.i18n.message.MessageFactory;

/**
 * @author jonasabreu
 * 
 */
final public class I18nHandler {

    private static final Logger log = new Logger(I18nHandler.class);

    private final String i18nKey;
    private final MessageFactory factory;

    public I18nHandler(final String i18nKey, final MessageFactory factory) {
        this.i18nKey = i18nKey;
        this.factory = factory;
    }

    public String i18nKey() {
        return i18nKey;
    }

    @Override
    public String toString() {
        return factory.getMessage(i18nKey);
    }
}
