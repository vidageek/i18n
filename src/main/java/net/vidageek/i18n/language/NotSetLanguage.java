package net.vidageek.i18n.language;

/**
 * @author jonasabreu
 * 
 */
final public class NotSetLanguage implements Language {

	public String code() {
		return "default";
	}

	@Override
	public String toString() {
		return "[not set]";
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		return code().equals(((Language) obj).code());
	}

	@Override
	public int hashCode() {
		return code().hashCode();
	}

}
