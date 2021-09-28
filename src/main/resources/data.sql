insert into address_entity (id, name) values (null, 'address1');
insert into address_entity (id, name) values (null, 'address2');
insert into address_entity (id, name) values (null, 'address3');
insert into person_entity (id, address_id, date_of_birth, person_name, person_nickname) values (null, 1, '1996-01-04', 'person1', 'per3');

insert into person_entity (id, address_id, date_of_birth, person_name) values (null, 2, '1999-01-04', 'person2');
insert into person_entity (id, address_id, date_of_birth, person_name) values (null, 2, '1998-01-04', 'person2.1');

insert into person_entity (id, address_id, date_of_birth, person_name, person_nickname) values (null, 1, '1998-01-04', 'person3', 'per3');

insert into person_entity (id, address_id, date_of_birth, person_name) values (null, 3, '1997-02-20', 'person4');
insert into person_entity (id, address_id, date_of_birth, person_name, person_nickname) values (null, 3, '2000-01-04', 'person5', 'per5');
insert into person_entity (id, address_id, date_of_birth, person_name) values (null, 3, '2002-02-20', 'person6');
