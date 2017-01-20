//  List.java
//  An integer List ADT
//  Programming Assignment 1 (CMPS101 Winter)
//  Jingyan Ran (jran4@ucsc.edu)
//  programmed based on Queue.java on the class website

class List{

   private class Node{
      // Fields
      int data;
      Node previous;
      Node next;
      
      // Constructor
      Node(int data) { this.data = data; previous = null ; next = null; }
      
      // toString():  overrides Object's toString() method
      public String toString() { 
         return String.valueOf(data); 
      }
      
      // equals(): overrides Object's equals() method
      public boolean equals(Object x){
         boolean eq = false;
         Node that;
         if(x instanceof Node){
            that = (Node) x;
            eq = (this.data==that.data);
         }
         return eq;
      }
   }

   // Fields
   private Node front;
   private Node back;
   private Node cursor;
   private int length;
   private int index;

   // List Constructor
   List() { 
      front = back = cursor = null; 
      length = 0; 
      index = -1;  
   }

   // Access Functions --------------------------------------------------------

   // length()
   // Returns length of this Queue.
   int length() { 
      return length; 
   }
   // index()
   // If cursor is defined, return the index of the cursor
   // if not, returns -1.
   int index() {
      if(length() <=0){
         return -1;
      }
      return index;
   }

   // front() 
   // Returns front element.
   // Pre: length() > 0
   int front(){
      if( length() == 0 ){
         throw new RuntimeException(
            "List Error: front() called on empty List");
      }
      return front.data;
   }
   
   // back()
   // Returns back element.
   // Pre: length() > 0
   int back(){
      if( length() == 0){
         throw new RuntimeException(
            "List Error: back() called on empty List");
      }
      return back.data;
   }

   // get()
   // Returns cursor element.
   // Pre: length() > 0, index() >= 0
   int get() {
      if( cursor == null){
         throw new RuntimeException(
            "List Error: get() called with undefined index on List");
      }
      if( length() <= 0){
         throw new RuntimeException(
            "List Error: get() called on an empty List");
      }
      return cursor.data;
   }


   // equals(List L) 
   // Returns true if this List and L are the same integer
   // sequence. The cursor is ignored in both lists.
   boolean equals(List L){
      Node lfront = L.front;
      Node tfront = this.front;
      boolean eq = true;
      if (this.length == L.length){
         while(eq == true && tfront != null){ //while previous data is equal
         eq = (lfront.data == tfront.data);
         lfront = lfront.next;
         tfront = tfront.next;
      }
      return eq;    
         }else{
            return false; //length != length, not equal
         }
      }
   

   // Manipulation Procedures -------------------------------------------------

   // void clear()   
   // Resets this List to its original empty state.
   void clear() {
      front = back = cursor = null;
      length = 0;
      index = -1;
   }

   // void moveFront()
   // If List is non-empty, places the cursor under the front element,
   // otherwise does nothing.
   void moveFront() {
      if(length > 0) {
         cursor = front;
         index = 0;
      } 
}
   
   // void moveBack()
   // If List is non-empty, places the cursor under the back element,
   // otherwise does nothing.
   void moveBack() {
      if(length > 0) {
         cursor = back;
         index = length - 1;
      }
}
   // void movePrev()
   // If cursor is defined and not at front, moves cursor one step
   // toward front of this List, if cursor is defined and at front,
   // cursor becomes undefined, if cursor is undefined does nothing.
   void movePrev(){
      if(cursor == front){    
         cursor = null;
         index = -1;
      }else { 
         cursor = cursor.previous;
         index--;
      }
   }

   // void moveNext()
   // If cursor is defined and not at back, moves cursor one step toward 
   // back of this List, if cursor is defined and at back, cursor becomes
   // undefined, if cursor is undefined does nothing.
   void moveNext(){
      if(cursor == back){
         cursor = null;
         index = -1;
      }else {
         cursor = cursor.next;
         index++;
      }
   }

   // void prepend(int data)
   // Insert new element into this List. If List is non-empty,
   // insertion takes place before front element.

   void prepend(int data){
      Node temp = new Node(data);
      if( front == null){
         front = temp;
         back = temp;
         cursor = front;
      }else{
         front.previous = temp;
         temp.next = front;
         temp.previous = null;
         front = temp;
         }
         length++;
      }
   
   // void append(int data)
   // Insert new element into this List. If List is non-empty,
   // insertion takes place after back element.

     void append(int data){
      Node temp = new Node(data);
      if( back == null){
         front = temp;
         back = temp;
         cursor = back;
      }else{
         back.next = temp;
         temp.previous = back;
         back = temp;
         temp.next = null;
      }
      length++;  
   }

 // void insertBefore(int data)
 // Insert new element before cursor.
 // Pre: length() > 0, index() >= 0

   void insertBefore(int data){
      if(index < 0|| cursor == null){
         throw new RuntimeException(
            "List Error: insertBefore() called with an undefined index on List");
      }
      if(length <= 0){
         throw new RuntimeException(
            "List Error: insertBefore() called on an empty List");
      }
      Node temp = new Node(data);
      if(length > 1){
         temp.previous = cursor.previous;
         temp.next = cursor;
         cursor.previous.next = temp;
         cursor.previous = temp;
         length++;
      }
   }

   // void insertAfter(int data)
   // Inserts new element after cursor. 
   // Pre: length() > 0, index() >= 0
   void insertAfter(int data){
      if(length() <= 0 && index < 0)
            throw new RuntimeException("List error: insertAfter() called on empty List");
        if(cursor == back) append(data);
        else {
            Node temp = new Node(data);
            cursor.next.previous = temp;
            temp.next = cursor.next;
            temp.previous = cursor;
            cursor.next = temp;
            length++;
        }
    }

   // deleteFront()
   // Deletes the front element.
   // Pre: length() > 0
   void deleteFront() {
      if(length == 0){
         throw new RuntimeException(
            "List Error: deleteFront() called on an empty List");
      }
      if(cursor == front) {
         cursor = null;
         index = -1;
      }
      front = front.next;
      front.previous = null;
      length--;
   }
   
   // deleteback()
   // Deletes the back element.
   // Pre: length() > 0
   void deleteBack() {
      if(length==0){
         throw new RuntimeException(
            "List Error: deleteBack() called on an empty List");
      }
      if(cursor == back) {
         cursor = null;
         index = -1;
      }
      back = back.previous;
      back.next = null;
      length--;
   }

   // delete()
   // Deletes cursor element, making cursor undefined.
   // Pre: length() > 0, index() >= 0
   void delete() {
      if(index < 0){
         throw new RuntimeException(
            "List Error: delete() called with an undefined index on List");
      }
      if(length == 0){
         throw new RuntimeException(
            "List Error: delete() called on an empty List");
      }
      if(cursor == back)
         deleteBack();
      else if(cursor == front)
         deleteFront();
      else {
         cursor.previous.next = cursor.next;
         cursor.next.previous = cursor.previous;
         cursor = null;
         index = -1;
         length--;
      }
}

   // Other Functions ---------------------------------------------------------

   // toString()
   // Overrides Object's toString method. Returns a String
   // representation of this List consisting of a space 
   // separated sequence of integers, with front on left.

   public String toString(){
         String str = "";
         for(Node N=front; N!=null; N=N.next){
            str += N.toString() + " ";
         }
         return str;
}
   // copy()
   // Returns a new List representing the same integer sequence as this
   // List. The cursor in the new list is undefined, regardless of the
   // state of the cursor in this List. This List is unchanged.

   List copy(){
      List L = new List();
      Node N = this.front;

      while( N!=null ){
         L.append(N.data);
         N = N.next;
      }
      return L;
   }
}
   // I don't understand the optional part concat(List L)