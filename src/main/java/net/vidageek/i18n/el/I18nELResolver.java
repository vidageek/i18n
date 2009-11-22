package net.vidageek.i18n.el;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ELResolver;

import net.vidageek.i18n.message.MessageFactory;

/**
 * @author jonasabreu
 * 
 */
final public class I18nELResolver extends ELResolver {

    private final MessageFactory factory;

    public I18nELResolver(final MessageFactory factory) {
        this.factory = factory;
    }

    @Override
    public Object getValue(final ELContext context, final Object base, final Object property) {
        if ("i18n".equals(property.toString())) {
            context.setPropertyResolved(true);
            return new I18nHandler("", factory);
        }
        if ((base != null) && I18nHandler.class.equals(base.getClass())) {
            context.setPropertyResolved(true);
            String key = I18nHandler.class.cast(base).i18nKey();
            if (!"".equals(key)) {
                key += ".";
            }
            return new I18nHandler(key + property, factory);

        }
        return null;
    }

    @Override
    public Class<?> getCommonPropertyType(final ELContext context, final Object base) {
        return Object.class;
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(final ELContext context, final Object base) {
        return new ArrayList<FeatureDescriptor>().iterator();
    }

    @Override
    public Class<?> getType(final ELContext context, final Object base, final Object property) {
        return Object.class;
    }

    @Override
    public boolean isReadOnly(final ELContext context, final Object base, final Object property) {
        return true;
    }

    @Override
    public void setValue(final ELContext context, final Object base, final Object property, final Object value) {
    }

}
