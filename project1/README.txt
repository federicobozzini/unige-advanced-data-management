Federico Bozzini
Federico Semprini

Exercise A
MapData: separa l'input in parole singole
ReduceData: conta il numero di parole uguali
MapTranspose: scambia la chiave col valore
ReduceTranspose: concatena le parole con lo stesso numero di occorrenze

Exercise B
MapData: separa l'input in parole singole
ReduceData: conta il numero di parole uguali
MapTranspose: scambia la chiave col valore
ReduceTranspose: conta le parole con lo stesso numero di occorrenze

Exercise C
MapData: separa l'input in parole singole
ReduceData: conta il numero di parole uguali
MapTranspose: scambia la chiave col valore
CombineTraspose: conta le parole con lo stesso numero di occorrenze sullo stesso mapper
ReduceTranspose: conta le parole con lo stesso numero di occorrenze

Exercise D
come esercizi A e B ma con output redirizionato su files (SequenceFileOutputFormat)

Exercise E
MapData: Legge i dati dal path wordcount/wordtrans e genera la coppia (frequenza, parole)
ReduceData: scorre tutte le frequenze e salva solo le parole con la frequenza maggiore e genere un output durante il cleanup

Exercise F
Come esercizio E ma con il reduce che tiene anche le parole con frequenza minore

Exercise G
MapData: Legge i dati da wordcount/wordfreq e li passa al reducer
ReduceData: calcola la media man mano che arrivano i dati e genera come output un numero (solo la chiave)

Exercise H
MapTrans: Legge i dati da wordcount/wordtrans e passa al reducer le coppie (frequenza, parole)
MapFreq: Legge i dati da wordcount/wordfreq e passa al reducer le coppie (frequenza, numero di parole)
ReduceJoin: spacchetta i vaolri e genera un output (numero di parole, parole)



