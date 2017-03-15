/*************************************************************************
  * Name         : Sophia Ciocca
  * PennKey      : sciocca
  * Recitation # : 211
  * Dependencies: none; implements List.java
  * 
  * The LinkedList class represents a singly linked list. It includes nine
  * public methods related to modifying or reviewing the LinkedList.
  *****************************************************************************/

public class LinkedList implements List {
    
    // define head now - the head of the list
    private Node head;
    
    
    // Inner Node Class
    class Node {
        Node next;
        String val;
        
        // Node constructor
        public Node(String stringInput) {
            val = stringInput;
        }   
    }
    
    
    // LinkedList constructor
    public LinkedList() {
        head = null;
    }
    
    
    
    /**
     * Adds the object x to the end of the list.
     * @param x the element to be added to this list
     * @return true
     */
    public boolean add(String x) {
        
        //special case: if list is empty
        if (isEmpty()) {
            // create a new head node
            head = new Node(x);
            return true;
        }
        
        //general case: the list exists/not empty
        //we need to find its end/tail, add node there
        //create node, set to head initially
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node(x);
        return true;
        
    }
    
    
    
    /**
     * Adds the object x at the specified position
     * @param index the position to add the element
     * @param x the element to be added to the list
     * @return true if the operation succeeded, false otherwise
     * @throws IllegalArgumentException if index is invalid
     */
    public boolean add(int index, String x) {
        
        //if index is invalid: Illegal Argument Exception
        if (index < 0 || index > size()) {
            throw new IllegalArgumentException("Your index is outside the range of the LinkedList.");
        }
        
        //special case: if list is empty
        if (isEmpty()) {
            // create a new head node
            head = new Node(x);
            return true;
        }
        
         //special case: if we want to add it at the head
        if (index == 0) {
            Node temp = head;
            head = new Node(x);
            head.next = temp;
            return true;
        }
        
        //general case: list exists, not empty
        //create node, set to head initially
        Node node = head;

        
        //get to index
        for (int i = 0; i < index - 1; i++, node = node.next);
        
        //create new node with String x, call it "add"
        Node add = new Node(x);
        
        //save the stuff from after the index as "temp"
        Node temp = node.next;
        
        //make "node" (index node) point to "add"
        node.next = add;
        
        //make "add" connect to "temp"
        add.next = temp;

        
        return true;
        
    }
    
    
    
    /**
     * Returns the number of elements in this list
     * @return the number of elements in this list
     */
    public int size() { 
        
        //check if list is empty
        if (head == null) {
            return 0;
        }
        
        //create node, set to head initially
        Node node = head;
        
        //set up counter to count each next
        int counter = 1;
        
        //
        while (node.next != null) {
            
            //advance node
            node = node.next;
            
            //if it's not the last thing in the list, go on to the next one & increase the counter
            counter++;
            
        }
        
        return counter;
    }
    
    
    
    /**
     * Returns the element with the specified position in this list
     * @param index the position of the element
     * @return the element at the specified position in this list
     * @throws IllegalArgumentException if index is invalid
     */
    public String get(int index) {
        
        //if index is invalid: Illegal Argument Exception
        if (index < 0 || index > size() - 1) {
            throw new IllegalArgumentException("Your index is outside the range of the LinkedList.");
        }
        
        //create node, set to head initially
        Node node = head;
        
        //get to index (for loop)
        for (int i = 0; i < index; i++, node = node.next);
        
        //return that index.item
        return node.val;
        
    }
    
    
    
    /**
     * Replaces the object at the specified position
     * @param index the position to replace
     * @param x the element to be stored
     * @return the previous value of the element at index
     * @throws IllegalArgumentException if index is invalid
     */
    public String set(int index, String x) {
        
        //if index is invalid: Illegal Argument Exception
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException("Your index is outside the range of the LinkedList.");
        }
        

        //special case: if we want to add it at the head
        if (index == 0) {
            Node temp = head;
            Node newNode = new Node (x);
            newNode.next = head.next;
            head = newNode;
            return temp.val;
        }
        
        //general case: list exists
        Node node = head;
        
        //get to thing BEFORE index (for loop)
        for (int i = 0; i < index - 1; i++, node = node.next);
        
        //save old index as something (to return later)
        Node oldIndex = node.next;
        
        //save things after old index as temp
        Node temp = oldIndex.next;
        
        //create new node with String x, call it "add"
        Node add = new Node(x);
        
        //make node point to new thing (new Node(x))
        node.next = add;
        
        //make new thing point to temp
        add.next = temp;
        
        //return old index
        return oldIndex.val;
        
    }
    
    
    
    /**
     * Removes the object at the specified position
     * @param index the position to remove
     * @return the object that was removed
     * @throws IllegalArgumentException if index is invalid
     */
    public String remove(int index) {
        
        //CHECK if index is invalid: Illegal Argument Exception
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException("Your index is outside the range of the LinkedList.");
        }
        
        //if index = 0, remove head, change head to next
        if (index == 0) {
            Node temp = head;
            head = head.next;
            return temp.val;
        }
        
        //create node, set to head initially
        Node node = head;
        
        //get to thing BEFORE index (for loop)
        for (int i = 0; i < index - 1; i++, node = node.next);
        
        //save old index as something (to return later)
        Node oldIndex = node.next;
        
        //save things after old index as temp
        Node temp = oldIndex.next;
        
        //make node point to temp
        node.next = temp;
        
        //return old index
        return oldIndex.val;
        
    }
    
    
    
    /**
     * Tests if this list has no elements.
     * @return  <tt>true</tt> if this list has no elements;
     *          <tt>false</tt> otherwise.
     */
    public boolean isEmpty() {
        
        //check if empty, if so return true
        if (size() == 0 || head == null) {
            return true;
        } 
        
        //if not empty, return false
        return false;
        
    }
    
    
    
    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     *
     * @param element element whose presence in this List is to be tested.
     * @return  <code>true</code> if the specified element is present;
     *  <code>false</code> otherwise.
     */
    public boolean contains(String element) {
        
        //start linkedlist at beginning, at head
        Node node = head;
        
        //create for loop
        for (int i = 0; i < size(); i++, node = node.next) {
            
            //compare each index.val to element, if they're equal, return true
            if (node.val.equals(element)) {
                return true;
            }
            
        }
        
        //if index.val is never equal to element, return false
        return false;
        
    }
    
    
    
    /**
     * Returns the index of the specified element
     *
     * @param element the element we're looking for
     * @return the index of the element in the list, or -1 if it is not contained within the list
     */
    public int indexOf(String element) {
        
        //if head == null
        if (head == null) {
           return -1;   
        }
        
        //define node, start at head
        Node node = head;
        
        //define i
        int i = 0;
        
        //go through list
        for (i = 0; !node.val.equals(element) && i < size() - 1; i++, node = node.next);
        
        //if we found the value, return it
        if (node.val.equals(element)) {
            return i;
        }
        
        return -1;
        
    }
    
    
    //MAIN: TESTING
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.add("this");
        l.add("is");
        l.add("a");
        l.add("list");
        
        l.add(2, "hello");
        
        
        for (int i = 4; i >= 0; i--) {
            System.out.println(l.get(i));  
        }   
    }
    
    
}