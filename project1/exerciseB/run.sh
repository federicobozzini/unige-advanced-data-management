#!/bin/bash

#ex B
hdfs dfs -rm -r wordcount/output
javac -cp .:$(hadoop classpath) exerciseB/Driver.java 
jar cf WordCount.jar exerciseB
hadoop jar WordCount.jar exerciseB/Driver 2 wordcount/input wordcount/output
hdfs dfs -cat wordcount/output/*