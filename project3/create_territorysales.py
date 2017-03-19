import csv
import os

def check_data_folder(path):
  if not os.path.exists(path):
      try:
          os.makedirs(path)
      except OSError as exc: # Guard against race condition
          if exc.errno != errno.EEXIST:
              raise
  
def get_territory_by_id(territorysales_tmp, t_id):
  return filter(lambda t: t[0] == t_id, territorysales_tmp)[0] 
  
def get_territories_by_sales_id(territorysales_tmp, s_id):
  return filter(lambda t: s_id in t[2], territorysales_tmp)
    
with open('DBData/SalesTerritory.csv', 'rb') as sales_territories_file:
  with open('DBData/SalesOrderHeader.csv', 'rb') as sales_headers_file:
    with open('DBData/SalesOrderDetail.csv', 'rb') as sales_details_file:
      territories = csv.reader(sales_territories_file, delimiter='\t', quotechar='|')
      s_headers = csv.reader(sales_headers_file, delimiter='\t', quotechar='|')
      s_details = csv.reader(sales_details_file, delimiter='\t', quotechar='|')
      #territory_id, territory_name, header_ids, map of products and quantity
      territorysales_tmp = map(lambda t: [t[0], t[1], set(), {}], territories)
      for s in s_headers:
        t_id = s[12]
        t = get_territory_by_id(territorysales_tmp, t_id)
        s_id = s[0]
        t[2].add(s_id)
      for d in s_details:
        s_id = d[0]
        ts = get_territories_by_sales_id(territorysales_tmp, s_id) 
        product_id = d[4]
        quantity = int(d[3])
        for t in ts:  
          if (product_id in t[3]):
            t[3][product_id] += quantity
          else:
            t[3][product_id] = quantity
      
#print map(lambda ts: [ts[0], ts[1], ts[3]], territorysales_tmp)
territorysales = []
for ts in territorysales_tmp:
  for product_id, quantity in ts[3].iteritems():
    territory_sale = [ts[1], product_id, quantity]
    territorysales.append(territory_sale)

check_data_folder('Data')
with open('Data/territorysales.csv', 'wb') as csvfile:
  datawriter = csv.writer(csvfile, delimiter='|', quoting=csv.QUOTE_NONE)
  for ts in territorysales:
    datawriter.writerow(ts)