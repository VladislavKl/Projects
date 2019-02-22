
package dequeclass.classes;

public class Node {
    private int info;
    private Node next;
    
    public int getInfo() { return info; }
    public Node getNext() { return next; }
    public void setInfo(int info) { this.info = info; }
    public void setNext(Node next) { this.next = next; }
    
    public Node() {}
    
    public Node(int info) {
        this.info = info;
        next = null;
    }
    
    public Node(Node n) {
        this.info = n.info;
        this.next = n.next;
    }
    
    public Node(int info, Node next) {
        this.info = info;
        this.next = next;
    }
}
