package exerciseH;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class ValuesWritable implements Writable {
    private IntWritable len;
    private Text words;
    private int kind;

    public  ValuesWritable() {
        len = new IntWritable();
        words = new Text();
        kind = -1;
    }

    public ValuesWritable(ValuesWritable rw){
        this();
        this.len.set(rw.len.get());
        this.words.set(rw.words);
        kind = rw.kind;
    }

    public  ValuesWritable(IntWritable len) {
        this();
        this.len.set(len.get());
        kind = 0;
    }

    public  ValuesWritable(Text words) {
        this();
        this.words.set(words);
        kind = 1;
    }


    public boolean isLen(){
        return kind == 0;
    }

    public boolean isWords(){
        return kind == 1;
    }

    public IntWritable getLen(){
        return len;
    }

    public Text getWords(){
        return words;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        len.write(out);
        words.write(out);
        out.writeInt(kind);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        len.readFields(in);
        words.readFields(in);
        kind = in.readInt();
    }

    @Override
    public int hashCode() {
        int res = len.hashCode();
        res+= 31 * words.hashCode();
        res+= 17 * kind;
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ValuesWritable)) {
            return false;
        }
        ValuesWritable tw = (ValuesWritable) obj;
        return this.len.equals(tw.len) &&
            this.words.equals(tw.words) &&
            this.kind == tw.kind;
    }

    @Override
    public String toString() {
        return kind + " " + len.toString() + " " + words.toString();
    }
}
