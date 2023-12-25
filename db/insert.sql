insert into users(name) values('Иванов');
insert into users(name) values('Быстрова');

insert into roles(name, user_id) values('Первый пилот', 1);
insert into roles(name, user_id) values('Стюардесса', 2);

insert into rules(name) values('Приносить кофе');
insert into rules(name) values('Закрывать дверь');
insert into rules(name) values('Спать за штурвалом');
insert into rules(name) values('Орать на пассажиров');

insert into roles_rules(roles_id, rules_id) values(1, 3);
insert into roles_rules(roles_id, rules_id) values(1, 4);
insert into roles_rules(roles_id, rules_id) values(2, 1);
insert into roles_rules(roles_id, rules_id) values(2, 2);
insert into roles_rules(roles_id, rules_id) values(2, 4);


insert into categories (name) values('категория 1');
insert into categories (name) values('категория 2');

insert into states (name) values('отменён');
insert into states (name) values('выполнен');

insert into items(name, user_id, category_id, state_id) values('заявка на полёт', 1,2,1);
insert into items(name, user_id, category_id, state_id) values('заявка на кофе', 2,2,2);

insert into comments (name, item_id) values('по расписанию', 1);
insert into comments (name, item_id) values('горячий', 2);

insert into attachs (name, item_id) values('файл с картой', 1);
insert into attachs (name, item_id) values('файл с кофе', 2);






