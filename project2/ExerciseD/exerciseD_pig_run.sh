#/bin/bash!

hdfs dfs -rm -r -f wordcount/pig_ex_d

pig exerciseD.pig

hdfs dfs -cat wordcount/pig_ex_d/*