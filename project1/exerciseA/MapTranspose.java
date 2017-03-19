package exerciseA;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapTranspose extends
		Mapper<Text, // Input key type
				IntWritable, // Input value type
				IntWritable, // Output key type
				Text> {// Output value type

	private static final IntWritable one = new IntWritable(1);

	protected void map(Text key, IntWritable value, Context ctx) throws IOException, InterruptedException {
    ctx.write(value, key);
	}
}
