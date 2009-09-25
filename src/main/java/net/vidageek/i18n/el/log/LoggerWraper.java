package net.vidageek.i18n.el.log;

/**
 * @author jonasabreu
 * 
 */
public interface LoggerWraper {

    public void warn(final String message, final Throwable t);

    public void warn(final String message);

}
