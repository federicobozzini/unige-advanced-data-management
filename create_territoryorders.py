import csv
import os

def check_data_folder(path):
  if not os.path.exists(path):
      try:
          os.makedirs(path)
      except OSError as exc: # Guard against race condition
          if exc.errno != errno.EEXIST:
              raise
    
def get_territory_by_id(territoryorders_tmp, t_id):
  return filter(lambda t: t[0] == t_id, territoryorders_tmp)[0] 
    
with open('DBData/SalesTerritory.csv', 'rb') as sales_territories_file:
  with open('DBData/SalesOrderHeader.csv', 'rb') as sales_headers_file:
    territories = csv.reader(sales_territories_file, delimiter='\t', quotechar='|')
    s_headers = csv.reader(sales_headers_file, delimiter='\t', quotechar='|')
    #territory_id, territory_name, header count
    territoryorders_tmp = map(lambda t: [t[0], t[1], []], territories)
    for s in s_headers:
      t_id = s[12]
      t = get_territory_by_id(territoryorders_tmp, t_id)
      s_id = s[0]
      t[2].append(s[0])
      
territoryorders = []
for to in territoryorders_tmp:
  for sale_id in to[2]:
    territoryorders.append([to[1], sale_id])
    
check_data_folder('Data')    
with open('Data/territoryorders.csv', 'wb') as csvfile:
  datawriter = csv.writer(csvfile, delimiter='|', quoting=csv.QUOTE_NONE)
  for to in territoryorders:
    datawriter.writerow(to)