/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedList;

/**
 *
 * @author EHVN
 */
public class LinkedList {
    private Node head;
    private Node tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public LinkedList(INodeValue value) {
        Node newNode = new Node(value);
        this.head = newNode;
        this.tail = newNode;
    }

    public void add(INodeValue value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    public boolean remove(INodeValue value) {
        if (head == null) {
            return false;
        }
        if (head.getValue().equals(value)) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            return true;
        }

        Node current = head;
        while (current.getNext() != null) {
            if (current.getNext().getValue().equals(value)) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public Node getHead() {
        return head;
    }
    public Node getTail() {
        return tail;
    }
}
