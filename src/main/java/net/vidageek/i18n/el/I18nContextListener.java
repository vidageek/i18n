package net.vidageek.i18n.el;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspFactory;

import net.vidageek.i18n.message.FileNameCreator;
import net.vidageek.i18n.message.LanguageLocator;
import net.vidageek.i18n.message.MessageFactory;
import net.vidageek.i18n.message.MessageProducer;

/**
 * @author jonasabreu
 * 
 */
final public class I18nContextListener implements ServletContextListener {

    public void contextDestroyed(final ServletContextEvent sce) {
        // does nothing at context destruction
    }

    public void contextInitialized(final ServletContextEvent event) {
        ServletContext context = event.getServletContext();

        JspApplicationContext jspContext = JspFactory.getDefaultFactory().getJspApplicationContext(context);

        jspContext.addELResolver(new I18nELResolver(new MessageFactory(new LanguageLocator(), new MessageProducer(
                new FileNameCreator("messages")))));
    }
}
