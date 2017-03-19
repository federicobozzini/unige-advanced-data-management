package exerciseH;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.ArrayList;
import java.util.List;

public class ReduceJoin extends
		Reducer<IntWritable, // Input key type
				ValuesWritable, // Input value type
				IntWritable, // Output key type
				Text> { // Output value type

  
	protected void reduce(IntWritable key, Iterable<ValuesWritable> values, Context ctx) throws IOException, InterruptedException {
    IntWritable newKey = new IntWritable();
    Text newValue = new Text();
    
    for ( ValuesWritable value: values) {
      if (value.isLen())
        newKey.set(value.getLen().get());
      else
        newValue.set(value.getWords().toString());
    }
    ctx.write(newKey, newValue);
    
	}
}
