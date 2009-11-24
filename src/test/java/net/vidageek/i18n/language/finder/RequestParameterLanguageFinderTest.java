package net.vidageek.i18n.language.finder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import net.vidageek.i18n.language.DefaultLanguage;
import net.vidageek.i18n.language.NotSetLanguage;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class RequestParameterLanguageFinderTest {

    @Test
    public void testThatFindsLanguage() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("i18n_lang")).thenReturn("pt_BR");
        Assert.assertEquals(new DefaultLanguage("pt_BR"), new RequestParameterLanguageFinder(request).findLanguage());
    }

    @Test
    public void testThatReturnsNotSetLanguageIfCantFindLanguage() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        Assert.assertEquals(new NotSetLanguage(), new RequestParameterLanguageFinder(request).findLanguage());
    }
}
