#!/bin/bash

#ex D
hdfs dfs -rm -r wordcount/wordtrans
javac -cp .:$(hadoop classpath) exerciseDwordTrans/Driver.java
jar cf WordCount.jar exerciseDwordTrans
hadoop jar WordCount.jar exerciseDwordTrans/Driver 2 wordcount/input wordcount/wordtrans
#hdfs dfs -cat wordcount/wordtrans/*