package exerciseDspark;

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
        String inputFile = "hdfs://localhost:9000/user/student/wordcount/spark_wordtrans";
        String outputFile = "hdfs://localhost:9000/user/student/" + args[0];

        // Create a Java Spark Context.
        SparkConf conf = new SparkConf().setAppName("WordCount");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        class TupleComparator implements 
            Comparator<Tuple2<Integer, String>>, Serializable {
          public int compare(Tuple2<Integer, String> x, Tuple2<Integer, String> y) {
            return Integer.compare(x._1(), y._1());
          }
        }
        
        JavaPairRDD<Integer, String> m = JavaPairRDD
          .fromJavaRDD(sc.objectFile(inputFile));
          
        Tuple2<Integer, String> res = m.max(new TupleComparator());
        
        ArrayList<Tuple2<Integer, String>> r = new ArrayList<Tuple2<Integer, String>>();
        r.add(res);
        
        sc.parallelize(r).saveAsTextFile(outputFile);
    };
};
