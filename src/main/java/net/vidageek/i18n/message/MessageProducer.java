package net.vidageek.i18n.message;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import net.vidageek.i18n.el.log.Logger;
import net.vidageek.i18n.language.Language;
import net.vidageek.i18n.language.NotSetLanguage;

/**
 * @author jonasabreu
 * 
 */
final public class MessageProducer {

	private final Logger log = new Logger(MessageProducer.class);

	private final Map<Language, Properties> bundles;

	private final Object lock = new Object();

	private final FileNameCreator creator;

	public MessageProducer(final FileNameCreator creator) {
		this.creator = creator;
		bundles = new ConcurrentHashMap<Language, Properties>();
		bundles.put(new NotSetLanguage(), readProperties(creator.createFileNameFor(new NotSetLanguage())));
	}

	public String getMessage(final String i18nKey, final Language language) {
		Properties bundle = getBundle(language);
		if (bundle.getProperty(i18nKey) == null) {
			String fromResource = getMessageFromResource(i18nKey, language);
			bundle.setProperty(i18nKey, fromResource);
			return fromResource;
		}
		return getBundle(language).getProperty(i18nKey);
	}

	private String getMessageFromResource(final String i18nKey, final Language language) {
		String path = "/i18n/" + language.code() + "/" + i18nKey;
		InputStream stream = MessageProducer.class.getResourceAsStream(path);
		String longMessage = "??? " + i18nKey + " ???";
		if (stream == null) {
			path = "/i18n/default/" + i18nKey;
			stream = MessageProducer.class.getResourceAsStream(path);
		}
		if (stream != null) {
			longMessage = new Scanner(stream).useDelimiter("$$").next();
		}
		return longMessage;
	}

	private Properties getBundle(final Language language) {
		log.debug("getting bundle for language " + language);
		Properties properties = bundles.get(language);
		if (properties == null) {
			synchronized (lock) {
				properties = bundles.get(language);
				if (properties == null) {
					properties = readProperties(buildFileName(language));
					// use fallback
					if (!properties.propertyNames().hasMoreElements()) {
						properties = bundles.get(new NotSetLanguage());
					}
				}
				bundles.put(language, properties);
			}
		}
		return properties;
	}

	private Properties readProperties(final String fileName) {
		log.debug("Reading file " + fileName);
		Properties properties = new Properties();

		try {
			InputStream stream = MessageProducer.class.getResourceAsStream(fileName);
			if (stream == null) {
				log.warn("Could not find i18n properties " + fileName + ".");
			} else {
				properties.load(new InputStreamReader(stream, "UTF-8"));
			}
		} catch (IOException e) {
			log.warn("Problem loading properties " + fileName + ". Using default language.", e);
		}
		return properties;
	}

	private String buildFileName(final Language language) {
		return creator.createFileNameFor(language);

	}

}
