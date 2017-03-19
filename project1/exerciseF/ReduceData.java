package exerciseF;

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
  
  private String bottomWords;
  private int bottomKey = Integer.MAX_VALUE;
  
	protected void reduce(IntWritable key, Iterable<Text> values, Context ctx) throws IOException, InterruptedException {
    int k = key.get();
    
		if (k <= bottomKey) {
      bottomKey = k;
      bottomWords = this.join(values);
    }
    
		if (k >= topKey) {
      topKey = k;
      topWords = this.join(values);
    }
    
	}
  
  protected void cleanup(Context ctx) throws IOException, InterruptedException{
      ctx.write(new IntWritable(bottomKey), new Text(bottomWords));
      ctx.write(new IntWritable(topKey), new Text(topWords));
  }
  
  private String join(Iterable<Text> values){
      List<String> tmp = new ArrayList<String>();
      for(Text value : values) {
        tmp.add(value.toString());
      }
      return String.join(" ", tmp);
  }
}
