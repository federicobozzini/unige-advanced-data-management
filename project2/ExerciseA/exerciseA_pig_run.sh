#/bin/bash!

hdfs dfs -rm -r -f wordcount/pig_wordtrans

pig exerciseA.pig

hdfs dfs -cat wordcount/pig_wordtrans/*