package net.vidageek.i18n.language;

/**
 * @author jonasabreu
 * 
 */
final public class LanguageLocator {

    private static ThreadLocal<Language> languages = new ThreadLocal<Language>();

    public Language findLanguage() {
        Language language = languages.get();
        if (language == null) {
            language = new NotSetLanguage();
        }
        return language;
    }

    public static void setLanguage(final Language language) {
        languages.set(language);
    }

    public static void remove() {
        languages.remove();

    }

}
