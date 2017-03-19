from pyspark import SparkContext, SparkConf
import sys


input = "hdfs://localhost:9000/user/student/" + sys.argv[1]
output = "hdfs://localhost:9000/user/student/" + sys.argv[2]

conf = SparkConf().setAppName("WordCount")
sc = SparkContext(conf=conf)

text_file = sc.textFile(input)
counts = text_file.flatMap(lambda line: line.lower().split(" ")).map(lambda word: (word, 1)).reduceByKey(lambda a, b: a + b)

counts.saveAsTextFile(output)

