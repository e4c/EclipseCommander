EclipseCommander
================

> This is a Russian version, see [README.md][4] for English.


Описание
-------

Исходные коды [файлового менеджера][8] под названием _Eclipse Commander_, построенного на платформе Eclipse [e4][9].


Цели создания
--------

 * Создать файловый менеджер на платформе e4 (в прошлом RCP).
 * Применить знания Java NIO.2, полученные при подготовке к [OCAJP7][6].
 * Применить знания [NatTable][7].


Функциональные возможности
--------

 * использование разных стилей выдления.
 

Структура плагинов
--------

- [cane.brothers.e4.commander][1] - основной плагин приложения
- [cane.brothers.e4.target][2] - целевая платформа


Используемые сторонние плагины
--------

- [e4Preferences][5] - extention point to manage e4 preferences in preference dialog like in RCP.
- [Nebula NatTable][7] - высокоэффективная, высокопроизводительная и, в тоже время, гибкая таблица данных на SWT с динамичным дизайном, предназначенная для обработки больших наборов данных в реальном времени.


Обратная связь
--------

Мне ценно Ваше мнение! 

Вы можете поделитесь своими идеями, задать вопрос или сообщить об ошибке одним из способов:

- [e-mail](mailto:webcane@ya.ru?subject=EclipseCommander)
- [issue](https://github.com/webcane/EclipseCommander/issues/new)


Лицензия
-------

Разрабатывается под лицензией [Eclipse Public License (EPL) v1.0][3]

[1]: cane.brothers.e4.commander
[2]: cane.brothers.e4.target
[3]: LICENSE
[4]: README.md
[5]: https://github.com/opcoach/e4Preferences
[6]: http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-804
[7]: http://www.eclipse.org/nattable
[8]: http://ru.wikipedia.org/wiki/%D0%A4%D0%B0%D0%B9%D0%BB%D0%BE%D0%B2%D1%8B%D0%B9_%D0%BC%D0%B5%D0%BD%D0%B5%D0%B4%D0%B6%D0%B5%D1%80
[9]: http://www.eclipse.org/e4/
