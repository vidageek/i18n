package net.vidageek.i18n.message;

/**
 * @author jonasabreu
 * 
 */
final public class NotSetLanguage implements Language {

    public String code() {
        return "";
    }

    @Override
    public String toString() {
        return "[not set]";
    }

    @Override
    public boolean equals(final Object obj) {
        return code().equals(((Language) obj).code());
    }

    @Override
    public int hashCode() {
        return code().hashCode();
    }

}
