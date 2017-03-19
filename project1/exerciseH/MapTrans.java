package exerciseH;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapTrans extends
		Mapper<IntWritable, // Input key type
				Text, // Input value type
				IntWritable, // Output key type
				ValuesWritable> {// Output value type

	protected void map(IntWritable value, Text words, Context ctx) throws IOException, InterruptedException {
    ctx.write(value, new ValuesWritable(words));
  }
}
