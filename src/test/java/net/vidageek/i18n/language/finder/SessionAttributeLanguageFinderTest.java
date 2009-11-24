package net.vidageek.i18n.language.finder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import net.vidageek.i18n.language.DefaultLanguage;
import net.vidageek.i18n.language.NotSetLanguage;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class SessionAttributeLanguageFinderTest {

    @Test
    public void testThatFindsLanguage() {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("i18n_lang")).thenReturn("pt_BR");
        Assert.assertEquals(new DefaultLanguage("pt_BR"), new SessionAttributeLanguageFinder(session).findLanguage());
    }

    @Test
    public void testThatReturnsNotSetLanguageIfCantFindLanguage() {
        HttpSession session = mock(HttpSession.class);
        Assert.assertEquals(new NotSetLanguage(), new SessionAttributeLanguageFinder(session).findLanguage());
    }

    @Test
    public void testThatReturnsNotSetLanguageIfSessionObjectIsNotAString() {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("i18n_lang")).thenReturn(new Object());
        Assert.assertEquals(new NotSetLanguage(), new SessionAttributeLanguageFinder(session).findLanguage());
    }

}
