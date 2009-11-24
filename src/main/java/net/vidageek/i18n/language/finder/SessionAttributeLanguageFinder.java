package net.vidageek.i18n.language.finder;

import javax.servlet.http.HttpSession;

import net.vidageek.i18n.language.DefaultLanguage;
import net.vidageek.i18n.language.Language;
import net.vidageek.i18n.language.NotSetLanguage;

/**
 * @author jonasabreu
 * 
 */
final public class SessionAttributeLanguageFinder implements LanguageFinder {

    private final HttpSession session;

    public SessionAttributeLanguageFinder(final HttpSession session) {
        this.session = session;
    }

    public Language findLanguage() {
        Object attribute = session.getAttribute("i18n_lang");
        if ((attribute == null) || !String.class.isAssignableFrom(attribute.getClass())) {
            return new NotSetLanguage();
        }
        return new DefaultLanguage(String.class.cast(attribute));
    }

}
