package net.vidageek.i18n.message;

import junit.framework.Assert;

import net.vidageek.i18n.language.DefaultLanguage;
import net.vidageek.i18n.language.NotSetLanguage;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class MessageProducerTest {

    @Test
    public void testThatDoesNotLoopInfinitelyIfCantFindBaseNameProperties() {
        MessageProducer producer = new MessageProducer(new FileNameCreator("someBaseNameThatDoesNotExist"));
        producer.getMessage("message", new NotSetLanguage());
    }

    @Test
    public void testThatUsesFallbackIfCouldNotFindLanguageBundle() {
        MessageProducer producer = new MessageProducer(new FileNameCreator("messages"));
        String message = producer.getMessage("test", new DefaultLanguage("country_code"));
        Assert.assertEquals("this is a test", message);
    }

    @Test
    public void testThatShowsKeyWhenCouldNotFindOnLanguageBundle() {
        MessageProducer producer = new MessageProducer(new FileNameCreator("messages"));
        String message = producer.getMessage("invalid.key", new DefaultLanguage("country_code"));
        Assert.assertEquals("??? invalid.key ???", message);
    }

    @Test
    public void testThatRespectsCountryCode() {
        MessageProducer producer = new MessageProducer(new FileNameCreator("messages"));
        String message = producer.getMessage("test", new DefaultLanguage("pt_BR"));
        Assert.assertEquals("isto é um teste", message);
    }
}
