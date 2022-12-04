insert ignore into user_statuses(status_id, status_name)
VALUES (1, 'Администратор');
insert ignore into user_statuses(status_id, status_name)
VALUES (2, 'Пользователь');

insert ignore into users(user_id, avatar_number, banned, email, first_name, last_name, password, username, status_id)
VALUES (-2, 1, false, 'admin@mail.ru', 'Admin', 'admin', 'TK69Rwról�Lw', 'admin_acc', 1);
#2020admin (pass)


/** взято из головы**/
insert ignore into departments(department_id, department_name)
VALUES (-1, ' Инженерно - экономический факультет ');
insert ignore into departments(department_id, department_name)
VALUES (-2, ' Факультет радиотехники и электроники ');

/** взято из головы**/
insert ignore into ranks(rank_id, increasing_coefficient, rank_name)
VALUES (-1, 1.5, 'Профессор');

insert ignore into ranks(rank_id, increasing_coefficient, rank_name)
VALUES (-2, 0.5, 'Ассистент');


insert ignore into ranks(rank_id, increasing_coefficient, rank_name)
VALUES (-2, 1, 'Старший преподаватель');


