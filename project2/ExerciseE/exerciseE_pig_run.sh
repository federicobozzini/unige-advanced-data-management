#/bin/bash!

hdfs dfs -rm -r -f wordcount/pig_ex_e

pig exerciseE.pig

hdfs dfs -cat wordcount/pig_ex_e/*