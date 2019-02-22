
import static java.lang.System.*;
import java.util.*;
public class KjvTest {

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        KjvCortege [] a = {new KjvCortege(1), new KjvCortege(2)};
        KjvArrayCortege<KjvCortege> aJv = new KjvArrayCortege<KjvCortege>(a);
        KjvCortege x = aJv.maxTuple();
        System.out.println("x="+x);
        KjvCortegeD [] b = {new KjvCortegeD(3), new KjvCortegeD(4)};
        KjvArrayCortege<KjvCortegeD> aJv2 = new KjvArrayCortege<KjvCortegeD>(b);
        //KjvArrayCortege<? extends ITuple<? super KjvCortege>> aJv3 = (KjvArrayCortege<? extends ITuple<? super KjvCortege>>) new KjvArrayCortege<KjvCortegeD>(b);

        //if (aJv.compareMaxTuple((KjvArrayCortege<? extends ITuple<? super KjvCortege>>) aJv2)==-1)
        if (aJv.compareMaxTuple(aJv2)==-1)
            System.out.println("aJv < a Jv2");
        else
            System.out.println("aJv >= a Jv2");

        Pair<ITuple> pair = new Pair(new KjvCortege(56),new KjvCortege(78));
        //Pair<Object> pair = new Pair(new KjvCortege(56),new KjvCortege(78));
        //Pair<KjvCortege> pair = new Pair(new KjvCortege(56),new KjvCortege(78));
        aJv.setMinMaxTuples(aJv2,pair);
        System.out.println(pair.getFirst()+" "+pair.getSecond() );

        KjvCortegeD  b1 = new KjvCortegeD(3);
        aJv2.setFirstCortege(b1);
        KjvCortegeDD  b11 = new KjvCortegeDD(3,4);
        aJv2.setFirstCortege(b11);
        //KjvCortege  bi1 = new KjvCortege(3);
        //aJv2.setFirstCortege(bi1);

        Pair<Integer> aaa = new Pair<Integer>(2,3);
        Pair aao = aaa;
        Pair<String> ppp0 = (Pair<String>)aao;
        Pair<String> ppp = new Pair<String>("a","b");
        if (aaa.getClass()==ppp.getClass())
        {
            System.out.println(" Классы равны");
        }
        else
        {
            System.out.println(" Классы различаются");
        }

    }

}
