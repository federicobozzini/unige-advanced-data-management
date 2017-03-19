#/bin/bash!

hdfs dfs -rm -r -f wordcount/pig_output

pig exercise0.pig

hdfs dfs -cat wordcount/pig_output/*