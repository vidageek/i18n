package net.vidageek.i18n.message;

/**
 * @author jonasabreu
 * 
 */
public class MessageFactory {

    private final LanguageLocator languageLocator;
    private final MessageProducerLocator producerLocator;

    public MessageFactory(final LanguageLocator languageLocator, final MessageProducerLocator producerLocator) {
        this.languageLocator = languageLocator;
        this.producerLocator = producerLocator;
    }

    public String getMessage(final String i18nKey) {

        MessageProducer producer = producerLocator.findProducer(languageLocator.findLanguage());

        return producer.getMessage(i18nKey);

    }
}
