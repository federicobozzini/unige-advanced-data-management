word_trans = LOAD '/user/student/wordcount/pig_wordtrans/' AS (freq:int, words:chararray);
word_freq = LOAD '/user/student/wordcount/pig_wordfreq/' AS (freq:int, count:int);

joined = JOIN word_trans BY freq, word_freq BY freq;

mapped = FOREACH joined GENERATE count, words;

STORE mapped INTO 'wordcount/pig_ex_f';