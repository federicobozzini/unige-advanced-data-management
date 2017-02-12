DROP KEYSPACE IF EXISTS sales ;

CREATE KEYSPACE sales WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE sales;

CREATE TABLE currencies (
  fromcurrencyname text,
  tocurrencyname text,
  date timestamp,
  averagerate float,
  PRIMARY KEY ((fromcurrencyname, tocurrencyname), date)
);

CREATE TABLE salespersons (
  id int PRIMARY KEY,
  saleslastyear float,
  customerscount int
);

CREATE TABLE territorysales(
  territoryname text,
  productid int,
  quantity int,
  PRIMARY KEY(territoryname, productid)
);

CREATE TABLE territoryorders(
  territoryname text,
  salesorderid int,
  PRIMARY KEY(territoryname, salesorderid)
);