# word_suggestion
Checks Word spellings and suggests 5 close words. 

Format to use : (CLI)
```
javac spellSuggestion.java
java spellSuggestion ./input.csv word
```

![Capture](https://user-images.githubusercontent.com/54400869/73965246-ca0c7900-4939-11ea-8363-ca4349bcb24d.PNG)


here input.csv is path to dictionary file and word is the word(correct or misspelled).


technique used : 
Multiple Hashmaps, checks frequencies, prefix, postfix, length and gives the output.


Dictionaries should be less than 10000 is size.
