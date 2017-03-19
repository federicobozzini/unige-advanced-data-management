#/bin/bash!

hdfs dfs -rm -r -f wordcount/pig_wordfreq

pig exerciseB.pig

hdfs dfs -cat wordcount/pig_wordfreq/*