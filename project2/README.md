# ADM PROJECT 2

Second project of the [Advanced Data Management](http://computerscience.dibris.unige.it/course/adm/) course of the Master in CS of the University of Genoa, academic year 2016-2017

## RUNNING THE PROJECT

To start docker move to the root folder and run the command

    docker-compose up -d

To initialize the data

    hdfs dfs -mkdir -p wordcount/input

    hdfs dfs -put data/* /user/student/wordcount/input

### PIG

The pig scripts are in the folders named ExerciseX.

To run the pig scripts move to the ExerciseX folder and run the exerciseX_pig_run.sh script.

### SPARK

The spark scripts are in the foldere named ExerciseXspark.

To run the spark scripts launche the following commands

    javac -cp/opt/spark-2.0.1-bin-hadoop2.7/jars/spark-core_2.11-2.0.1.jar:/opt/spark-2.0.1-bin-hadoop2.7/jars/scala-library-2.11.8.jar exerciseXspark/WordCount.java

    jar cf WordCount.jar exerciseXspark/

    spark-submit --class exerciseXspark.WordCount WordCount.jar <params>

For instance to run the exercise 0 the command is

    javac -cp /opt/spark-2.0.1-bin-hadoop2.7/jars/spark-core_2.11-2.0.1.jar:/opt/spark-2.0.1-bin-hadoop2.7/jars/scala-library-2.11.8.jar exercise0spark/WordCount.java

    jar cf WordCount.jar exercise0spark/

    spark-submit --class exercise0spark.WordCount WordCount.jar wordcount/input/ wordcount/spark_output

For the following scripts follow the instructions in the pdf.

Note the the exercises must be executed in order, especially run the script 0.