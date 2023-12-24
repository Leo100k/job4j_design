insert into users(name) values('Иванов');
insert into users(name) values('Быстрова');

insert into roles(name, user_id) values('Первый пилот', 9);
insert into roles(name, user_id) values('Стюардесса', 10);

insert into rules(name) values('Приносить кофе');
insert into rules(name) values('Закрывать дверь');
insert into rules(name) values('Спать за штурвалом');
insert into rules(name) values('Орать на пассажиров');

insert into roles_rules(roles_id, rules_id) values(6, 7);
insert into roles_rules(roles_id, rules_id) values(6, 8);
insert into roles_rules(roles_id, rules_id) values(7, 5);
insert into roles_rules(roles_id, rules_id) values(7, 6);
insert into roles_rules(roles_id, rules_id) values(7, 8);

insert into items(name, user_id) values('заявка на полёт', 9);
insert into items(name, user_id) values('заявка на кофе', 10);

insert into comments (name, item_id) values('по расписанию', 3);
insert into comments (name, item_id) values('горячий', 4);

insert into attachs (name, item_id) values('файл с картой', 3);
insert into attachs (name, item_id) values('файл с кофе', 4);

insert into categories (name, item_id) values('категория 1', 3);
insert into categories (name, item_id) values('категория 2', 4);

insert into states (name, item_id) values('отменён', 3);
insert into states (name, item_id) values('выполнен', 4);





