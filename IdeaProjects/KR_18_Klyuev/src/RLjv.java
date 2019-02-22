import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

//public class LFjv<T extends IMatrix<?>&Comparable<T>>
public class RLjv<T extends IList&Comparable<T>>
        implements Cloneable, Iterable<T>
{
    LinkedList<T> ar;
    T v;

    public int size(){
        return ar.size();
    }

    public T getValue(int i){
        return ar.get(i);
    }

    public RLjv(LinkedList<T> ar)
    {
        this.ar = ar;
    }

    @SuppressWarnings("unchecked")
    public T sum()
    {
        T xsum = ar.get(0);
        for (int i = 1; i < ar.size(); ++i) {
            try {
                xsum = (T)xsum.Plus(ar.get(i));
            } catch (MyExcClass myExcClass) {
                myExcClass.printStackTrace();
            }
        }
        return xsum;
    }


    public T avg(){
        return ar.get(ar.size()/2);
    }

    public void setMaxAndAvgItems(RLjv<? extends IList > other, Pair<? super IList> pair){
        if ((this.maxTuple().greater(other.avg()))<0) {
            pair.setFirst(this.maxTuple());
            pair.setSecond(this.avg());
        }
        if ((this.maxTuple().less(other.avg()))<0) {
            pair.setFirst(this.maxTuple());
            pair.setSecond(this.avg());
        }

    }

    public RLjv(T... ar1)
    {
        ar=new LinkedList<T>();
        for(int i=0;i<ar1.length;i++){
            ar.add(ar1[i]);
        }
    }

    public T maxTuple()
    {
        T xmax = ar.getFirst();
        for (T e: ar)
        {
            xmax = (T) e.max(xmax);
        }
        return xmax;
    }

    public T minTuple()
    {
        T xmin = ar.getFirst();
        for (T e: ar)
        {
            xmin = (T) e.min(xmin);
        }
        return xmin;
    }

    public T firstFieldsMax()
    {
        T xmax = ar.getFirst();
        for (T e: ar)
        {
            xmax = (T) e.max(xmax);
        }
        return xmax;
    }

    public void compare1stFIMRecords(RLjv<T> other)
    {
        T xmax1=this.maxTuple();
        T xmax2=other.maxTuple();
        if(xmax1.max(xmax2)==xmax1){System.out.println("First big ");}
        else if(xmax1.max(xmax2)==xmax2) {System.out.println("Second big ");}
        else System.out.println("equal");
    }

    @Override
    public  RLjv<T> clone() throws CloneNotSupportedException
    {
        LinkedList<T> newData = new LinkedList<>(ar);
        //newData = Arrays.copyOf(ar , ar.length);
        RLjv<T> newQ = (RLjv<T>)super.clone();
        newQ.ar = newData;
        return newQ;
    }
    //public void setFirstCortege(T crt)
    //public <V extends T> void setFirstCortege(V crt)  // !!! Ќе добавл€ет новых возможностей
    public <V extends T> void setFirstCortege(V crt)  // !!! Ќе добавл€ет новых возможностей
    {
        //ar[0] = crt;
        v = crt;
    }

    //public int compareMaxTuple(LFjv<? extends IMatrix<? super T> > aCort)
    public int compareMaxTuple(RLjv<? extends IList > aCort)
    {
        KjvCortege1  b = new KjvCortege1(3);
        //aCort.setFirstCortege(b); // ќшибка! Ќевозможно гарантировать корректность присваивани€
        // объекта типа KjvCortegeD объекту, тип которого производен
        // от IMatrix
		/*
		if (maxTuple().less(aCort.maxTuple())<0)
			return -1;
		else
			return 1;
		*/
        T t = maxTuple();
        IList w = aCort.maxTuple();
        if (t.less(w)<0)
            return -1;
        else
            return 1;

    }
    public int compareMinTuple(RLjv<? extends IList > aCort)
    {
        KjvCortege1  b = new KjvCortege1(3);
        //aCort.setFirstCortege(b); // ќшибка! Ќевозможно гарантировать корректность присваивани€
        // объекта типа KjvCortegeD объекту, тип которого производен
        // от IMatrix
		/*
		if (maxTuple().less(aCort.maxTuple())<0)
			return -1;
		else
			return 1;
		*/
        T t = maxTuple();
        IList w = aCort.minTuple();
        if (t.greater(w)<0)
            return -1;
        else
            return 1;

    }

    public T at(int n) throws ArrayIndexOutOfBoundsException{
        return ar.get(n);
    }



    public void setMinMaxTuples(RLjv<? extends IList > aCort,
                                Pair<? super IList> result)
    {
        if (aCort.maxTuple().less(maxTuple())<0)
        {
            result.setFirst(maxTuple());
            result.setSecond(maxTuple());
        }
        else
        {
            result.setFirst((T)aCort.maxTuple());
            //result.setSecond((T)(maxTuple().max(ar[0])));
            //result.setSecond((maxTuple().max(ar[0])));
            result.setSecond((T)aCort.maxTuple());
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
    @Override
    public RIter<T> iterator() {
        // TODO Auto-generated method stub
        return new RIter<T>(this);
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
