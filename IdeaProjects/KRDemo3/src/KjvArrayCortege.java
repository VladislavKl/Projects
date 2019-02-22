//public class KjvArrayCortege<T extends ITuple<?>&Comparable<T>>
public class KjvArrayCortege <T extends ITuple&Comparable<T>>
{
    T[] ar;
    T v;
    public KjvArrayCortege(T[] ar)
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
    //public <V extends T> void setFirstCortege(V crt)  // !!! Ќе добавл€ет новых возможностей
    public <V extends T> void setFirstCortege(V crt)  // !!! Ќе добавл€ет новых возможностей
    {
        //ar[0] = crt;
        v = crt;
    }
    public void Modify(KjvArrayCortege<? super KjvCortegeD > aCort)
    {
        KjvCortegeD  b = new KjvCortegeD(3);
        aCort.setFirstCortege(b); // Ok! ћожно гарантировать корректность присваивани€ объекта
        // типа KjvCortegeD объекту, от которого KjvCortegeD производен
    }
    //public int compareMaxTuple(KjvArrayCortege<? extends ITuple<? super T> > aCort)
    public int compareMaxTuple(KjvArrayCortege<? extends ITuple > aCort)
    {
        KjvCortegeD  b = new KjvCortegeD(3);
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
    public void setMinMaxTuples(KjvArrayCortege<? extends ITuple > aCort,
                                Pair<? super ITuple> result)
    {
        if (aCort.maxTuple().less(maxTuple())<0)
        {
            result.setFirst(maxTuple());
            result.setSecond(maxTuple());
        }
        else
        {
            //result.setFirst(aCort.maxTuple());
            //result.setSecond((T)(maxTuple().max(ar[0])));
            //result.setSecond((maxTuple().max(ar[0])));
           // result.setSecond(aCort.maxTuple());
        }

		/*
		//if (aCort.maxTuple().l)
		result.setFirst(aCort.maxTuple());
		//result.setSecond((T)(maxTuple().max(ar[0])));
		//result.setSecond((maxTuple().max(ar[0])));
		result.setSecond(maxTuple());
		//result.setSecond(aCort.maxTuple());
		 */
    }
}

class Pair<T extends Object>
{
    public Pair() { first = null; second = null; }
    public Pair(T first, T second) { this.first = first;  this.second = second; }

    public T getFirst() { return first; }
    public T getSecond() { return second; }

    public void setFirst(T newValue) { first = newValue; }
    public void setSecond(T newValue) { second = newValue; }

    private T first;
    private T second;
}
