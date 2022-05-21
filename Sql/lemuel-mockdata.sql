set search_path to Netflix_app;

insert into netflix_acc (FirstName, LastName, email, password, role_id) values ('Lemuel', 'Thomas', 'lemuel.thomas3@gmail.com', 'demopassword1', 3);
insert into netflix_acc (FirstName, LastName, email, password, role_id) values ('Christopher', 'King', 'cking22@example.com', 'demopassword2', 2);
insert into netflix_acc (FirstName, LastName, email, password, role_id) values ('Michael', 'Mons', 'MichMons@yahoo.com', 'demopassword3', 1);

insert into netflix_user (Name, Age, role_id) values ('YoungerBrother', 15, 2);
insert into netflix_user (Name, Age, role_id) values ('OlderBrother', 30, 1);
insert into netflix_user (Name, Age, role_id) values ('Mom', 40, 1);
insert into netflix_user (Name, Age, role_id) values ('Dad', 43, 1);

insert into subscription_roles (role_Name) values ('basic_user', 'hd_user', 'ultrahd_user');
insert into user_roles (role_Name) values ('adult', 'child', 'teen');