package exerciseG;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapData extends
		Mapper<IntWritable, // Input key type
				Text, // Input value type
				FloatWritable, // Output key type
				FloatWritable> {// Output value type

	protected void map(IntWritable value, Text count, Context ctx) throws IOException, InterruptedException {
    ctx.write(new FloatWritable((float)value.get()), new FloatWritable(Float.parseFloat(count.toString())));
  }
}
