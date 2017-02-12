1.1 THE WORKLOAD:

q1: a report of the fluctuation of a currency rate against the dollar.

  SELECT	cr.averagerate,
    cr.tocurrencycode
  FROM	sales.currencyrate cr
  WHERE	cr.fromcurrencycode = 'USD'
  
  
q2: calculates the total amount of sales made last year by all the sales people.

  SELECT	SUM(saleslastyear)
  FROM	sales.salesperson

  
q3: gives as result the number of customers that a sales person has come in contact through his career.

  SELECT	businessentityid,
    COUNT(distinct customerid) as customernum
  FROM	sales.salesterritoryhistory sth
  JOIN	sales.salesterritory st
  ON	sth.territoryid = st.territoryid
  JOIN	sales.customer c
  ON	c.territoryid = st.territoryid
  GROUP BY businessentityid
  
  
q4: describes for every territory the products that had been sold and in which quantity.

  SELECT	st.name,
    sod.productid,
    SUM(sod.orderqty)
  FROM	sales.salesterritory st
  JOIN	sales.salesorderheader soh
  ON	st.territoryid = soh.territoryid
  JOIN	sales.salesorderdetail sod
  ON	soh.salesorderid = sod.salesorderid
  GROUP BY st.territoryid, productid
  
  
q5: a report of the sales id for every territory.

  SELECT	st.name,
    soh.salesorderid
  FROM	sales.salesterritory st
  JOIN	sales.salesorderheader soh
  ON	st.territoryid = soh.territoryid
  
  
q6: is an aggregation of the sales for every territory.

  SELECT	st.name,
    COUNT(soh.salesorderid)
  FROM	sales.salesterritory st
  JOIN	sales.salesorderheader soh
  ON	st.territoryid = soh.territoryid
  WHERE st.name = 'United Kingdom'

  

1.2: LOGICAL DESIGN
For the schema, refer to the file schema.sql.


DESCRIPTIONS AND MOTIVATIONS:
1.1: We invented 6 queries. We tried to select different entities, different level of complexities and different types of queries and results (Aggregates, Listings, ...). We tried to invent queries that may simulate real-life needs for company reports.


1.2: We tried to design the Cassandra logical schema to satisfy the requirements of the queries at the best efficiency. Sometimes we loaded more data than necessary to allow more flexibility and more detailed queries.
We need four tables: 
Currencies to answer q1. 
Salesperons to answer q2 and q3.
territoryorders to answer q4.
territorysales to answer q5 and q6.

1.3: We designed the column families to represent the logical schema.  We developed 4 python scripts to convert the data from the CSV source to the target CSV. Efficiency was not a main concern here. In a real world scenario our approach would have been to use a SQL (with the queries presented before) to extract the data. We prepared to CQL to present the correct results with the best possible efficiency.

1.4: We used the commands:
"nodetools cfstasts sales" as a shell command to get information about the memory and disk used to store the column families.
"tracing on" as a cqlsh command to get information about the queries run


TIME ESTIMATE:

- 30min: Understanding the project requirements
- 2h: Familiarization with Cassandra, CQL and documentation reading
- 30min: Familiarization with the DB schema and CSV data
- 1h: Invention of the workload, preparation and testing (via postgres) of the queries.
- 2h, 30min: Preparation of the Cassandra schema and scripts to convert and load the data
- 30min: Preparation and testing of the CQL queries
- 1h: Documentation
- 30min: Polishing and wrapping up of the project files