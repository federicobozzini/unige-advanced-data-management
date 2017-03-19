wordfreq = LOAD '/user/student/wordcount/pig_wordfreq/' AS (freq:int, count:int);

g = GROUP wordfreq ALL;

average = FOREACH g GENERATE AVG(wordfreq.freq);

STORE average INTO 'wordcount/pig_ex_e';