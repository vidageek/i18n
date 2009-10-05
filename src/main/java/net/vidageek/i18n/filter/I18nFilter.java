package net.vidageek.i18n.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
            } else {
                HttpServletRequest servletRequest = (HttpServletRequest) request;
                String requestAttribute = servletRequest.getSession().getAttribute("i18n_lang").toString();
                if (requestAttribute != null) {
                    LanguageLocator.setLanguage(requestAttribute);
                } else {
                    Cookie[] cookies = servletRequest.getCookies();
                    for (int i = 0; i < cookies.length; i++) {
                        if ("i18n_lang".equals(cookies[i].getName())) {
                            LanguageLocator.setLanguage(cookies[i].getValue());
                            break;
                        }
                    }

                }
            }
            chain.doFilter(request, response);
        } finally {
            LanguageLocator.remove();
        }

    }

    public void init(final FilterConfig filterConfig) throws ServletException {

    }

}
