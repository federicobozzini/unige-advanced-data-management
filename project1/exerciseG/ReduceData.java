package exerciseG;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.ArrayList;
import java.util.List;

public class ReduceData extends
		Reducer<FloatWritable, // Input key type
				FloatWritable, // Input value type
				FloatWritable, // Output key type
				NullWritable> { // Output value type

  private float avg = 0;
  private float total = 0;
  
	protected void reduce(FloatWritable key, Iterable<FloatWritable> values, Context ctx) throws IOException, InterruptedException {
    float value = 0;
    
    for(FloatWritable v : values) {
      value += v.get();
    }
    
    float currentTotal = total;
    total += value;
    avg = (avg * currentTotal + (key.get() * value) ) / total;
    
	}
  
  protected void cleanup(Context ctx) throws IOException, InterruptedException{
      ctx.write(new FloatWritable(avg), NullWritable.get());
  }
}
