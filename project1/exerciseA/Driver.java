package exerciseA;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Driver extends Configured implements Tool {

    private boolean step1(Job job, Path inputPath, Path outputPath, int numReducers) throws Exception {
        job.setJobName("WordCounter - step 1");
        // Set path of the input file/folder
        // if it is a folder, the job reads all the files in the specified folder
        FileInputFormat.addInputPath(job, inputPath);

        FileOutputFormat.setOutputPath(job, outputPath);

        // Specify the class of the Driver for this job
        job.setJarByClass(Driver.class);

        // Set job input format
        job.setInputFormatClass(TextInputFormat.class);

        // Set job output format
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        // Set map class
        job.setMapperClass(MapData.class);

        // Set map output key and value classes
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // Set reduce class
        job.setReducerClass(ReduceData.class);

        // Set reduce output key and value classes
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Set number of reducers
        job.setNumReduceTasks(numReducers);

        // Execute the job and wait for completion
        return job.waitForCompletion(true);
    }

    private boolean step2(Job job, Path inputPath, Path outputPath, int numReducers) throws Exception {
        job.setJobName("WordCounter - step 2");

        FileInputFormat.addInputPath(job, inputPath);

        FileOutputFormat.setOutputPath(job, outputPath);

        job.setJarByClass(Driver.class);

        job.setInputFormatClass(SequenceFileInputFormat.class);

        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapperClass(MapTranspose.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        job.setReducerClass(ReduceTranspose.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(numReducers);

        return job.waitForCompletion(true);
    }


    public int run(String[] args) throws Exception {
        Path inputPath = new Path(args[1]);
        Path output1Path = new Path("/tmp/wordcountfreq");
        Path output2Path = new Path(args[2]);
        int numReducers = Integer.parseInt(args[0]);

        Configuration conf = this.getConf();

        FileSystem fs = FileSystem.get(conf);
        if(fs.exists(output1Path)) {
            fs.delete(output1Path, true);
        }

        Job step1job = Job.getInstance(conf);
        Job step2job = Job.getInstance(conf);

        return step1(step1job, inputPath, output1Path, numReducers) &&
            step2(step2job, output1Path, output2Path, numReducers) ? 0 : 1;
    }

    public static void main(String args[]) throws Exception {
        int res = ToolRunner.run(new Configuration(), new Driver(), args);
        System.exit(res);
    }
}
