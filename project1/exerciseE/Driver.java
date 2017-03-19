package exerciseE;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.fs.FileSystem;

public class Driver extends Configured implements Tool {

	public int run(String[] args) throws Exception {
    Path inputPath = new Path("wordcount/wordtrans");
		Path outputPath = new Path(args[0]);
    int numReducers = 1;

		Configuration conf = this.getConf();

		// Define a new job;
		Job job = Job.getInstance(conf);

		job.setJobName("WordCounter");
		// Set path of the input file/folder
		// if it is a folder, the job reads all the files in the specified folder
		FileInputFormat.addInputPath(job, inputPath);

		FileOutputFormat.setOutputPath(job, outputPath);

		// Specify the class of the Driver for this job
		job.setJarByClass(Driver.class);

		// Set job input format
    job.setInputFormatClass(SequenceFileInputFormat.class);

    // Set job output format
	  job.setOutputFormatClass(TextOutputFormat.class);

	  // Set map class
	  job.setMapperClass(MapData.class);

	  // Set map output key and value classes
	  job.setMapOutputKeyClass(IntWritable.class);
	  job.setMapOutputValueClass(Text.class);

	  // Set reduce class
	  job.setReducerClass(ReduceData.class);

	  // Set reduce output key and value classes
	  job.setOutputKeyClass(IntWritable.class);
	  job.setOutputValueClass(Text.class);

    // Set number of reducers
    job.setNumReduceTasks(numReducers);

    // Execute the job and wait for completion
    return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String args[]) throws Exception {
		int res = ToolRunner.run(new Configuration(), new Driver(), args);
		System.exit(res);
	}
}
