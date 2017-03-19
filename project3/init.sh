#!/bin/bash

python create_salespersons.py
python create_currencies.py
python create_territoryorders.py
python create_territorysales.py

cqlsh < schema.cql
cqlsh < load.cql