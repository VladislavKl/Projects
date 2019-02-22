import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class KjvArrayCortege<T extends ITuple & Comparable<T>> implements Cloneable, Iterable<T>
{
    public KjvArrayCortege(T... ar)
    {
        this.ar = ar;
    }

    public T maxTuple()
    {
        T xmax = ar[0];
        for (T e: ar)
        {
            xmax = (T) e.max(xmax);
        }
        return xmax;
    }

    //public void setFirstCortege(T crt)
    public <V extends T> void setFirstCortege(V crt)  // !!! Ќе добавл€ет новых возможностей
    {
        ar[0] = crt;
        v = crt;
    }

    public void Modify(KjvArrayCortege<? super KjvCortegeD > aCort)
    {
        KjvCortegeD  b = new KjvCortegeD(3);
        aCort.setFirstCortege(b); // Ok! ћожно гарантировать корректность присваивани€ объекта
        // типа KjvCortegeD объекту, от которого KjvCortegeD производен
    }

    public int compareMaxTuple(KjvArrayCortege<? extends ITuple > aCort)
    {
        //KjvCortegeD  b = new KjvCortegeD(3);
        //aCort.setFirstCortege(b); // ќшибка! Ќевозможно гарантировать корректность присваивани€
        // объекта типа KjvCortegeD объекту, тип которого производен
        // от ITuple
		/*
		if (maxTuple().less(aCort.maxTuple())<0)
			return -1;
		else
			return 1;
		*/
        T t = maxTuple();
        ITuple w = aCort.maxTuple();
        if (t.less(w)<0)
            return -1;
        else
            return 1;

    }

    @SuppressWarnings("unchecked")
    public T Sum()
    {
        T xsum = (T)ar[0].myClone();
        for (int i = 1; i < ar.length; ++i)
            xsum = (T)xsum.Plus(ar[i]);
        return xsum;
    }

    public <V extends ITuple & Comparable<V>> int compareSumTuples(KjvArrayCortege<V> aCort)
    { T xSum1 = Sum();
        V xSum2 = aCort.Sum();
        if (xSum1.less(xSum2)<0)
            return -1;
        else
            return 1;

    }

    public void setMinMaxTuples(KjvArrayCortege<? extends ITuple > aCort,
                                Pair<? super ITuple> result)
    {
        if (aCort.maxTuple().less(maxTuple())<0)
        {
            result.setFirst(maxTuple());
            result.setSecond(maxTuple());
        }
        else
        { result.setFirst((T)aCort.maxTuple());
            result.setSecond((T)aCort.maxTuple());
        }
    }

    public KjvArrayCortege<T>  clone ()
    {
        return new KjvArrayCortege<>(ar);
    }

    public T at(int n) throws ArrayIndexOutOfBoundsException{
       return ar[n];
    }


    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int CurrentIndex = 0;

            @Override
            public boolean hasNext() {
                return CurrentIndex < ar.length && ar[CurrentIndex]!= null;
            }

            @Override
            public T next() {
                if (hasNext())
                    return ar[CurrentIndex++];
                else
                    throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
            if(!hasNext()) {
                ArrayList<T> temp = new ArrayList<>();
                Collections.addAll(temp, ar);
                temp.remove(CurrentIndex);
                ar = (T[]) temp.toArray();
            }
            }
        };
        return it;
    }

    private T[] ar;
    private T v;

}
