
package dequeclass.classes;
import java.util.*;

public class KjvDeque extends AbstractCollection<Integer> implements Iterable<Integer> {
    private Node first;
    private Node last;
    
    public Node getFirst() { return first; }
    public Node getLast() { return last; }
    public void setFirst(Node first) { this.first = first; }
    public void setLast(Node last) { this.last = last; }
    
    public KjvDeque() {}
    
    public KjvDeque(KjvDeque d) {
        this.first = d.getFirst();
        this.last = d.getLast();
    }
    
    public boolean isEmpty() {
        if(this.first == null) return true;
        return false;
    }
    
    public int size() {
        if(first == null) return 0;
        Node cur = new Node(first);
        int count = 0;
        while(cur != null) {
            count++;
            cur = cur.getNext();
        }
        return count;
    }
    
    public void clear() {
        first = last = null;
    }
    
    public int front() { return first.getInfo(); }
    public int back() { return last.getInfo(); }
    
    public void pushFront(int a) {
        Node n = new Node(a, first);
        if(first == null)
            last = n;
        first = n;
    }
    
    public void pushBack(int a) {
        Node n = new Node(a, null);
        if(last == null)
            first = n;
        else
            last.setNext(n);
        last = n;
    }
    
    public int popFront() throws NullPointerException {
        int res = first.getInfo();
        first = first.getNext();
        return res;
    }
    
    public int popBack() throws NullPointerException {
        if(last == first)
            return this.popFront();
        Node temp = first;
        Node prev = first;
        while(temp.getNext() != null) {
            prev = temp;
            temp = temp.getNext();
        }
        int res = temp.getInfo();
        prev.setNext(null);
        last = prev;
        return res;
    }
    
    public void swap(KjvDeque d2) {
        Node temp = new Node();
        temp = first;
        first = d2.getFirst();
        d2.setFirst(temp);
        temp = last;
        last = d2.last;
        d2.setLast(temp);     
    }
    
    public void read() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Кол-во элементов: ");
            int n = sc.nextInt();
            System.out.println("Введите элементы: ");
            while(n != 0) {
                this.pushBack(sc.nextInt());
                n--;
            }
        } catch(InputMismatchException e) {
            System.err.println("Некорректный ввод!");
        }
        
    }
    
    public void show() {
        for(Node it = first; it != null; it = it.getNext())
            System.out.print(it.getInfo() + " ");
        System.out.println();
    }
    
    public KjvDeque plus(KjvDeque d) {
        KjvDeque temp = new KjvDeque();
        Node n1 = new Node();
        Node n2 = new Node();
        n1 = this.first;
        n2 = d.first;
        while(n1 != null && n2 != null) {
            temp.pushBack(n1.getInfo() + n2.getInfo());
            n1 = n1.getNext();
            n2 = n2.getNext();
        }
        while(n1 != null) {
            temp.pushBack(n1.getInfo());
            n1 = n1.getNext();
        }
        while(n2 != null) {
            temp.pushBack(n2.getInfo());
            n2 = n2.getNext();
        }
        return temp;
    }

    public KjvDeque plusTo(KjvDeque d) {
        Node n1 = new Node();
        Node n2 = new Node();
        n1 = this.first;
        n2 = d.first;
        while(n1 != null && n2 != null) {
            n1.setInfo(n1.getInfo() + n2.getInfo());
            n1 = n1.getNext();
            n2 = n2.getNext();
        }
        while(n2 != null) {
            this.pushBack(n2.getInfo());
            n2 = n2.getNext();
        }
        return new KjvDeque(this);
    }
    
    public boolean isEqual(KjvDeque d) {
        if(this == d) return true;
        if(d == null) return false;
        if(this.size() != d.size()) return false;
        Node n1 = new Node(this.first);
        Node n2 = new Node(d.getFirst());
        while(n1 != null) {
            if(n1.getInfo() != n2.getInfo())
                return false;
            n1 = n1.getNext();
            n2 = n2.getNext();
        }
        return true;
    }

    public boolean isNotEqual(KjvDeque d) {
        if(this.isEqual(d))
            return false;
        return true;
    }
    
    @Override
    public KjvIterator iterator() {
        return new KjvIterator(this);
    }
}