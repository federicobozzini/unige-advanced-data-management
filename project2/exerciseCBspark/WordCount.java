package exerciseCBspark;

import scala.Tuple2;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.PairFlatMapFunction;

import java.util.Map;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import java.util.Arrays;

public class WordCount {
    public static void main(String[] args) throws Exception {
        String inputFile = "hdfs://localhost:9000/user/student/" + args[0];
        String outputFile = "hdfs://localhost:9000/user/student/wordcount/spark_wordfreq";

        // Create a Java Spark Context.
        SparkConf conf = new SparkConf().setAppName("WordCount");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> textFile = sc.textFile(inputFile);

        JavaPairRDD<Integer, Integer> counts = textFile
                .mapToPair((String s) -> {
                  String[] parts = s.split(" ");
                  String word = parts[0];
                  Integer count = Integer.parseInt(parts[1]);
                  return new Tuple2<Integer, Integer>(count, 1);
                })
                .reduceByKey((Integer a, Integer b) -> a + b);
                
        counts.saveAsObjectFile(outputFile);
    };
};
