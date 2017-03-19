#/bin/bash!

hdfs dfs -rm -r -f wordcount/pig_ex_f

pig exerciseF.pig

hdfs dfs -cat wordcount/pig_ex_f/*