package exerciseF;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapData extends
		Mapper<LongWritable, // Input key type
				Text, // Input value type
				IntWritable, // Output key type
				Text> {// Output value type

	private static final IntWritable one = new IntWritable(1);

	protected void map(IntWritable key, Text value, Context ctx) throws IOException, InterruptedException {
		String[] words = value.toString().split("\\s+");
    int count = Integer.parseInt(words[0]);
    
    for (int i=1; i< words.length; i++)
      ctx.write(new IntWritable(count), new Text(words[i]));
	}
}
