package net.vidageek.i18n.message;

import net.vidageek.i18n.el.log.Logger;
import net.vidageek.i18n.language.Language;
import net.vidageek.i18n.language.NotSetLanguage;

/**
 * @author jonasabreu
 * 
 */
final public class FileNameCreator {

    private final Logger log = new Logger(FileNameCreator.class);

    private final String baseName;
    private final String fallBackBundleName;

    public FileNameCreator(final String baseName) {
        this.baseName = baseName;
        fallBackBundleName = "/" + baseName + ".properties";
        log.debug("Initialized name creator. Using [" + baseName + "] as base name. Fallback name is ["
                + fallBackBundleName + "]");
    }

    public String createFileNameFor(final Language language) {
        if (NotSetLanguage.class.isAssignableFrom(language.getClass())) {
            return fallBackBundleName;
        }
        return "/" + baseName + "_" + language.code() + ".properties";
    }

}
