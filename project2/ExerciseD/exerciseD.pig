word_trans = LOAD '/user/student/wordcount/pig_wordtrans/' AS (freq:int, words:chararray);

g = GROUP word_trans ALL;

max = FOREACH g GENERATE MAX(word_trans.freq) AS freq;

word_max = FILTER word_trans By freq == max.freq;

STORE word_max INTO 'wordcount/pig_ex_d';