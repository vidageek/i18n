package net.vidageek.i18n.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.vidageek.i18n.language.Language;
import net.vidageek.i18n.language.LanguageLocator;
import net.vidageek.i18n.language.NotSetLanguage;
import net.vidageek.i18n.language.finder.CookieLanguageFinder;
import net.vidageek.i18n.language.finder.LanguageFinder;
import net.vidageek.i18n.language.finder.RequestParameterLanguageFinder;
import net.vidageek.i18n.language.finder.SessionAttributeLanguageFinder;

/**
 * @author jonasabreu
 * 
 */
final public class I18nFilter implements Filter {

    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {

        Language language = new NotSetLanguage();

        for (LanguageFinder finder : findersFor((HttpServletRequest) request)) {
            language = finder.findLanguage();
            if (!new NotSetLanguage().equals(language)) {
                break;
            }
        }

        LanguageLocator.setLanguage(language);

        try {
            chain.doFilter(request, response);
        } finally {
            LanguageLocator.remove();
        }
    }

    private List<LanguageFinder> findersFor(final HttpServletRequest request) {
        final List<LanguageFinder> finders = new ArrayList<LanguageFinder>();
        finders.add(new RequestParameterLanguageFinder(request));
        finders.add(new SessionAttributeLanguageFinder(request.getSession()));
        finders.add(new CookieLanguageFinder(request.getCookies()));
        return finders;
    }

    public void destroy() {

    }

    public void init(final FilterConfig filterConfig) throws ServletException {

    }

}
