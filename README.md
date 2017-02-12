#ADM PROJECT 3

##1.1 THE WORKLOAD:

We invented 7 queries. We tried to select different entities, different level of complexities and different types of queries and results (Aggregates, Listings, ...). We tried to invent queries that may simulate real-life needs for company reports.

q1: a report of the fluctuation of a currency (XXX) rate against the dollar in a specified time period.

  SELECT cr.fromcurrencycode,
    cr.tocurrencycode,
    cr.averagerate
  FROM	sales.currencyrate cr
  WHERE	cr.fromcurrencycode = 'USD'
  AND cr.tocurrencycode = 'XXX'
  AND cr.currencyratedate BETWEEN 'YYY' AND 'ZZZ'

q2: a report of the average rate of a currency (XXX) rate against the dollar in a specified time period.

  SELECT cr.fromcurrencycode,
    cr.tocurrencycode,
    AVG(cr.averagerate)
  FROM	sales.currencyrate cr
  WHERE	cr.fromcurrencycode = 'USD'
  AND cr.tocurrencycode = 'XXX'
  AND cr.currencyratedate BETWEEN 'YYY' AND 'ZZZ'
  GROUP BY cr.fromcurrencycode, cr.tocurrencycode
  
  
q3: calculates the total amount of sales made last year by all the sales people.

  SELECT	SUM(saleslastyear)
  FROM	sales.salesperson

  
q4: gives as result the number of customers that a sales person has come in contact through his career.

  SELECT	businessentityid,
    COUNT(distinct customerid) as customernum
  FROM	sales.salesterritoryhistory sth
  JOIN	sales.salesterritory st
  ON	sth.territoryid = st.territoryid
  JOIN	sales.customer c
  ON	c.territoryid = st.territoryid
  GROUP BY businessentityid
  
  
q5: describes for every territory the products that had been sold and in which quantity.

  SELECT	st.name,
    sod.productid,
    SUM(sod.orderqty)
  FROM	sales.salesterritory st
  JOIN	sales.salesorderheader soh
  ON	st.territoryid = soh.territoryid
  JOIN	sales.salesorderdetail sod
  ON	soh.salesorderid = sod.salesorderid
  GROUP BY st.territoryid, productid
  
  
q6: a report of the orders id for every territory.

  SELECT	st.name,
    soh.salesorderid
  FROM	sales.salesterritory st
  JOIN	sales.salesorderheader soh
  ON	st.territoryid = soh.territoryid
  
  
q7: is an aggregation of the sales for every territory.

  SELECT	st.name,
    COUNT(soh.salesorderid)
  FROM	sales.salesterritory st
  JOIN	sales.salesorderheader soh
  ON	st.territoryid = soh.territoryid
  WHERE st.name = 'United Kingdom'
  GROUP BY st.name

  

##1.2: LOGICAL DESIGN
For the schema, refer to the file schema.sql.

We tried to design the Cassandra logical schema to satisfy the requirements of the queries with the best efficiency. Sometimes we loaded more data than necessary to allow more flexibility and more detailed queries.

We need four tables: 
currencies to answer q1 and q2. 
salesperons to answer q3 and q4.
territoryorders to answer q5.
territorysales to answer q6 and q7.

##1.3: IMPLEMENTATION
We designed the column families to represent the logical schema.  We developed 4 python scripts to convert the data from the CSV source to the target CSV. Efficiency was not a main concern here. In a real world scenario our approach would have been to use a SQL (with the queries presented before) to extract the data. We prepared to CQL to present the correct results with the best possible efficiency.

##1.4: HYSICAL ORGANIZATION
We used the commands:
"nodetools cfstasts sales" as a shell command to get information about the memory and disk used to store the column families.
"tracing on" as a cqlsh command to get information about the queries run


##TIME ESTIMATE:

- 30min: Understanding the project requirements
- 2h: Familiarization with Cassandra, CQL and documentation reading
- 30min: Familiarization with the DB schema and CSV data
- 1h: Invention of the workload, preparation and testing (via postgres) of the queries.
- 2h, 30min: Preparation of the Cassandra schema and scripts to convert and load the data
- 30min: Preparation and testing of the CQL queries
- 1h: Documentation
- 30min: Polishing and wrapping up of the project files
