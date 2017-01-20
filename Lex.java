// Lex.java
// PA1 CMPS101
// Jingyan Ran (jran4@ucsc.edu)
// based on FileIO.java provided on classwebsite
// input a file with strings, output file with the new lex order.
// construct the list, and sort them based on Insertionsort, by the cursorindex.
// import ArrayList for convinience because ArrayList is dynamic in size.

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class Lex {
    public static void main (String[] args) throws IOException {
  
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));
        int number = 0;                         //count for input fine line number
        ArrayList<String> arlist = new ArrayList<String>();

        if(args.length != 2) {
            System.err.println("Usage: Lex infile outfile");  //Usage
            System.exit(1);
        }
       
        while(in.hasNextLine()) {               // count number of lines 
            number++;
            arlist.add(in.nextLine());          // use arraylist for convinience, add element
                                                // to the arraylist
        }
      
                                      
        List list = new List();
        int j;
        list.append(0);                          // uses arraylistobject size method
        for(int i = 1; i < arlist.size(); i++) { // Sorting algorithm based on insertion sort
            j = i - 1;                    
            list.moveBack();
            String temp = arlist.get(i);
            while(j >= 0 && temp.compareTo(arlist.get(list.get())) <= 0) { //provided on website
                list.movePrev();
                j--;
            }
            if(list.index() >= 0){
            	list.insertAfter(i);
            }
            else {
            	list.prepend(i);
            }
        }

        // Writing to the output file
        list.moveFront();
        while(list.index() >= 0) {
            out.println(arlist.get(list.get()));
            list.moveNext();
        }

        in.close();
        out.close();
    }
}