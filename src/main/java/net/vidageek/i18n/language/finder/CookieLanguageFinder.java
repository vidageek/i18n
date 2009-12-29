package net.vidageek.i18n.language.finder;

import javax.servlet.http.Cookie;

import net.vidageek.i18n.language.DefaultLanguage;
import net.vidageek.i18n.language.Language;
import net.vidageek.i18n.language.NotSetLanguage;

/**
 * @author jonasabreu
 * 
 */
final public class CookieLanguageFinder implements LanguageFinder {

    private final Cookie[] cookies;

    public CookieLanguageFinder(final Cookie... cookies) {
        this.cookies = cookies;
    }

    public Language findLanguage() {
        if (cookies == null) {
            return new NotSetLanguage();
        }
        Language language = new NotSetLanguage();
        for (int i = 0; i < cookies.length; i++) {
            if ("i18n_lang".equals(cookies[i].getName())) {
                language = new DefaultLanguage(cookies[i].getValue());
            }
        }
        return language;
    }

}
