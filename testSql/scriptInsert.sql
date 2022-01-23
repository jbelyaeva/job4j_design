insert into role (role) values ('администратор'),('менеджер'),('рекрутер'),('клиент');
insert into rule (rule) values ('просмотр'),('редактирование');
insert into user (name, sername, role_id) values ('Иван', 'Иванов', 4),('Сергей', 'Сергеев', 4);
insert into role_rule (role_id, rule_id) values (1,1), (1,2), (2,1);
insert into comment (comment) values ('позвонить клиенту'), ('занести в черный список');
insert into category (category) values ('звонок'), ('подтверждение заказа');
insert into state (state) values ('открыта'), ('закрыта');
insert into item (description, user_id, category_id, state_id ) values ('Заказ1', 1, 1, 1), ('Заказ2', 2, 2, 2);
insert into attach (path, item_id) values ('./client/client1.txt', 1), ('./client/client2.txt', 2);