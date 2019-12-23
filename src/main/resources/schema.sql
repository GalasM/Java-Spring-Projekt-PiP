drop table if exists footballer;
drop table if exists news;

create table footballer
(
   id varchar(255) not null,
   imie varchar(255) not null,
   nazwisko varchar(255) not null,
   pozycja varchar(255) not null,
   status varchar(255) not null,
   primary key(id)
);


create table news
(
  id integer not null,
  tytul varchar(255) not null,
  tresc varchar(255) not null,
  primary key(id)
);

create table event
(
  id varchar(255) not null primary key,
  title varchar(255) not null,
  start varchar(255) not null,
  end varchar(255),
  type varchar(255)
);

create table extendProps
(
  id varchar(255) not null,
  name varchar(255),
  value varchar(255),
  eventId varchar(255)
);

create table trainingBefore
(
  id varchar(255) not null,
  idMatch varchar(255),
  constraint fk
  foreign key(idMatch)
  references event(id)
  on delete cascade
);