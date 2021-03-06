create schema Netflix_app;

set search_path to Netflix_app;

create table subscription_roles (
    id int generated always as identity primary key,
    role_Name varchar unique not null
);

create table user_roles (
    id int generated always as identity primary key,
    user_category varchar not null
);

create table netflix_user (
    netflix_id int generated always as identity primary key,
    name varchar unique not null,
    age int not null,
    user_id int,
    constraint fk_user
    foreign key (user_id)
    references user_roles(id)
    );

create table netflix_acc (
    id int generated always as  identity primary key,
    FirstName varchar not null,
    LastName varchar not null,
    email varchar unique not null,
    username varchar unique not null check(length(username) >= 5),
    password varchar not null check(length(password) >= 8),
    acc_id int,
    constraint fk_acc
    foreign key (acc_id)
    references subscription_roles(id)
);
