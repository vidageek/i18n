package net.vidageek.i18n.el;

import javax.el.ELContext;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class I18nELResolverTest {

    private I18nELResolver resolver;
    private Mockery mockery;
    private ELContext context;

    @Before
    public void setup() {
        mockery = new Mockery() {
            {
                setImposteriser(ClassImposteriser.INSTANCE);
            }
        };
        context = mockery.mock(ELContext.class);
        resolver = new I18nELResolver();
    }

    @Test
    public void testThatAcceptI18n() {
        mockery.checking(new Expectations() {
            {
                allowing(context);
            }
        });

        Object handler = resolver.getValue(context, null, "i18n");

        Assert.assertNotNull(handler);
        Assert.assertEquals(I18nHandler.class, handler.getClass());
    }

    @Test
    public void testThatSetsPropertyAsResolvedForI18n() {
        mockery.checking(new Expectations() {
            {
                oneOf(context).setPropertyResolved(with(true));
            }
        });

        Object handler = resolver.getValue(context, null, "i18n");

        Assert.assertNotNull(handler);
        Assert.assertEquals(I18nHandler.class, handler.getClass());

        mockery.assertIsSatisfied();
    }

    @Test
    public void testThatBuildsPropertyKey() {
        mockery.checking(new Expectations() {
            {
                allowing(context).setPropertyResolved(with(true));
            }
        });

        Object handler = resolver.getValue(context, null, "i18n");
        handler = resolver.getValue(context, handler, "foo");
        handler = resolver.getValue(context, handler, "bar");

        Assert.assertEquals("foo.bar", I18nHandler.class.cast(handler).i18nKey());

        mockery.assertIsSatisfied();
    }

}
