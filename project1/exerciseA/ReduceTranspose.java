package exerciseA;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceTranspose extends
		Reducer<IntWritable, // Input key type
				Text, // Input value type
				IntWritable, // Output key type
				Text> { // Output value type

	protected void reduce(IntWritable key, Iterable<Text> values, Context ctx) throws IOException, InterruptedException {
		String res = "";

		for(Text value :  values) {
			res = res + " " + value.toString();
		}

    ctx.write(key, new Text(res));
	}
}
