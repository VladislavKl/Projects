//  @SuppressWarnings("unchecked")	// отключение предупреждений, св€занных с непроверенными операци€ми
//  @SuppressWarnings("rawtypes")	// отключение предупреждений, св€занных с использованием непараметризованных типов
//  @SafeVarargs  // примен€етс€ к методу или конструктору и указывает, что код не осуществл€ет потенциально опасных
// операций со своим varargs-параметром (параметр, принимающий произвольное число параметров).
// ѕример:
// @SafeVarargs
// static <E> E[] array(E ... ar) { return ar; }

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

//import static java.lang.System.*;
//import java.util.*;
public class KjvTest {

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Pair<Integer> prInt = new Pair<>(2,3);
        //if (prInt instanceof Pair<Integer>) // Compile error: Cannot perform instanceof check against parameterized type
        //  System.out.println(prInt);        // Pair<Integer>. Use the form Pair<?> instead since
        // further generic type information will be erased at runtime

        if (prInt instanceof Pair<?>)  // ≈сли условие истинно,
            System.out.println("prInt=" + prInt);
        KjvCortege tpl = new KjvCortege(78);
        //ITuple tpl = new KjvCortege(78);
        KjvCortege tpl2 =  tpl.clone();
        KjvCortege [] a = {new KjvCortege(1), new KjvCortege(2)};
        KjvArrayCortege<KjvCortege> aJv = new KjvArrayCortege<KjvCortege>(a);
        KjvCortege x = aJv.maxTuple();
        System.out.println("x="+x);

        x.TestInstanceof(); //==============================================================

        KjvCortegeD [] b = {new KjvCortegeD(3), new KjvCortegeD(4)};
        KjvArrayCortege<KjvCortegeD> aJv2 = new KjvArrayCortege<KjvCortegeD>(b);
        //KjvArrayCortege<? extends ITuple<? super KjvCortege>> aJv3 = (KjvArrayCortege<? extends ITuple<? super KjvCortege>>) new KjvArrayCortege<KjvCortegeD>(b);

        //if (aJv.compareMaxTuple((KjvArrayCortege<? extends ITuple<? super KjvCortege>>) aJv2)==-1)
        if (aJv.compareMaxTuple(aJv2)==-1)
            System.out.println("aJv < aJv2");
        else
            System.out.println("aJv >= aJv2");

        @SuppressWarnings({"rawtypes","unchecked"})
        Pair<ITuple> pair = new Pair(new KjvCortege(56),new KjvCortege(78));
        //Pair<Object> pair = new Pair(new KjvCortege(56),new KjvCortege(78));
        //Pair<KjvCortege> pair = new Pair(new KjvCortege(56),new KjvCortege(78));
        aJv.setMinMaxTuples(aJv2,pair);
        System.out.println(pair.getFirst()+" "+pair.getSecond() );
        //-------------------------------------------------------------
        System.out.println("aJv.Sum()" + aJv.Sum());
        System.out.println("aJv2.Sum()" + aJv2.Sum());
        if ( aJv.compareSumTuples(aJv2)==-1 )
            System.out.println("sum aJv < sum aJv2");
        else
            System.out.println("sum aJv >= sum aJv2");

        if ( aJv2.compareSumTuples(aJv)==-1 )
            System.out.println("sum aJv > sum aJv2");
        else
            System.out.println("sum aJv <= sum aJv2");
        //------------------------------------------------------------
        KjvCortegeD  b1 = new KjvCortegeD(3);
        aJv2.setFirstCortege(b1);
        KjvCortegeDD  b11 = new KjvCortegeDD(3,4);
        aJv2.setFirstCortege(b11);
        //KjvCortege  bi1 = new KjvCortege(3);
        //aJv2.setFirstCortege(bi1);

        Pair<Integer> aaa = new Pair<>(2,3);
        Pair aao = aaa;
        Pair<String> ppp0 = (Pair<String>)aao;
        Pair<String> ppp = new Pair<>("a","b");
        if (aaa.getClass()==ppp.getClass())
        {
            System.out.println("Классы равны");
        }
        else
        {
            System.out.println("Классы различаются");
        }

        ArrayList<Integer> ai = new ArrayList<>();

        FInputFromTxt("in.txt", ai);

        KjvCortegeD ai0=new KjvCortegeD(ai.get(0));
        KjvCortegeD ai1=new KjvCortegeD(ai.get(1));
        KjvArrayCortege<KjvCortegeD> list=new KjvArrayCortege<>(ai0,ai1);
        System.out.println("Сумма кортежей в первом списке: "+list.Sum());

        KjvArrayCortege<KjvCortegeD> list1=new KjvArrayCortege<>(new KjvCortegeD(ai.get(2)),new KjvCortegeD(ai.get(3)));
        System.out.println("Сумма кортежей во втором списке: "+list1.Sum());

        if(list.compareSumTuples(list1)>0)
            System.out.println("Сумма кортежей в первом списке больше:"+list.Sum());
        else if (list.compareSumTuples(list1)<0)
            System.out.println("Сумма кортежей во втором списке больше: "+list1.Sum());
        else if (list.compareSumTuples(list1)==0)
            System.out.println("Сумма кортежей в первом и втором списках одинакова: "+list1.Sum());

        System.out.println("Второй элемент кортежа list: "+list.at(1));

        System.out.println("Первый элемент кортежа list1: "+list1.at(0));


    }

    static void FInputFromTxt(String name, ArrayList<Integer> ai) {
        try {
            InputStreamReader isr = new FileReader(name);
            Scanner xIn = new Scanner(isr);
            int x = 0;

            while (true) {
                try {
                    x = xIn.nextInt();
                    ai.add(x);
                    System.out.print(x + " ");

                } catch (NoSuchElementException e1) {
                    break;
                }
            }
            xIn.close();

        } catch (IOException e) {
            System.out.println(e.toString());
        }

        System.out.println("\n----------------------------");

    }


}
