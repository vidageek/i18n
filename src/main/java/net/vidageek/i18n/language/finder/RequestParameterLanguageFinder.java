package net.vidageek.i18n.language.finder;

import javax.servlet.http.HttpServletRequest;

import net.vidageek.i18n.language.DefaultLanguage;
import net.vidageek.i18n.language.Language;
import net.vidageek.i18n.language.NotSetLanguage;

/**
 * @author jonasabreu
 * 
 */
final public class RequestParameterLanguageFinder implements LanguageFinder {

    private final HttpServletRequest request;

    public RequestParameterLanguageFinder(final HttpServletRequest request) {
        this.request = request;
    }

    public Language findLanguage() {
        String parameter = request.getParameter("i18n_lang");
        if (parameter == null) {
            return new NotSetLanguage();
        }
        return new DefaultLanguage(parameter);
    }

}
