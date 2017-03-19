#!/bin/bash

#ex A
hdfs dfs -rm -r wordcount/output
javac -cp .:$(hadoop classpath) exerciseA/Driver.java 
jar cf WordCount.jar exerciseA 
hadoop jar WordCount.jar exerciseA/Driver 2 wordcount/input wordcount/output
hdfs dfs -cat wordcount/output/*