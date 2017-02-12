import csv
import os

def check_data_folder(path):
  if not os.path.exists(path):
      try:
          os.makedirs(path)
      except OSError as exc: # Guard against race condition
          if exc.errno != errno.EEXIST:
              raise

with open('DBData/CurrencyRate.csv', 'rb') as old_currency_file:
  rows = csv.reader(old_currency_file, delimiter='\t', quotechar='|')
  currency_values = []
  for row in rows:
    fromcurrency = row[2]
    tocurrency = row[3]
    date = row[1].split(' ')[0]
    value = row[4]
    v = [fromcurrency, tocurrency, date, value]
    currency_values.append(v)

check_data_folder('Data')
with open('Data/currencies.csv', 'wb') as new_currency_file:
  datawriter = csv.writer(new_currency_file, delimiter='|', quoting=csv.QUOTE_NONE)
  for c in currency_values:
    datawriter.writerow(c)
   