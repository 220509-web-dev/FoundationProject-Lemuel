set search_path to Netflix_app;

insert into subscription_roles (role_Name) values ('basic_user');
insert into subscription_roles (role_Name) values ('hd_user');
insert into subscription_roles (role_Name) values ('ultrahd_user');
insert into user_roles (user_category) values ('adult');
insert into user_roles (user_category) values ('teen');
insert into user_roles (user_category) values ('child');

insert into netflix_acc (FirstName, LastName, email, username, password, acc_id) values ('Lemuel', 'Thomas', 'lemuel.thomas3@gmail.com', 'lemstry', 'demopassword1', 3);
insert into netflix_acc (FirstName, LastName, email, username, password, acc_id) values ('Christopher', 'King', 'cking22@example.com', 'ckingster' , 'demopassword2', 2);
insert into netflix_acc (FirstName, LastName, email, username, password, acc_id) values ('Michael', 'Mons', 'MichMons@yahoo.com', 'nmons12' ,'demopassword3', 1);

insert into netflix_user (Name, Age, user_id) values ('YoungerBrother', 15, 2);
insert into netflix_user (Name, Age, user_id) values ('OlderBrother', 30, 1);
insert into netflix_user (Name, Age, user_id) values ('Mom', 40, 1);
insert into netflix_user (Name, Age, user_id) values ('Dad', 43, 1);