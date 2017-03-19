#!/bin/bash

#ex H
hdfs dfs -rm -r wordcount/output
javac -cp .:$(hadoop classpath) exerciseH/Driver.java
jar cf WordCount.jar exerciseH
hadoop jar WordCount.jar exerciseH/Driver 2 wordcount/wordfreq  wordcount/wordtrans wordcount/output
hdfs dfs -cat wordcount/output/*