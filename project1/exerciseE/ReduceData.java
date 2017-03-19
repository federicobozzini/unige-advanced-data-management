package exerciseE;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.ArrayList;
import java.util.List;

public class ReduceData extends
		Reducer<IntWritable, // Input key type
				Text, // Input value type
				IntWritable, // Output key type
				Text> { // Output value type

	
  private String topWords;
  private int topKey = 0;
  
	protected void reduce(IntWritable key, Iterable<Text> values, Context ctx) throws IOException, InterruptedException {
    
		if (key.get() > topKey) {
      List<String> tmp = new ArrayList<String>();
      topKey = key.get();
      for(Text value : values) {
        tmp.add(value.toString());
      }
      topWords = String.join(" ", tmp);
    }
    
	}
  
  protected void cleanup(Context ctx) throws IOException, InterruptedException{
      ctx.write(new IntWritable(topKey), new Text(topWords));
  }
}
