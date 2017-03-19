package exerciseC;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceTranspose extends
		Reducer<IntWritable, // Input key type
				Text, // Input value type
				IntWritable, // Output key type
				IntWritable> { // Output value type

	protected void reduce(IntWritable key, Iterable<Text> values, Context ctx) throws IOException, InterruptedException {
		int res = 0;

		for(Text value :  values) {
			res++;
		}

    ctx.write(key, new IntWritable(res));
	}
}
