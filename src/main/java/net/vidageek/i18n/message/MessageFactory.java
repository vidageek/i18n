package net.vidageek.i18n.message;


/**
 * @author jonasabreu
 * 
 */
final public class MessageFactory {

    private final LanguageLocator languageLocator;
    private final BundleLocator bundleLocator;

    public MessageFactory(final LanguageLocator languageLocator, final BundleLocator bundleLocator) {
        this.languageLocator = languageLocator;
        this.bundleLocator = bundleLocator;
    }

    public String getMessage(final String i18nKey) {

        MessageProducer producer = bundleLocator.findBundle(languageLocator.findLanguage());

        return producer.getMessage(i18nKey);

    }
}
