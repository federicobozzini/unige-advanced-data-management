#!/bin/bash

#ex D
hdfs dfs -rm -r wordcount/wordfreq
javac -cp .:$(hadoop classpath) exerciseDwordFreq/Driver.java
jar cf WordCount.jar exerciseDwordFreq
hadoop jar WordCount.jar exerciseDwordFreq/Driver 2 wordcount/input wordcount/wordfreq
#hdfs dfs -cat wordcount/wordfreq/*