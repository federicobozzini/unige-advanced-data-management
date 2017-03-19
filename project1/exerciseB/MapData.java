package exerciseB;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapData extends
		Mapper<LongWritable, // Input key type
				Text, // Input value type
				Text, // Output key type
				IntWritable> {// Output value type

	private static final IntWritable one = new IntWritable(1);

	protected void map(LongWritable key, Text value, Context ctx) throws IOException, InterruptedException {
		String[] words = value.toString().split("\\s+");

		for (String word : words) {
			word = word.toLowerCase();

			ctx.write(new Text(word), one);
		}
	}
}
