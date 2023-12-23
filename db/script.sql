create table cars(id serial primary key, name varchar, area text);
insert into cars(name, area) values('Skoda', 'Чехия');
select * from cars;
update cars set name = 'KAMAZ' where id = 2;
delete from cars where id = 3;