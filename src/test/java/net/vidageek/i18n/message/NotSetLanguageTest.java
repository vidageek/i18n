package net.vidageek.i18n.message;

import junit.framework.Assert;

import net.vidageek.i18n.language.NotSetLanguage;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class NotSetLanguageTest {

    @Test
    public void testThatIsNotEqualsWithNull() {
        Assert.assertFalse(new NotSetLanguage().equals(null));
    }
}
