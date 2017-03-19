USE sales;

COPY sales.territoryorders (territoryname, salesorderid) FROM 'Data/territoryorders.csv' WITH DELIMITER='|' AND HEADER=FALSE ;
COPY sales.territorysales (territoryname, productid, quantity) FROM 'Data/territorysales.csv' WITH DELIMITER='|' AND HEADER=FALSE ;
COPY sales.salespersons (id, saleslastyear, customerscount) FROM 'Data/salespersons.csv' WITH DELIMITER='|' AND HEADER=FALSE ;
COPY sales.currencies (fromcurrencyname, tocurrencyname, date, averagerate) FROM 'Data/currencies.csv' WITH DELIMITER='|' AND HEADER=FALSE ;