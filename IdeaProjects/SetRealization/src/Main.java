import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MySet<Integer> a=new MySet<>();
        FileReader fr=null;
        FileWriter fw=null;

        try {
            fw=new FileWriter("out.txt");
            fw.write("4 7 2 6 9 10 3 end");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
                fr=new FileReader("out.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
                Scanner scr=new Scanner(fr);
                while(scr.hasNext()) {
                    if(scr.hasNextInt()) {
                        a.add(scr.nextInt());
                    }
                    else {
                String str=scr.next();
                if(str.equals("end"))
                    break;
            }
        }


        MySet<Integer> b=new MySet<>();
        for (int i=4;i<10;++i)
            b.add(i);
        b.add(4);
        a.remove(2);

        System.out.println(a.toString());
        System.out.println(b.toString());

        System.out.println("-----------------------");
        for (Integer x : b) {
            System.out.print(x+" ");
        }

        for (Object o: a){
            System.out.println(o);
        }

        System.out.println(a.intersection(b).toString());
        System.out.println("");
        System.out.println(a.difference(b).toString());
        System.out.println("");
        System.out.println(a.intersection(b).toString());
        System.out.println("");
        System.out.println(a.symmetricDifference(b).toString());
        System.out.println("");
        System.out.println(a.plus(b).toString());


        }
}
