<center>

## Предпросмотр выполненного ТЗ:

### 1. Главная. Первый вход:
<img src="https://github.com/user-attachments/assets/c93ece66-91e7-4076-aa6c-cc341d754b3d" width="300"/> Фото 1. Главный фрагмент

-На главном экране были добавлены все хардкод элементы согласно ТЗ.  
-В поле "Откуда" автоматически добавляется последнее заполненное место.  
-В поле "Музыкально отлететь" парсятся данные из прикрепленного API с использованием retrofit.  
-Дизайн нижней навигационной панели согласно ТЗ.  
-Пункты нижней навигационной панели: "Отели", "Короче", "Подписки", "Профиль" ведут на соответствующие заглушки

### 2. Поиск
<img src="https://github.com/user-attachments/assets/e00e27fb-7a2d-4abc-ae90-2aa69ad609b8" width="300"/> Фото 2. Панель поиска  

-При нажатии на поле "Куда" появляется нижняя панель с панелью поиска.  
-При вводе места прибытия появляются иконки поиска и очистки поля.  
-Весь хардкод и дизайн оформлен согласно ТЗ.  
-При нажатии на кнопку "Сложный маршурт", "Выходные", "горячие билеты" открываются соответствующие заглушки с возможностью вернуться назад.  
-Иконки популярных направлений кликабельны, при нажатии соответствующее название подставляется в поле "Куда".

### 3. Поиск. Выбрана страна
<img src="https://github.com/user-attachments/assets/f12e4a8f-fd1f-461a-878a-0dab30427bf8" width="300"/>Фото 3. Фрагмент поиска при выборе страны  


<img src="https://github.com/user-attachments/assets/bb548094-512f-4a96-93ae-d00956d8b12f" width="300"/> Фото 4. Фильтры для поиска


-Весь хардкод и дизайн оформлены согласно ТЗ.  
-Данные для полей "Откуда" и "Куда" берутся из предыдущего фрагмента.  
-Кнопка меняющая место отправления и прибытия и кнопка очистки работают соответствующе названию.  
-Панель с кнопками перемещается скроллом.  
-При нажатии на кнопку выбора времени возвращения отображается DatePicker, при выборе даты она отображается на месте текста "обратно".  
-При нажатии на кнопку выбора даты прибытия отображается DatePicker, при выборе даты она отображается внутри поля. Если дата не выбрана, то остается текущая дата.  
-При нажатии на кнопку "Фильтры" открывается окно с фильтрами, элементы согласно ТЗ, но нефункциональны(см. Фото 4).  
-Данные для поля "Прямые рейсы" парсятся из приложенного API.  
-При нажатии на кнопку "Посмотреть все билеты" осуществляется переход на фрагмент "Все билеты"(см. пункт 4).  
-Кнопка "Подписка на цену" нефункциональна.

### 4. Экран "Все билеты"
<img src="https://github.com/user-attachments/assets/8c2defdd-807c-47f4-9ade-0e3934b14b13" width="300"/>

-Весь дизайн и хардкод оформлен согласно ТЗ.  
-Кнопка "Назад" возвращает на предыдущий фрагмент.  
-Данные для полей "Откуда", "Куда", а также дата прибытия берутся с фрагмента "Поиск. Выбрана страна"(см. Пункт 3).  
-Просмотр списка билетов осуществляется скроллом.  
-Данные для каждого билета берутся из приложенного API.  
-Бейджы соответственно отображаются при наличии.  
-Кнопки "Фильтр" и "График цен" нефункциональны.


### 5. Дополнительно
<img src="https://github.com/user-attachments/assets/67cc4496-c9e6-4551-9a89-2fdfaaf4bf46" width="300"/>

Дополнительно была добавлена иконка загрузки для данных, берущихся из API всех билетов.
</center>
