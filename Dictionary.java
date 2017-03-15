/*************************************************************************
  * Name         : Sophia Ciocca
  * PennKey      : sciocca
  * Recitation # : 211
  * Dependencies: MRUList.java, LinkedList.java
  * 
  * Represents the complete set of correctly spelled words. 
  * It is an array of 26 MRULists, each of which holds words that start with
  * a single letter.
  *****************************************************************************/

public class Dictionary {    
    
    //create array of 26 MRU Lists
    MRUList[] arrayOfMRUs;
    
    //figure out subtratcion # to compare lowercase 1st letter to correct array[#]
    // a: 97, A: 65   b: 98, B: 66
    int subtractionNumber = 97;
    
    //make constructor
    public Dictionary() {  
        //defining array
        arrayOfMRUs = new MRUList[26];
    
        //for loop: initialize array of MRULists
        for (int i = 0; i < arrayOfMRUs.length; i++) {     
            //fill that array spot with an MRUList                               
            arrayOfMRUs[i] = new MRUList();  
        }     
    }
    
    
    // insert method:
    // --adds the specified word to the dictionary if it does not already contain that word
    // --throws IlledgalArgumentException if the word is invalid
    public void insert (String word) {
        
       //CHECK: check if string is null. if so, throw illegal argument exception
        if (word == null || word.equals("")) {
            throw new IllegalArgumentException("You have entered an invalid word.");
        }
        
        //get lowercase first letter
        char firstLetter = getLowercaseFirstLetter(word);
        
        //convert first letter to integer, to compare to valid letter integers
        int intFirstLetter = (int) firstLetter; 
        
        //CHECK: check if valid:
        // a: 97, z: 122
        //return IllegalArgumentException if the string is null OR starts with anything other than a letter
        if (intFirstLetter < 97 || intFirstLetter > 122) {
            throw new IllegalArgumentException("You have entered an invalid word.");
        }            

        //CHECK: check if word is already there. if so, return
        if (contains(word)) {
            return;
        }
        
        //add word to dictionary
        arrayOfMRUs[(intFirstLetter - subtractionNumber)].add(word);

    }
    
    
    // contains method:
    // -- tests whether the dictionary contains specified word
    public boolean contains (String word) {
        
        //get lowercase first letter
        char firstLetter = getLowercaseFirstLetter(word);
        
        //cast that lowercase to int
        int intFirstLetter = (int) firstLetter;
        
        //if first letter is not a letter, return false
        if ((intFirstLetter - subtractionNumber) < 0 || (intFirstLetter - subtractionNumber) > 25) {
            return false;
        }
        
        //return whether or not its there
        return arrayOfMRUs[(intFirstLetter - subtractionNumber)].contains(word);
           
    }
    
    
    // printMRU method:
    // --outputs the n most recently used words in the dictionary for each letter
    // of the alphabet
    // --if contains less than n words, output entire list
    public void printMRU (int n) {
        
        //for loop: go through each array[i]
        for (int k = 0; k < arrayOfMRUs.length; k++) {  
            
            //do uppercase letter number for each time through loop (A - Z)
            int uppercaseLetterNumber = 65 + k;
            //convert that to char
            char uppercaseLetter = (char) uppercaseLetterNumber;
            
            if (!arrayOfMRUs[k].isEmpty()) {
                //print out that char (A: )
                System.out.print(uppercaseLetter + ": ");
                
                //print n most recent words
                arrayOfMRUs[k].printWords(n);               
            }
        }
        
    }
    
    //PRIVATE METHOD: getLowercaseFirstLetter:
    //takes a word, and returns the lowercase first letter
    private char getLowercaseFirstLetter (String word) {
        
        //turn string to lowercase, save as lowercaseword
        String lowercaseWord = word.toLowerCase();
        
        //isolate first letter in lowercase word
        char firstLetter = lowercaseWord.charAt(0);
        
        //return
        return firstLetter;
    }
    
    
    //MAIN: TESTING
    public static void main(String[] args) {
        
        //create a dictionary
        Dictionary dictionary = new Dictionary();
        
        //insert a bunch of words
        dictionary.insert("apples");
        dictionary.insert("boy");
        dictionary.insert("animal");
        dictionary.insert("peach");
        
        //print out the result (true/false) 
        System.out.println("boy in dict? expected: true, real: " + dictionary.contains("boy"));
        System.out.println("pear in dict? expected: false, real: " + dictionary.contains("pear"));
        
        System.out.println("2abc in dict? expected: false, real: " + dictionary.contains("2abc"));
        System.out.println("a2bc in dict? expected: false, real: " + dictionary.contains("a2bc"));
        
        //print out the dictionary
        dictionary.printMRU(3);
        
    }
    
}