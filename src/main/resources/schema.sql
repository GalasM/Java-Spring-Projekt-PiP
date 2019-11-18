drop table if exists footballer;
drop table if exists news;

create table footballer
(
   id varchar(255) not null,
   imie varchar(255) not null,
   nazwisko varchar(255) not null,
   pozycja varchar(255) not null,
   primary key(id)
);


create table news
(
  id integer not null,
  tytul varchar(255) not null,
  tresc varchar(255) not null,
  primary key(id)
);