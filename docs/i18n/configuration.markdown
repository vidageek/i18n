---
title: Configuration
---

### web.xml

It's quite easy to start using I18n. You just need to copy the following lines on your web.xml


    <listener>
    	<listener-class>net.vidageek.i18n.el.I18nContextListener</listener-class>
    </listener>
    
    <filter>
    	<filter-name>i18n</filter-name>
    	<filter-class>net.vidageek.i18n.filter.I18nFilter</filter-class>
    </filter>
    
    <filter-mapping>
    	<filter-name>i18n</filter-name>
    	<url-pattern>/*</url-pattern>
    </filter-mapping>

That's it.

### Changing the language

I18n does not attempt to discover which language to use. You must inform it on one of the following ways:

- A request parameter named i18n\_lang
- A session attribute named i18n\_lang
- A cookie named i18n\_lang

This means that if you set the language on session and I18n finds a request parameter 
i18n\_lang, the language of the request will be used.
