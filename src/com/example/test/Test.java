package com.example.test;

import java.util.*;
import java.util.Map.Entry;
import java.io.File;
import java.util.Collections;
import org.w3c.dom.Node;

public class Test {
    int size = 0;

    public static Entry<String, Integer> count_ARRAY(String[] tokens) {
        int CAPACITY = 1000000;
        int counter = 0;
        String[] words = new String[CAPACITY];
        int[] counts = new int[CAPACITY];
        for (int j = 0; j < tokens.length; j++) {
            String token = tokens[j];
            for (int i = 0; i < CAPACITY; i++) {
                if (words[i] == null) {
                    words[i] = token;
                    counts[i] = 1;
                    break;
                } else if (words[i].equals(token))
                    counts[i] = counts[i] + 1;
            }
        }
        for(String item:words){
            if(item != null){
                counter++;
            }
        }
        System.out.println("The word count is: " + counter);
        int maxCount = 0;
        String maxWord = "";
        for (int i = 0; i < CAPACITY & words[i] != null; i++) {
            if (counts[i] > maxCount) {
                maxWord = words[i];
                maxCount = counts[i];
            }
        }
        return new AbstractMap.SimpleEntry<String, Integer>(maxWord, maxCount);
    }
    public static Entry<String, Integer> count_LINKED_LIST(String[] tokens) {
        Hashtable<String, Integer> list = new Hashtable<>();

        for(int n = 0 ; n < tokens.length ; n++){
            String word = tokens[n];
            boolean found = false;
            for(int i = 0 ; i < list.size() ; i++){
                if(list.containsKey(word)){
                    list.replace(word, list.get(word).intValue() + 1);
                    found = true;
                    break;

                }

            }
            if(!found){
                list.put(word, 1);
            }
        }
        int maxCount = 0;
        String maxWord = "";
        int index = 0;
        Set<String> keys = list.keySet();// plan: create an arraylist, dump all the keys from the map into it.
        //sort the arraylist, iterate over the sorted arraylist, arraylist.get() returns String(mostly likely key
       Object[] newTokens = list.keySet().toArray();
//        for(Object item : list.values()){
//            e.add(item);
//        }
        for(String key:keys) {
     //       System.out.println(list.get(key).);
            int count = list.get(key);
           // e.next().getKey();

            //System.out.println(e.next().getKey());
            if (count > maxCount) {
                maxWord = newTokens[index].toString();
                // maxWord = list.get(key).toString();
                maxCount = count;
            }
            index++;
        }
//        for (int i = 0; i < list.size () ; i ++) {
//            int count = list.
//            if ( count > maxCount ) {
//                maxWord = list.get(i).getKey() ;
//                maxCount = count ;
//            }
//        }
        return new AbstractMap . SimpleEntry < String , Integer >( maxWord, maxCount );

        ///return new AbstractMap.SimpleEntry<String, Integer>(maxWord, maxCount);
    }

    public static String[] readText(String PATH) throws Exception{
        Scanner doc = new Scanner(new File(PATH)).useDelimiter("[^a-zA-Z]+");
        int length = 0;
        while(doc.hasNext()){
            doc.next();
            length++;
        }
        String[] tokens = new String[length];
        Scanner s = new Scanner(new File(PATH)).useDelimiter("[^a-zA-Z]+");
        length = 0;
        while(s.hasNext()){
            tokens[length] = s.next().toLowerCase();
            length++;
        }
        doc.close();

        return tokens;
    }


    public static void main(String[] args) throws Exception{

        String word;
        String PATH = "../Test.txt";
        String[] tokens = readText(PATH);
        long startTime = System.currentTimeMillis();
        Entry<String, Integer> entry = count_LINKED_LIST(tokens);
        long endTime = System.currentTimeMillis();
        String time = String.format("%12d", endTime - startTime);
        System.out.println("time\t" + time + "\t" + entry.getKey() + ":" + entry.getValue());//CANNOT FIND LINKED LIST ALTERNATIVE METHOD THAT GIVES THE CORRECT WORD AND IS FASTER THAN ~530 ms



        tokens = readText(PATH);
        startTime = System.currentTimeMillis();
        entry = count_ARRAY(tokens);
        endTime = System.currentTimeMillis();
        time = String.format("%12d", endTime - startTime);
        System.out.println("time\t" + time + "\t" + entry.getKey() + ":" + entry.getValue());
    }
}

//going to do count_LINKED_LIST using Hashtable.
//for alphanumberic key, divide the sum of ASCII codes in a key by the number of available
//addresses, n, and take the remainder

//another method: Folding method, divides key into equal parts then adds the parts together
//The telephone number 01452 8345654 becomes 01 + 45 + 28 + 34 + 56 + 54 = 218
//depending on size of table, may then divide by some constant and take remainder

//sometimes if you generate the same hash code for different keys both items cannot
//be stored in the same location in memory so a collision occurs.
//so to fix the collision a linear search is used to find the next avialable slot
//for the second element.

//count_LINKED_LIST: on the smallest text input there is a tie between "there" and "are" so
//count_LINKED_LIST returns "are", count_ARRAYS returns "there", all other inputs from 200 - 10k get same result.