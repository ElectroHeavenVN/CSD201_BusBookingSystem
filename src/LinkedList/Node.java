/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedList;

/**
 *
 * @author EHVN
 */
public class Node {
    private INodeValue value;
    private Node next;

    public Node(INodeValue value) {
        this.value = value;
        this.next = null;
    }

    public INodeValue getValue() {
        return value;
    }
    public void setValue(INodeValue value) {
        this.value = value;
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }
}
