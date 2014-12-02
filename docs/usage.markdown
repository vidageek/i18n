---
title: Usage
---

Due to the way EL are compiled, you can use i18n on some different ways:

    ${i18n.your.message}

or

    ${i18n["your.message"]}

or even

    ${i18n.your["message"]}

The second way is quite useful when you need get a message for an error added to the JSP or that is inside a variable.
