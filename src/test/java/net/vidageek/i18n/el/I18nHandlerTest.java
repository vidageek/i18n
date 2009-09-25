package net.vidageek.i18n.el;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class I18nHandlerTest {

    @Test
    public void testThatLocalizeMessage() {
        I18nHandler handler = new I18nHandler("test");
        Assert.assertEquals("this is a test", handler.toString());
    }
}
