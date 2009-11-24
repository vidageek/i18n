package net.vidageek.i18n.message;

import net.vidageek.i18n.language.LanguageLocator;

/**
 * @author jonasabreu
 * 
 */
public class MessageFactory {

    private final LanguageLocator languageLocator;
    private final MessageProducer producer;

    public MessageFactory(final LanguageLocator languageLocator, final MessageProducer producer) {
        this.languageLocator = languageLocator;
        this.producer = producer;
    }

    public String getMessage(final String i18nKey) {

        return producer.getMessage(i18nKey, languageLocator.findLanguage());

    }
}
