#!/bin/bash

#ex C
hdfs dfs -rm -r wordcount/output
javac -cp .:$(hadoop classpath) exerciseC/Driver.java 
jar cf WordCount.jar exerciseC
hadoop jar WordCount.jar exerciseC/Driver 2 wordcount/input wordcount/output
hdfs dfs -cat wordcount/output/*