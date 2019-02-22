
class KjvCortege1 implements IList, Comparable<KjvCortege1>, Cloneable
{
    protected int a;

    public KjvCortege1()
    { this.a = 0; }

    public KjvCortege1(int a)
    { this.a = a; }

    @Override
    public KjvCortege Plus(IList o) throws MyExcClass {
        KjvCortege t = new KjvCortege(a+o.value());
        return t;
    }


    public KjvCortege1 max (IList o)
    {
        if (compareTo((KjvCortege1)o) > 0)
            return this;
        else
            return (KjvCortege1)o;
    }

    @Override
    public int value(){
        return a;
    }

    public int value(int i, int j) throws MyExcClass { return a; }
    @Override
    public KjvCortege1 clone() throws CloneNotSupportedException
    {
        KjvCortege1 newQ = (KjvCortege1)super.clone();
        newQ.a = a;
        return newQ;
    }

    public  int less(IList t)
    {
        try {
            return a - t.value(0,0);
        } catch (MyExcClass e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public IList min(IList o) {
        if (compareTo((KjvCortege1)o) < 0)
            return this;
        else
            return (KjvCortege1)o;
    }

    @Override
    public int greater(IList t) {
        try {
            return t.value(0,0) - a;
        } catch (MyExcClass e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

    public int compareTo (KjvCortege1 o)
    {
        return a - o.a;
    }

    public String toString()
    { return a+" "; }

}

class KjvCorteget extends KjvCortege1 //implements IMatrix
//class KjvCortegeDD extends KjvCortegeD implements IMatrix,Comparable<KjvCortegeDD>
//The interface Comparable cannot be implemented more than once with different arguments:
//Comparable<KjvCortegeD> and Comparable<KjvCortegeDD>
{
    @Override
    public KjvCorteget clone() throws CloneNotSupportedException
    {
        KjvCorteget newQ = (KjvCorteget)super.clone();
        newQ.b = b;
        return newQ;
    }
    private int b;

    public KjvCorteget() {
        super();
        this.b=0;
    }

    public KjvCorteget(int a, int b)
    { super(a); this.b = b; }

    public KjvCorteget max (IList o)
    {
        if (compareTo((KjvCorteget)o) > 0)
            return this;
        else
            return (KjvCorteget)o;
    }
    public KjvCorteget min (IList o)
    {
        if (compareTo((KjvCorteget)o) < 0)
            return this;
        else
            return (KjvCorteget)o;
    }
    public int value(int i, int j) throws MyExcClass { return b; }

    //	public  int less(IMatrix<? extends KjvCortegeD> t)
    public  int less(IList t)
    {
        try {
            return b - t.value(0, 1);
        } catch (MyExcClass e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }

    }
    public  int greater(IList t)
    {
        try {
            return t.value(0,1) - b;
        } catch (MyExcClass e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }
    public int compareTo (KjvCorteget o)
    {
        return b - o.b;
    }
    public String toString()
    { return b+" "; }
}

public class KjvCortege extends KjvCorteget{
    private int c;
    private int d;
    @Override
    public KjvCortege clone() throws CloneNotSupportedException
    {
        KjvCortege newQ = (KjvCortege)super.clone();
        newQ.c = c;
        newQ.d = d;
        return newQ;
    }
    private int b;

    public KjvCortege(int i)
    { super(); this.c = 0;this.d=0; }

    public KjvCortege(int a, int b, int c, int d)
    { super(a,b); this.c = c;this.d=d; }

    public KjvCortege max (KjvCortege o)
    {
        if (compareTo((KjvCortege)o) > 0)
            return this;
        else
            return (KjvCortege)o;
    }
    public KjvCortege min (KjvCortege o)
    {
        if (compareTo((KjvCortege)o) < 0)
            return this;
        else
            return (KjvCortege)o;
    }
    public int value(int i, int j) throws MyExcClass {
        if(i==0&&j==0)
            return a;
        else if(i==0&&j==1)
            return b;
        else if (i==1&&j==0)
            return c;
        else if (i==1&&j==1)
            return d;
        else throw new MyExcClass("(Illegal size index)");
    }

    //	public  int less(IMatrix<? extends KjvCortegeD> t)
    public  int less(KjvCortege o)
    {
        try {
            return -(a*d - b*d - o.value(0,0)*o.value(1,1) + o.value(0,1)*o.value(1,0));
        } catch (MyExcClass e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }
    public  int greater(KjvCortege o)
    {
        try {
            return (a*d - b*d - o.value(0,0)*o.value(1,1) + o.value(0,1)*o.value(1,0));
        } catch (MyExcClass e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }
    public int compareTo (KjvCortege o)
    {
        return a*d - b*d - o.a*o.c + o.b*o.d;
    }
    public String toString()
    { return a+" "+b+" "+c+" "+d;
    }

}
