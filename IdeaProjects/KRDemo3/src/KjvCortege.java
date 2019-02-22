import java.lang.*;

public class KjvCortege implements ITuple,Comparable<KjvCortege>
{
    private int a;

    public KjvCortege(int a)
    { this.a = a; }
    @Override
    public KjvCortege max (ITuple o) // ‘актический аргумент должен быть KjvCortege или производным от него !!!
    {
        if (compareTo((KjvCortege)o) > 0)
            return this;
        else
            return (KjvCortege)o;
    }
    public int value() { return a; }

    //	public  int less(ITuple<? extends KjvCortegeD> t)
    public  int less (ITuple t) // ‘актический аргумент может и Ќ≈ Ѕџ“№ KjvCortege ????
    {
        return a - t.value();
    }

    public int compareTo (KjvCortege o)
    {
        return a - o.a;
    }

    public String toString()
    { return "["+a+"]"; }
}

class KjvCortegeD implements ITuple,Comparable<KjvCortegeD>
{
    private int a;

    public KjvCortegeD(int a)
    { this.a = a; }

    public KjvCortegeD max (ITuple o) // ‘актический аргумент должен быть KjvCortegeD или производным от него !!!
    {
        if (compareTo((KjvCortegeD)o) > 0)
            return this;
        else
            return (KjvCortegeD)o;
    }
    public int value() { return a; }

    //	public  int less(ITuple<? extends KjvCortegeD> t)
    public  int less(ITuple t)  // ‘актический аргумент может и Ќ≈ Ѕџ“№ KjvCortegeD ????
    {
        return a - t.value();
    }

    public int compareTo (KjvCortegeD o)
    {
        return a - o.a;
    }
    public String toString()
    { return "["+a+"]"; }
}

class KjvCortegeDD extends KjvCortegeD //implements ITuple
//class KjvCortegeDD extends KjvCortegeD implements ITuple,Comparable<KjvCortegeDD>
//The interface Comparable cannot be implemented more than once with different arguments:
//Comparable<KjvCortegeD> and Comparable<KjvCortegeDD>
{
    private int b;

    public KjvCortegeDD(int a, int b)
    { super(a); this.b = b; }

    public KjvCortegeDD max (ITuple o) // ‘актический аргумент должен быть KjvCortegeDD или производным от него !!!
    {
        if (compareTo((KjvCortegeDD)o) > 0)
            return this;
        else
            return (KjvCortegeDD)o;
    }
    public int value() { return b; }

    //	public  int less(ITuple<? extends KjvCortegeD> t)
    public  int less(ITuple t)
    {
        return b - t.value();	// ??? «десь уже t должен быть типа KjvCortegeDD
    }

    public int compareTo (KjvCortegeDD o)
    {
        return b - o.b;
    }
    public String toString()
    { return "["+b+"]"; }
}

