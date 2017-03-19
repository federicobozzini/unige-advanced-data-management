package exerciseEspark;

import scala.Tuple2;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.PairFlatMapFunction;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import java.util.ArrayList;

public class WordCount {
    public static void main(String[] args) throws Exception {
        String inputFile = "hdfs://localhost:9000/user/student/" + args[0];
        String outputFile = "hdfs://localhost:9000/user/student/" + args[1];

        // Create a Java Spark Context.
        SparkConf conf = new SparkConf().setAppName("WordCount");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> textFile = sc.textFile(inputFile);

        JavaRDD<Integer> rdd = textFile
                .map((String s) -> {
                  String[] parts = s.split(" ");
                  String word = parts[0];
                  Integer count = Integer.parseInt(parts[1]);
                  return count;
                })
                .cache();
                
        Float sum = (float) rdd.reduce((Integer a, Integer b) -> a + b);
        Float count = (float) rdd.count();
        Float avg = sum/count;

        ArrayList<Float> r = new ArrayList<Float>();
        r.add(avg);
        
        sc.parallelize(r).saveAsTextFile(outputFile);
    };
};
