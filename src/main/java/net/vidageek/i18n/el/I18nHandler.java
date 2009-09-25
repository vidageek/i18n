package net.vidageek.i18n.el;


/**
 * @author jonasabreu
 * 
 */
final public class I18nHandler {

    private final String i18nKey;

    public I18nHandler(final String i18nKey) {
        this.i18nKey = i18nKey;
    }

    public String i18nKey() {
        return i18nKey;
    }

    @Override
    public String toString() {
        return i18nKey;
    }
}
