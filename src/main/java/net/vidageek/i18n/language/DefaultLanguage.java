package net.vidageek.i18n.language;

/**
 * @author jonasabreu
 * 
 */
final public class DefaultLanguage implements Language {

    private final String code;

    public DefaultLanguage(final String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    @Override
    public boolean equals(final Object obj) {
        return code().equals(((Language) obj).code());
    }

    @Override
    public int hashCode() {
        return code().hashCode();
    }

    @Override
    public String toString() {
        return "[" + code + "]";
    }

}
