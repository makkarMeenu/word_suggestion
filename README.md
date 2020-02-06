# word_suggestion
Checks Word spellings and suggests 5 close words. 

Format to use : (CLI)
```
javac spellSuggestion.java
java spellSuggestion ./input.csv word
```
here input.csv is path to dictionary file and word is the word(correct or misspelled).


technique used : 
Multiple Hashmaps, checks frequencies, prefix, postfix, length and gives the output.
