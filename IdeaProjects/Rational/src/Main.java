import java.util.*;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Rat a = new Rat(3,2);
        Rat b = new Rat(12, 8);
        System.out.println(b);


        Scanner sc = new Scanner(System.in);
        System.out.println(a.getNum());
        System.out.println(a.getDenum());

        Rat a3 =  a.plus(b);

        if (a.isEqualToFraction(b))
            System.out.println("Equal");
        else
            System.out.println("Not equal");


        System.out.println(a3.getNum());
        System.out.println(a3.getDenum());


        a3.plusTo(a);

        System.out.println(a3.getNum());
        System.out.println(a3.getDenum());

        Rat a4 = a3.plus(58);

        System.out.println(a4.getNum());
        System.out.println(a4.getDenum());


        Rat a5 =  a4.plusTo(12);

        System.out.println(a5.getNum());
        System.out.println(a5.getDenum());

        System.out.println(a4.getNum());
        System.out.println(a4.getDenum());

        a5.plusTo(7);

        System.out.println(a5.getNum());
        System.out.println(a5.getDenum());
        System.out.println(a4.getNum());
        System.out.println(a4.getDenum());


        Rat temp;
        temp=a5;
        a5.setDenum(163);

        System.out.println(temp);

    }
}
