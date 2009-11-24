package net.vidageek.i18n.language;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class LanguageLocatorTest {

    @After
    public void tearDown() {
        LanguageLocator.remove();
    }

    @Test
    public void testThatReturnsNotSetLanguageIfLanguageWasNotSet() {
        Assert.assertEquals(new NotSetLanguage(), new LanguageLocator().findLanguage());
    }

    @Test
    public void testThatReturnsLanguageSet() {
        LanguageLocator.setLanguage(new DefaultLanguage("pt_BR"));
        Assert.assertEquals(new DefaultLanguage("pt_BR"), new LanguageLocator().findLanguage());
    }

}
