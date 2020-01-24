use people;

/* drop table `people`.`employees`; */
/* drop table `people`.`users`; */
/* drop table `people`.`products`; */
/* drop table `people`.`roles`; */
create TABLE IF NOT EXISTS `people`.`roles`(
  id int primary key not null auto_increment,
  code varchar (10) not null,
  description varchar(25) not null);
show columns from roles;


create table IF NOT EXISTS `people`.`employees` (
  id int primary key not null auto_increment,
  name varchar(20) not null,
  lastname varchar(30) not null,
  age int not null,
  role_id int,
  foreign key(role_id) references roles(id));
show columns from employees;


CREATE TABLE IF NOT EXISTS `people`.`users` (
  id int primary key not null auto_increment,
  login VARCHAR(20) NOT NULL,
  password VARCHAR(20) NOT NULL,
  role_id int,
  foreign key(role_id) references roles(id));
show columns from users;


CREATE TABLE IF NOT EXISTS `people`.`products` (
  id int primary key not null auto_increment,
  type VARCHAR(1) NOT NULL,
  name VARCHAR(30) NOT NULL,
  price DECIMAL(8,2) NOT NULL,
  weight DECIMAL(8,2) NOT NULL,
  count DECIMAL(8,2) NOT NULL,
  color VARCHAR(10) NOT NULL,
  material VARCHAR(20) default '' NOT NULL,
  sizeStr VARCHAR(5) default '' NOT NULL,
  sizeNum int default 0 NOT NULL 
  );
show columns from products;

select * from users;
select * from products;