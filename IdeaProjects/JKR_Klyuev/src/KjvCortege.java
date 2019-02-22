//import java.lang.*;

public class KjvCortege implements ITuple, Cloneable, Comparable<KjvCortege>
{
    //private int a;
    int a;

    public KjvCortege(){
        this.a=0;
    }

    public KjvCortege(int a)
    { this.a = a; }

    public void TestInstanceof() // “тестирование instanceof для параметризованных классов
    {
        Pair<Integer> prInt = new Pair<>(2,3);

        if (IsInstanceOfPairString(prInt))
            System.out.println("prInt экземпляр класса Pair<String>");
        else
            System.out.println("prInt не экземпляр класса Pair<String>");

    }

    @Override
    public KjvCortege max (ITuple o) // фактический аргумент должен быть KjvCortege или производным от него !!!
    {
        if (compareTo((KjvCortege)o) > 0)
            return this;
        else
            return (KjvCortege)o;
    }

    @Override
    public KjvCortege Plus(ITuple o)
    {
        KjvCortege t = new KjvCortege(a+o.value());
        return t;
    }

    @Override
    public int value() { return a; }

    @Override
    public  int less(ITuple t) // фактический аргумент может и Ќ≈ Ѕџ“№ KjvCortege ????
    {                        // «десь тип обьекта t может быть любым, производным от ITuple
        return a - t.value();
    }
    @Override
    public KjvCortege  myClone()
    {
        return new KjvCortege(a);
    }

    @Override
    public KjvCortege  clone ()
    {
        return new KjvCortege(a);
    }

    @Override
    public int compareTo (KjvCortege o)
    {
        return a - o.a;
    }

    @Override
    public String toString()
    { return "["+a+"]"; }

    <T> boolean IsInstanceOfPairString(T a) // “естирование instanceof дл€ параметризованных классов
    {
        //if (a instanceof Pair<String>)
        if (a instanceof Pair<?>) // мы не можем указать, что a экземпл€р класса Pair<String>
            return true;
        else
            return false;
    }

}

class KjvCortegeD implements Cloneable, ITuple,Comparable<KjvCortegeD>
{
    protected int a;

    public KjvCortegeD(){
        this.a=0;
    }

    public KjvCortegeD(int a)
    { this.a = a; }

    @Override
    public KjvCortegeD max (ITuple o) // ‘актический аргумент должен быть KjvCortegeD или производным от него !!!
    {
        if (compareTo((KjvCortegeD)o) > 0)
            return this;
        else
            return (KjvCortegeD)o;
    }
    @Override
    public KjvCortegeD Plus(ITuple o)
    {
        KjvCortegeD t = new KjvCortegeD(a+o.value());
        return t;
    }

    @Override
    public int value() { return a; }

    @Override
    public  int less(ITuple t)  // ‘актический аргумент может и Ќ≈ Ѕџ“№ KjvCortegeD ????
    {
        return a - t.value();
    }

    @Override
    public KjvCortegeD  myClone()
    {
        return new KjvCortegeD(a);
    }

    @Override
    public KjvCortegeD clone()
    {
        return new KjvCortegeD(a);
    }

    @Override
    public int compareTo (KjvCortegeD o)
    {
        return a - o.a;
    }

    @Override
    public String toString()
    { return "["+a+"]"; }
}

class KjvCortegeDD extends KjvCortegeD
//class KjvCortegeDD extends KjvCortegeD implements Comparable<KjvCortegeDD> // Compile error: The interface Comparable
        // cannot be implemented more than once with different arguments:
        // Comparable<KjvCortegeD> and Comparable<KjvCortegeDD>
{
    int b;

    public KjvCortegeDD(){
        super();
        this.b=0;
    }

    public KjvCortegeDD(int a, int b)
    { super(a); this.b = b; }

    @Override
    public KjvCortegeDD max (ITuple o) // ‘актический аргумент должен быть KjvCortegeDD
    {                                // или производным от него !!!
        if (compareTo((KjvCortegeDD)o) > 0)
            return this;
        else
            return (KjvCortegeDD)o;
    }

    @Override
    public KjvCortegeDD Plus(ITuple o)
    {
        KjvCortegeDD t = new KjvCortegeDD(a+((KjvCortegeDD)o).a, b+o.value());
        return t;
    }

    @Override
    public int value() { return b; }

    @Override
    public  int less(ITuple t)
    {
        return b - t.value();
    }

    @Override
    public KjvCortegeDD myClone()
    {
        return new KjvCortegeDD(a,b);
    }

    @Override
    public KjvCortegeDD clone()
    {
        return new KjvCortegeDD(a,b);
    }

    public int compareTo (KjvCortegeDD o)
    {
        return b - o.b;
    }

    @Override
    public String toString()
    { return "["+b+"]"; }

}

