#!/bin/bash

#ex F
hdfs dfs -rm -r wordcount/output
javac -cp .:$(hadoop classpath) exerciseF/Driver.java
jar cf WordCount.jar exerciseF
hadoop jar WordCount.jar exerciseF/Driver wordcount/output
hdfs dfs -cat wordcount/output/*