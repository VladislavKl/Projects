
package dequeclass.classes;
import java.util.*;

public class KjvIterator implements Iterator<Integer> {
    private KjvDeque deque;
    private Node current;
    private Node prev;
    private Node pprev;
    
    public KjvIterator(KjvDeque deque) {
        this.deque = deque;
        current = deque.getFirst();
        prev = null;
        pprev = null;
    }
    
    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Integer next() {
        if(!hasNext()) return null;
        Integer item = new Integer(current.getInfo());
        pprev = prev;
	prev = current;
	current = current.getNext();
	return item;
    }

    @Override
    public void remove() { 
        if(pprev == null)
            deque.setFirst(deque.getFirst().getNext());
	else
            pprev.setNext(current);
	prev = pprev;
	if(current == null)
	    deque.setLast(prev);
    } 
}
