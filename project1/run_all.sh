#!/bin/bash

#ex A
hdfs dfs -rm -r wordcount/output
javac -cp .:$(hadoop classpath) exerciseA/Driver.java && jar cf WordCount.jar exerciseA && hadoop jar WordCount.jar exerciseA/Driver 2 wordcount/input wordcount/output
hdfs dfs -cat wordcount/output

#ex B
hdfs dfs -rm -r wordcount/output
javac -cp .:$(hadoop classpath) exerciseB/Driver.java && jar cf WordCount.jar exerciseB && hadoop jar WordCount.jar exerciseB/Driver 2 wordcount/input wordcount/output
hdfs dfs -cat wordcount/output

#ex C
hdfs dfs -rm -r wordcount/wordtrans
javac -cp .:$(hadoop classpath) exerciseDwordTrans/Driver.java && jar cf WordCount.jar exerciseDwordTrans && hadoop jar WordCount.jar exerciseDwordTrans/Driver 2 wordcount/input wordcount/wordtrans
#hdfs dfs -cat wordcount/wordtrans/*

#ex D
hdfs dfs -rm -r wordcount/wordfreq
javac -cp .:$(hadoop classpath) exerciseDwordFreq/Driver.java && jar cf WordCount.jar exerciseDwordFreq && hadoop jar WordCount.jar exerciseDwordFreq/Driver 2 wordcount/input wordcount/wordfreq
#hdfs dfs -cat wordcount/wordfreq/*

#ex E
hdfs dfs -rm -r wordcount/output
javac -cp .:$(hadoop classpath) exerciseE/Driver.java && jar cf WordCount.jar exerciseE && hadoop jar WordCount.jar exerciseE/Driver wordcount/output
hdfs dfs -cat wordcount/output/*

#ex F
hdfs dfs -rm -r wordcount/output
javac -cp .:$(hadoop classpath) exerciseF/Driver.java && jar cf WordCount.jar exerciseF && hadoop jar WordCount.jar exerciseF/Driver wordcount/output
hdfs dfs -cat wordcount/output/*

#ex G
hdfs dfs -rm -r wordcount/output
javac -cp .:$(hadoop classpath) exerciseG/Driver.java && jar cf WordCount.jar exerciseG && hadoop jar WordCount.jar exerciseG/Driver wordcount/output
hdfs dfs -cat wordcount/output/*

#ex H
hdfs dfs -rm -r wordcount/output
javac -cp .:$(hadoop classpath) exerciseH/Driver.java && jar cf WordCount.jar exerciseH && hadoop jar WordCount.jar exerciseH/Driver 2 wordcount/wordfreq  wordcount/wordtrans wordcount/output
hdfs dfs -cat wordcount/output/*
