package net.vidageek.i18n.el;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.el.ELContext;

import junit.framework.Assert;
import net.vidageek.i18n.message.MessageFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class I18nELResolverTest {

    private I18nELResolver resolver;
    private ELContext context;

    @Before
    public void setup() {
        context = mock(ELContext.class);
        MessageFactory factory = mock(MessageFactory.class);
        resolver = new I18nELResolver(factory);
    }

    @Test
    public void testThatAcceptI18n() {
        Object handler = resolver.getValue(context, null, "i18n");

        Assert.assertNotNull(handler);
        Assert.assertEquals(I18nHandler.class, handler.getClass());
    }

    @Test
    public void testThatSetsPropertyAsResolvedForI18n() {
        Object handler = resolver.getValue(context, null, "i18n");

        Assert.assertNotNull(handler);
        Assert.assertEquals(I18nHandler.class, handler.getClass());

        verify(context).setPropertyResolved(true);
    }

    @Test
    public void testThatBuildsPropertyKey() {
        Object handler = resolver.getValue(context, null, "i18n");
        handler = resolver.getValue(context, handler, "foo");
        handler = resolver.getValue(context, handler, "bar");

        Assert.assertEquals("foo.bar", I18nHandler.class.cast(handler).i18nKey());

        verify(context, times(3)).setPropertyResolved(true);
    }

}
