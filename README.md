О проекте
Этот проект является учебным. Представляет собой простой аналог JMS.

Приложение запускает Socket и ждет клиентов.
Клиенты могут быть двух типов: отправители (publisher), получатели (subscriber).
В качестве клиента используется cURL. https://curl.se/download.html
В качестве протокола используется HTTP.
Pooh будет иметь два режима: 
1) "topic" для каждого потребителя  будет своя уникальная очередь с данными
2) "queue" все потребители получают данные из одной и той же очереди.