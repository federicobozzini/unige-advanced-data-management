package exerciseC;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CombineTranspose extends
		Reducer<IntWritable, // Input key type
				Text, // Input value type
				IntWritable, // Output key type
				IntWritable> { // Output value type

	protected void reduce(IntWritable key, Iterable<Text> values, Context ctx) throws IOException, InterruptedException {
    // This combiner just executes a preprocessing step for the reducer.
		int res = 0;

		for(Text value :  values) {
			res++;
		}

    ctx.write(key, new IntWritable(res));
	}
}

