# trello-project
Проект для практического изучения Spring и Vue. Основная идея - реализовать функциональность сайта [Trello](https://trello.com/ru), в основе которого лежит метод управления проектами канбан.

## Описание
Данный репозиторий представляет собой серверную часть проекта. Также вы можете ознакомиться с [клиентской частью](https://github.com/Igor-Larin/trello-project-vue).

## Используемые технологии
- Spring MVC для обработки клиентских запросов;
- Spring Data JPA для связи с БД, в качестве поставщика сохраняемости был использован Hibernate;
- PostgreSQL в качестве СУБД.

## Реализованные функции
- Создание новых объектов(досок/карточек/задач) в БД;
- Получение объектов из БД, с последующей отправкой клиенту в виде JSON;
- Изменение объектов в БД;
- Удаление объектов в БД;

## Что нужно сделать
1. С помощью Spring Security и JWT реализовать аутентификацию и авторизацию;
2. Добавить возможность добавлять теги к доскам/карточкам;
3. Добавить колонки для задач.
