package net.vidageek.i18n.filter;

import javax.servlet.http.Cookie;

import net.vidageek.i18n.language.DefaultLanguage;
import net.vidageek.i18n.language.NotSetLanguage;
import net.vidageek.i18n.language.finder.CookieLanguageFinder;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class CookieLanguageFinderTest {

    @Test
    public void testThatFindsLanguage() {
        Assert.assertEquals(new DefaultLanguage("pt_BR"), new CookieLanguageFinder(new Cookie("i18n_lang", "pt_BR"))
            .findLanguage());
    }

    @Test
    public void testThatReturnsNotSetLanguageIfCantFindLanguage() {
        Assert.assertEquals(new NotSetLanguage(), new CookieLanguageFinder(new Cookie[] {}).findLanguage());
    }

    @Test
    public void testThatReturnsNotSetLanguageIfCookiesAreNull() {
        Assert.assertEquals(new NotSetLanguage(), new CookieLanguageFinder((Cookie[]) null).findLanguage());
    }
}
