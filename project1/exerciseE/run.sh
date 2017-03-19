#!/bin/bash

#ex E
hdfs dfs -rm -r wordcount/output
javac -cp .:$(hadoop classpath) exerciseE/Driver.java
jar cf WordCount.jar exerciseE
hdfs dfs -rm -r wordcount/output || hadoop jar WordCount.jar exerciseE/Driver wordcount/output
hdfs dfs -cat wordcount/output/*