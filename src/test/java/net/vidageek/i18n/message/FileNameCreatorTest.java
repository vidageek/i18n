package net.vidageek.i18n.message;

import junit.framework.Assert;

import net.vidageek.i18n.language.DefaultLanguage;
import net.vidageek.i18n.language.NotSetLanguage;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class FileNameCreatorTest {

    @Test
    public void testThatCreatesFallbackNameForNotSetLanguage() {
        FileNameCreator creator = new FileNameCreator("baseName");
        Assert.assertEquals("/baseName.properties", creator.createFileNameFor(new NotSetLanguage()));
    }

    @Test
    public void testThatCreatesNameForLanguage() {
        FileNameCreator creator = new FileNameCreator("baseName");
        Assert.assertEquals("/baseName_pt_BR.properties", creator.createFileNameFor(new DefaultLanguage("pt_BR")));
    }

}
