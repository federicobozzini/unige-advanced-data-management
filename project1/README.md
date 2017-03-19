# ADM PROJECT 1

First project of the [Advanced Data Management](http://computerscience.dibris.unige.it/course/adm/) course of the Master in CS of the University of Genoa, academic year 2016-2017

## RUNNING THE PROJECT

To start docker move to the root folder and run the command 

    docker-compose up -d

To initialize the data

    hdfs dfs -mkdir -p wordcount/input

    hdfs dfs -put data/* /user/student/wordcount/input

To run the map-reduce scipts see the file run_all.sh and the run.sh files in each exercise folder.