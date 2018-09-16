package com.example.test;

public class LinkedList {
    Node head;


    public void insert(int data)
    {
        Node node = new Node();//new Node object created
        node.data = data;
        node.next = null;

        if(head == null){//if no head
            head = node;
        }
        else{//if head is there
            Node n = head;
            while(n.next != null){
                n = n.next;//if n has a next element assign n to the next element
            }
            n.next = node;//assigning new node as the last element of the list
        }


        //creating a new node
    }
    public void insertAtStart(int data){
        Node node = new Node();
        node.data = data;
        node.next = null;//newly created node points to no other node
        node.next = head;
        head = node;// the head node now points to the newly created node
    }
    public void insertAt(int index, int data){
        Node node = new Node();
        node.data = data;
        node.next = null;
        if(index == 0){//inserting at the start of the linked list
            insertAtStart(data);
        }
        else{
            Node n = head;
            for(int i = 0; i < index - 1; i++){
                n = n.next;//put n to last node in the linked list
            }
            node.next = n.next;//inserts newly created node right after node the node occupying index + 1
            n.next = node;// node in position index + 1 now points to newly created node
        }
    }

    public void show(){
        Node node = head;
        while(node.next != null){
            System.out.println(node.data);//printing all the elements in the linked list
            node = node.next;
        }
    }

}
