create table machine (
 id integer not null auto_increment primary key,
 name varchar(100) not null
)engine=innodb;

create table resource_type (
  id integer not null auto_increment primary key,
  name varchar(40) not null;
)engine = innodb;

create table cpu_monitoring (
  id integer not null auto_increment primary key,
  machine_id integer not null references machine (id),
  cpu_id integer not null,
  date timestamp not null,
  combined double not null,
  user double not null,
  system double not null,
  nice double not null,
  wait double not null,
  idle double not null,
  temperature double,
  frequency long not null
)engine = innodb;

create table io_stat(
  id integer not null auto_increment primary key,
  machine_id integer not null references machine (id),
  device varchar(60) not null
)engine = innodb;

create table io_stat_property(
  id integer not null auto_increment primary key,
  io_stat_id integer not null references io_stat (id),
  name varchar(30) not null,
  value double not null,
  timestamp_stat timestamp not null
)engine = innodb;