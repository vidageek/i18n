---
title: i18n
---

This project was created after I had to use JSTL/fmt for a while. Usually, there is no problem using

    <fmt:message key="your.key.value" />

But what happens when you need your button value to be internationalized?

    <input type="submit" value="<fmt:message key="your.key.value" />" />

It gets really ugly and hard to read. Why? Because you opened a tag inside another tag.

I really like Expression Language, so I decided to turn that code above into

    <input type="submit" value="${i18n.your.key.value}" />

And that's it. All you need to do is add __i18n.__ before your key value on a expression language.

Ow! I almost forgot. Take a look at how to configure it to work this way (actually, you'll just 
need to copy it's configuration on your web.xml)
