package exerciseFspark;

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
import java.util.Comparator;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Arrays;

public class WordCount {
    public static void main(String[] args) throws Exception {
        String wordTransFile = "hdfs://localhost:9000/user/student/wordcount/spark_wordtrans";
        String wordFreqFile = "hdfs://localhost:9000/user/student/wordcount/spark_wordfreq";
        String outputFile = "hdfs://localhost:9000/user/student/" + args[0];

        // Create a Java Spark Context.
        SparkConf conf = new SparkConf().setAppName("WordCount");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        JavaPairRDD<Integer, String> wordTrans = JavaPairRDD
          .fromJavaRDD(sc.objectFile(wordTransFile));
        
        JavaPairRDD<Integer, Integer> wordFreq = JavaPairRDD
          .fromJavaRDD(sc.objectFile(wordFreqFile));
          
        JavaRDD<scala.Tuple2<Integer,String>> res = wordFreq.join(wordTrans).map(t -> t._2);
        
        res.saveAsTextFile(outputFile);
    };
};
