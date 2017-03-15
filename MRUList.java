/*************************************************************************
  * Name         : Sophia Ciocca
  * PennKey      : sciocca
  * Recitation # : 211
  * Dependencies: extends LinkedList.java
  * 
  * MRUList.java extends LinkedList.java. It has the same functions,
  * however whenever the contains function is called, it moves the
  * accessed item to the front of the list.
  *****************************************************************************/

public class MRUList extends LinkedList {
    
    
    /**
     * Returns "true" if this list contains the specified element,
     * and moves the accessed element to the front of the list.
     *
     * @param element: element whose presence in this List is to be tested.
     * @return  "true" if the specified element is present;
     *  "false"otherwise.
     */
    public boolean contains(String element) {
    
        //check: if list DOESN'T contain element we're looking for, return false
        if (!super.contains(element)) {
            return false;
        }
        
        //if it DOES contain element:
        //find the index for it
        int index = indexOf(element);
        
        //remove the element from where it was
        remove(index);
        
        //add it to head
        add(0, element);
        
        //return true, because the list contained the element
        return true;
        
    }
    
    
    
    /**
     * Prints the n most recently used words in the list on a single line separated by spaces. 
     * If there are less than n words in the MRUList, it prints all elements.
     */
    public void printWords(int n) {
        
        if (n > size()) {
            n = size();
        }
        
        for (int i = 0; i < n; i++) {
            
            //get the value of the index
            String value = get(i);
            
            //if it's the last value, no space afterwards
            if(i == n - 1) {
                System.out.print(value + "\n");
            } 
               
            else {
            System.out.print(value + " ");
            }
            
        }
        
    }
    
    
    
    //MAIN: FOR TESTING
    public static void main(String[] args) {
        
        MRUList mru = new MRUList();
        mru.add("i");
        mru.add("like");
        mru.add("apples");
        
        mru.printWords(10);
        
        mru.contains("apples");
        mru.printWords(3);
        
        mru.contains("i");
        mru.printWords(0);
        
    }
    
}