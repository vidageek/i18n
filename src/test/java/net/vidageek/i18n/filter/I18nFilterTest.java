package net.vidageek.i18n.filter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import junit.framework.Assert;
import net.vidageek.i18n.language.DefaultLanguage;
import net.vidageek.i18n.language.Language;
import net.vidageek.i18n.language.LanguageLocator;
import net.vidageek.i18n.language.NotSetLanguage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class I18nFilterTest {

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setup() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @After
    public void tearDown() {
        LanguageLocator.remove();
    }

    @Test
    public void testThatFindsLanguageOnRequest() throws Throwable {
        Filter filter = new I18nFilter();
        when(request.getParameter("i18n_lang")).thenReturn("pt_BR");
        filter.doFilter(request, response, new AssertLanguageChain(new DefaultLanguage("pt_BR")));
    }

    @Test
    public void testThatFindsLanguageOnSession() throws Throwable {
        Filter filter = new I18nFilter();
        final HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("i18n_lang")).thenReturn("pt_BR");
        filter.doFilter(request, response, new AssertLanguageChain(new DefaultLanguage("pt_BR")));
    }

    @Test
    public void testThatFindsLanguageOnCookies() throws Throwable {
        Filter filter = new I18nFilter();
        final HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getCookies()).thenReturn(new Cookie[] { new Cookie("i18n_lang", "pt_BR") });
        filter.doFilter(request, response, new AssertLanguageChain(new DefaultLanguage("pt_BR")));
    }

    @Test
    public void testThatIfCantFindLanguageSetItAsNotSetLanguage() throws Throwable {
        Filter filter = new I18nFilter();
        final HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getCookies()).thenReturn(new Cookie[] {});
        filter.doFilter(request, response, new AssertLanguageChain(new NotSetLanguage()));
    }

    @Test
    public void testThatRequestHasPriorityOverAllOtherMethods() throws Throwable {
        Filter filter = new I18nFilter();
        final HttpSession session = mock(HttpSession.class);
        when(request.getParameter("i18n_lang")).thenReturn("pt_BR");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("i18n_lang")).thenReturn("en_US");
        when(request.getCookies()).thenReturn(new Cookie[] { new Cookie("i18n_lang", "en_US") });
        filter.doFilter(request, response, new AssertLanguageChain(new DefaultLanguage("pt_BR")));
    }

    @Test
    public void testThatSessionHasPriorityOverCookies() throws Throwable {
        Filter filter = new I18nFilter();
        final HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("i18n_lang")).thenReturn("pt_BR");
        when(request.getCookies()).thenReturn(new Cookie[] { new Cookie("i18n_lang", "en_US") });
        filter.doFilter(request, response, new AssertLanguageChain(new DefaultLanguage("pt_BR")));
    }

    @Test
    public void testThatRemovesLanguageAfterDoFilter() throws Throwable {
        Filter filter = new I18nFilter();
        when(request.getParameter("i18n_lang")).thenReturn("pt_BR");
        try {
            filter.doFilter(request, response, new ThrowExceptionChain());
        } catch (Exception e) {
            Assert.assertEquals(new NotSetLanguage(), new LanguageLocator().findLanguage());
        }
    }

    public class AssertLanguageChain implements FilterChain {

        private final Language language;

        public AssertLanguageChain(final Language language) {
            this.language = language;
        }

        public void doFilter(final ServletRequest request, final ServletResponse response) throws IOException,
                ServletException {
            Assert.assertEquals(language, new LanguageLocator().findLanguage());
        }
    }

    public class ThrowExceptionChain implements FilterChain {

        public void doFilter(final ServletRequest request, final ServletResponse response) throws IOException,
                ServletException {
            throw new RuntimeException();
        }

    }
}
