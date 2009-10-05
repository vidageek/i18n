package net.vidageek.i18n.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.vidageek.i18n.message.LanguageLocator;

/**
 * @author jonasabreu
 * 
 */
final public class I18nFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        try {
            String requestParameter = request.getParameter("i18n_lang");
            if (requestParameter != null) {
                LanguageLocator.setLanguage(requestParameter);
            }
            chain.doFilter(request, response);
        } finally {
            LanguageLocator.remove();
        }

    }

    public void init(final FilterConfig filterConfig) throws ServletException {

    }

}
