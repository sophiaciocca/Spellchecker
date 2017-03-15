/*************************************************************************
  * Name         : Sophia Ciocca
  * PennKey      : sciocca
  * Recitation # : 211
  * Dependencies: Dictionary.java, MRUList.java, LinkedList.java
  * 
  * WHAT IT DOES
  * 
  *****************************************************************************/

public class SpellChecker {
    
    /** Main Function
      */
    public static void main(String[] args) {
        
        //ERROR CHECKING:
        //if wrong number of arguments, program should 
        //print an appropriate message describing how to use the program and then gracefully exit.
        if (args.length < 2 || args.length > 3) {
            System.out.println("You entered an incorrect number of arguments.");
            System.out.println("Correct usage: java SpellChecker <text>.txt <dictionary>.txt (-d)");
            return;
        }
        
        //if there are 3 args & third argument is anything other than -d, invalid argument
        if (args.length == 3 && !args[2].equals("-d")) {
            System.out.println("You entered an invalid third argument.");
            System.out.println("Correct usage: java SpellChecker <text>.txt <dictionary>.txt (-d)");
            return;
        }
        
        
        //now, let's get the DICTIONARY FILE set up
        //read in dictionary file
        String dictionaryFilename = args[1];
        In inStream = new In(dictionaryFilename);
        
        //construct dictionary object
        Dictionary dictionary = new Dictionary();
        
        //populate dictionary loop
        while (!inStream.isEmpty()) {
            //read in a word
            String wordFromText = inStream.readString();
            
            //populate dictionary object with words
            dictionary.insert(wordFromText);
        }
        
        //now, let's get the TEXT FILE set up
        //read in text file
        String textFilename = args[0];
        
        //create second instream
        In inStream2 = new In(textFilename);
        
        //read in all the words into one string
        String allWords = inStream2.readAll();
        
        //separate into words (removePunctuationAndSplitIntoWords)
        String[] arrayOfWords = removePunctuationAndSplitIntoWords(allWords);
        
        
        //now, LET'S GO CHECK SOME SPELLING
        //print out "Spell checking [textfile name] using [dictionary name]"
        System.out.println("Spell checking " + textFilename + " using " + dictionaryFilename);
        System.out.println();
        
        //establish counter
        int counter = 0;
        
        //create for loop to iterate through array
        for (int i = 0; i < arrayOfWords.length; i++) {
            
            //define "word" as current place in array
            String word = arrayOfWords[i];
            
            //if a word is a number, skip it and go to next word (iteration of for loop)
            if (isNumber(word)) {
                continue;
            }
            
            //IF word is not in dictionary (i.e. misspelled)
            if (!dictionary.contains(word)) {
                
                //increase counter by 1
                counter++;
                
                //if this is the first misspelled word, print "Misspelled Words:"
                if (counter == 1) {
                    System.out.println("Misspelled Words:");
                }
                
                //print out 1.) or 2.) etc.
                System.out.print(counter + ".) ");
                
                
                //if not two words before it or after it, then just print out however many there are
                int startingK;
                int endingK;
                
                //figure out beginning of for loop for printing words
                if (i < 2) {
                    startingK = 0;
                }
                else {
                    startingK = i - 2;
                }
                
                //figure out ending of for loop for printing words
                if (i > arrayOfWords.length - 2) {
                    endingK = arrayOfWords.length;
                }
                else {
                    endingK = i + 3;
                }
                
                //for loop to go through and print words!
                for (int k = startingK; k < endingK; k++) {
                    //if it's the misspelled word, put it in asterisks
                    if (k == i) {
                        System.out.print("*" + arrayOfWords[k] + "* ");
                    }
                    
                    //if not the misspelled word, just print it
                    else {
                        System.out.print(arrayOfWords[k] + " ");
                    }                   
                }

                //end line, so that each misspelled word is on its own line
                System.out.println();
                
            }
            
        }
        
        //if counter is still 0 at the end, print out "No misspelled words"
        if (counter == 0) {
            System.out.println("No Misspelled Words");
        }
        
        //IF user inputted "-d" as third argument, print out "Most Recently Used Words: " 
        // & Dictionary.printWords(10) (most recent 10, n = 10)
        if (args.length == 3 && args[2].equals("-d")) {
            System.out.println();
            System.out.println("Most Recently Used Words:");
            dictionary.printMRU(10);
        }
        
        
    }
    
    
    //***************HELPER FUNCTIONS***************//
    
    
    /** removePunctuationAndSplitIntoWords - give string, return string
      * Strips the punctuation from all words in an array
      * @param s a string that contains one or more words with punctuation
      * @return an array of the individual words with punctuation stripped
      */
    static String[] removePunctuationAndSplitIntoWords(String s) {
        String[] rawWords = s.split("\\s+|-");
        String[] cleanedWords = new String[rawWords.length];
        for (int i = 0; i < rawWords.length; i++) {
            cleanedWords[i] = removePunctuationFromWord(rawWords[i]);
        }
        return cleanedWords;
    }
    
    
    /** removePunctuationFromWord - give string, return string
      * Strips the punctuation from a word
      * @param s the word
      * @return the word with the punctuation removed
      */
    static String removePunctuationFromWord(String s) {
        
        StringBuffer buf = new StringBuffer();
        
        // find first and last letter to denote start and end of word
        int startOfWord = -1;
        int endOfWord = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                if (startOfWord == -1)
                    startOfWord = i;  // start is only set once
                endOfWord = i;    // end keeps being updated until it runs out
            }
        }
        if (startOfWord < 0) {
            return "";
        }
        
        // from the start of the word, strip only quotation marks and parenthesis
        for (int i = 0; i < startOfWord; i++) {
            char ch = s.charAt(i);
            if (ch != '\"' && ch != '(' && ch != '[' && ch != '{') {
                buf.append(ch);
            }
        }
        // from the end of the word, we strip all punctuation
        buf.append(s.substring(startOfWord, endOfWord + 1));
        
        return buf.toString();
    }
    
    
    /** isNumber - give string, return boolean
      * Tests to see if a word contains a number
      * @param word the word to test
      * @return true if it is a number, false otherwise
      */
    static boolean isNumber(String word) {
        try {
            Double.parseDouble(word);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}