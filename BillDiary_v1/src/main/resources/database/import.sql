--create table if not exists user2(userid int,username varchar(20),password varchar(20),role varchar(20),primary key(userid));
--insert into user2 values(1,'Gajanan','Gajanan','admin');
--insert into user2 values(2,'Gajanan','Gajanan','admin');
create table if not exists user(id int, username varchar(20),password varchar(20),role varchar(20),primary key(id));
--insert into user values(1,'Gajanan','Gajanan','admin');
create table if not exists product(id int,name varchar(20),description varchar(20),wholesale_price double,retail_price double,discount double,stock int);

insert into product values(101,'Mobile','Apple',30000,26500.50,0,5);
