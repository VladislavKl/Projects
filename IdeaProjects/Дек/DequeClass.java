
package dequeclass;
import dequeclass.classes.*;
import java.util.*;

public class DequeClass {
    public static void main(String[] args) throws NullPointerException {
        KjvDeque d = new KjvDeque();
        d.pushBack(1);
        d.pushFront(2);
        d.pushFront(0);
        d.pushBack(4);
        d.pushBack(5);
        d.pushBack(6);
        KjvDeque d2 = new KjvDeque();
        d2.pushBack(4);
        d2.pushBack(3);
        d.show();
        d2.show();
        KjvDeque sum = new KjvDeque();
        sum = d.plus(d2);
        sum.pushBack(10);
        KjvIterator it = new KjvIterator(sum);
        while(it.hasNext())
            System.out.print(it.next() + " ");
        //sum.clear();
        try {
            sum.popBack();
        } catch(NullPointerException e) {
            System.err.println("Empty");
        }
        System.out.println();
        
        /*Object[] ob = d.toArray();
        Integer[] arr = d.toArray(new Integer[0]);
       
        for(Object x : ob)
            System.out.print(x + " ");
        System.out.println();
        for(int x : arr)
            System.out.print(x + " ");
        System.out.println();*/
       
        while(!d.isEmpty())
            System.out.print(d.popFront() + " ");
        System.out.println("\n" + d.size());
    }
}
