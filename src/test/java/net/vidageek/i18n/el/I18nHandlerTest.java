package net.vidageek.i18n.el;

import junit.framework.Assert;
import net.vidageek.i18n.language.LanguageLocator;
import net.vidageek.i18n.message.FileNameCreator;
import net.vidageek.i18n.message.MessageFactory;
import net.vidageek.i18n.message.MessageProducer;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class I18nHandlerTest {

    private MessageFactory factory;

    @Before
    public void setup() {
        factory = new MessageFactory(new LanguageLocator(), new MessageProducer(new FileNameCreator("messages")));
    }

    @Test
    public void testThatLocalizeMessage() {
        I18nHandler handler = new I18nHandler("test", factory);
        Assert.assertEquals("this is a test", handler.toString());
    }

    @Test
    public void testThatOutputSomethingWhenKeyIsNotFound() {
        I18nHandler handler = new I18nHandler("some.key.that.does.not.exist", factory);
        Assert.assertEquals("??? some.key.that.does.not.exist ???", handler.toString());
    }
}
