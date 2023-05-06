# BookKeeperSpringDataJPA

Вся логика старого проекта из https://github.com/Phaser2028/BookKeeperJavaSpring перенесена на SpringDataJPA

## Добавлена пагинация для страницы с книгами

### простой запрос ```/books```

![image](https://user-images.githubusercontent.com/43641188/236622093-6fb9adf5-79d1-4aee-8483-ce32261acd66.png)

запрос с параметрами ```/books?page=1&books_per_page=3```

![image](https://user-images.githubusercontent.com/43641188/236622165-ef3cf53b-b798-415f-9dd0-5572875382b3.png)

# Добавлена страница для поиска книги по первым буквам названия

при запросе ```/book/search```

![image](https://user-images.githubusercontent.com/43641188/236622240-7ced8363-7c67-40ac-b1a3-e604b1a5699c.png)

при попытке найти свободную книгу с запросом ```Чистый```: 

![image](https://user-images.githubusercontent.com/43641188/236622522-b165c602-3763-4e11-8469-43e6dd87bdb2.png)

при попытке найти занятую книгу с запросом ```Цветы```:

![image](https://user-images.githubusercontent.com/43641188/236623139-145107a6-9798-4be9-a85a-8d8e29cd9718.png)


при попытке найти книгу которая отсутствует в библиотеке с запросом ```Фауст```:
![image](https://user-images.githubusercontent.com/43641188/236622764-a72020f1-c372-464c-982f-bab89bb90378.png)

## Добавлена проверка на просрочку

книга просроченная на 10 дней будет будет отображаться красным цветом на странице пользователя
![image](https://user-images.githubusercontent.com/43641188/236623012-1e38708e-4f45-4070-9919-53b23aae6452.png)




