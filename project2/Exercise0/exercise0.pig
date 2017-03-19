inputwords = LOAD '/user/student/wordcount/input/';
words = FOREACH inputwords GENERATE FLATTEN(TOKENIZE(LOWER((chararray)$0))) AS word;
word_group = GROUP words BY word;
wc = FOREACH word_group GENERATE group as word, COUNT(words) AS count;
STORE wc INTO 'wordcount/pig_output';