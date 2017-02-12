USE sales;

--Q1
SELECT fromcurrencyname, tocurrencyname, date, averagerate
FROM currencies 
WHERE fromcurrencyname = 'USD' 
AND tocurrencyname = 'EUR' 
AND date >= '2014-04-01' 
AND date < '2014-05-01';

--Q2
SELECT fromcurrencyname, tocurrencyname, AVG(averagerate)
FROM currencies 
WHERE fromcurrencyname = 'USD' 
AND tocurrencyname = 'EUR' 
AND date >= '2014-04-01' 
AND date < '2014-05-01';

--Q3
SELECT SUM(saleslastyear)
FROM salespersons;
This requires an access to multiple partitions. Performances may be subobtimal.

--Q4
SELECT id, customerscount
FROM salespersons;

--Q5
SELECT territoryname, productid, quantity
FROM territorysales;

--Q6
SELECT territoryname, salesorderid
FROM territoryorders;

--Q7
SELECT territoryname, COUNT(salesorderid)
FROM territoryorders
WHERE territoryname = 'United Kingdom';
