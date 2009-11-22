package net.vidageek.i18n.el.log;

/**
 * @author jonasabreu
 * 
 */
public interface LoggerWrapper {

    public void warn(final String message, final Throwable t);

    public void warn(final String message);

    public void debug(String string);

    public void fatal(String string);

}
