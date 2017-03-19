import csv
import os

def check_data_folder(path):
  if not os.path.exists(path):
      try:
          os.makedirs(path)
      except OSError as exc: # Guard against race condition
          if exc.errno != errno.EEXIST:
              raise
  
def get_person_by_id(people_tmp, p_id):
  return filter(lambda p: p[0] == p_id, people_tmp)[0] 
  
def get_persons_by_territory_id(people_tmp, t_id):
  return filter(lambda p: t_id in p[2], people_tmp)
    
with open('DBData/SalesPerson.csv', 'rb') as sales_person_file:
  with open('DBData/SalesTerritoryHistory.csv', 'rb') as sales_territory_history_file:
    with open('DBData/Customer.csv', 'rb') as customer_file:
      sales_persons = csv.reader(sales_person_file, delimiter='\t', quotechar='|')
      history_rows = csv.reader(sales_territory_history_file, delimiter='\t', quotechar='|')
      customers = csv.reader(customer_file, delimiter='\t', quotechar='|')
      people_tmp = map(lambda p: [p[0], p[6], set(), set()], sales_persons)
      for r in history_rows:
        p = get_person_by_id(people_tmp, r[0])
        territory_id = r[1]
        p[2].add(territory_id)
      for c in customers:
        customer_id = c[0]
        t_id = c[3]
        persons = get_persons_by_territory_id(people_tmp, t_id)        
        for p in persons:          
          p[3].add(customer_id)
      
people = map(lambda p: [p[0], p[1], len(p[3])], people_tmp)

check_data_folder('Data')
with open('Data/salespersons.csv', 'wb') as csvfile:
  datawriter = csv.writer(csvfile, delimiter='|', quoting=csv.QUOTE_NONE)
  for p in people:
    datawriter.writerow(p)