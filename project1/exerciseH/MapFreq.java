package exerciseH;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapFreq extends
		Mapper<IntWritable, // Input key type
				Text, // Input value type
				IntWritable, // Output key type
				ValuesWritable> {// Output value type

	protected void map(IntWritable value, Text count, Context ctx) throws IOException, InterruptedException {
    ValuesWritable res = new ValuesWritable(
    new IntWritable(
      Integer.parseInt(count.toString())
      )
    );
    ctx.write(value, res);
  }
}
