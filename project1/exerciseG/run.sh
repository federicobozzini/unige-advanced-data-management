#!/bin/bash

#ex G
hdfs dfs -rm -r wordcount/output
javac -cp .:$(hadoop classpath) exerciseG/Driver.java 
jar cf WordCount.jar exerciseG
hadoop jar WordCount.jar exerciseG/Driver wordcount/output
hdfs dfs -cat wordcount/output/*