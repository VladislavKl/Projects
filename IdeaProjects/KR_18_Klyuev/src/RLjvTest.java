import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RLjvTest {

    /**
     * @param args
     * @throws CloneNotSupportedException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws CloneNotSupportedException, FileNotFoundException
    {
        // TODO Auto-generated method stub

        try(BufferedReader fin = Files.newBufferedReader(Paths.get("info.txt"))){
            String line;//fin.readLine();
            while((line = fin.readLine())!=null){
                String[] wd=line.split(" ");
                for(String i : wd){
                    if(!i.equals("")){
                        KjvCortege1 vv = new KjvCortege1(Integer.parseInt(i));
                        System.out.println(vv);
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //fin.close();
        //Scanner sc=new Scanner(fin);
        //System.out.println(sc);
        System.out.println();
        KjvCortege1 [] a = {new KjvCortege1(3), new KjvCortege1(23)};
        RLjv<KjvCortege1> aJv = new RLjv<>(a);
        RIter<KjvCortege1> aaaa = new RIter<>(aJv);
        System.out.println();
        while(aaaa.hasNext()){
            System.out.println(aaaa.next());
        }
        RLjv<KjvCortege1> bb=aJv.clone();
        System.out.println();

        KjvCortege [] b = {new KjvCortege(0,1,32,4), new KjvCortege(2,4,23,43)};
        RLjv<KjvCortege1> aJv2 = new RLjv<>(b);
        System.out.println(new KjvCortege(5,8,47,11));
        RLjv<? extends IList> aJv3 =  new RLjv<>(b);
        KjvCortege1 x = aJv.maxTuple();
        System.out.println("x="+x);
        if (bb.compareMaxTuple(aJv2)==-1)
            System.out.println("a1 < a Jv2");
        else
            System.out.println("aJv >= a Jv2");

        Pair<IList> pair = new Pair(new KjvCortege1(19),new KjvCortege1(2));
        Pair<Object> pair1 = new Pair(new KjvCortege(923),new KjvCortege(49));
        Pair<KjvCortege> pair2 = new Pair(new KjvCortege(12),new KjvCortege(46));
        aJv.setMinMaxTuples(aJv2,pair);
        System.out.println(pair.getFirst()+" "+pair.getSecond() );

        KjvCortege1  b1 = new KjvCortege1(3);
        aJv2.setFirstCortege(b1);
        KjvCorteget  b11 = new KjvCorteget(3,4);
        aJv2.setFirstCortege(b11);

        KjvCortege  bi1 = new KjvCortege(3);
        aJv2.setFirstCortege(bi1);

        ArrayList<Integer> ai = new ArrayList<>();

        FInputFromTxt("in.txt", ai);

        KjvCortege1 ai0=new KjvCortege1(ai.get(0));
        KjvCortege1 ai1=new KjvCortege1(ai.get(1));
        RLjv<KjvCortege1> list=new RLjv<>(ai0,ai1);
        System.out.println(list.at(0)+" "+list.at(1));

        //aJv2.setMaxAndAvgItems(aJv,pair1);

        KjvCortege1 [] testik1 = {new KjvCortege1(2), new KjvCortege1(1)};
        RLjv<KjvCortege1> test1 = new RLjv<>(testik1);

        KjvCortege1 [] testik2 = {new KjvCortege1(4), new KjvCortege1(3)};
        RLjv<KjvCortege1> test2 = new RLjv<>(testik2);

        test1.setMaxAndAvgItems(test2,pair1);
        System.out.println("Результат setMaxAndAvgItems:" + pair1.getFirst()+" и "+pair1.getSecond());
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
