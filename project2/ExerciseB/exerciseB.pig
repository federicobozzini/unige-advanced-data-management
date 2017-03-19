words = LOAD '/user/student/wordcount/pig_output/' AS (word:chararray, freq:int);

word_group = GROUP words BY freq;

word_group2 = FOREACH word_group GENERATE group AS freq, COUNT(words) AS count; 

STORE word_group2 INTO 'wordcount/pig_wordfreq';