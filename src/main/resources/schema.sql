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

create table team
(
  id varchar(255) not null,
  name varchar(255) not null,
  formation varchar(5),
  primary key(id)
);

create table teamFootballer
(
  id varchar(255) not null,
  idTeam varchar(255) ,
  idFootballer varchar(255) not null,
  status varchar(2),
  primary key(id),
  constraint fkTeam foreign key(idTeam) references team(id) on delete cascade,
  constraint fkFootballer foreign key(idFootballer) references footballer(id) on delete cascade
);


create table news
(
  id varchar(255) not null,
  tytul varchar(255),
  tresc varchar(255),
  data varchar(255),
  primary key(id)
);

create table event
(
  id varchar(255) not null primary key,
  title varchar(255) not null,
  start varchar(255) not null,
  end varchar(255),
  type varchar(255),
  sklad varchar(255)
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
  constraint fk foreign key(idMatch) references event(id) on delete cascade
);