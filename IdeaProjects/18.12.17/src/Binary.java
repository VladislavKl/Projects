import java.io.*;
import java.util.Scanner;


public class Binary {

    public static void main(String[] args) throws IOException {

        DataOutputStream out;

        {
            try {
                out = new DataOutputStream(new FileOutputStream("in.txt"));
                out.writeInt(-7);
                out.writeInt(0);
                out.writeInt(120);
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DataInputStream in;

        {
            try {
                in = new DataInputStream(new FileInputStream("/Users/klyuevvladislav/IdeaProjects/18.12.17/in.txt"));
                int Sum = 0;
                while (true) {
                    try {
                        Sum += in.readInt();
                    } catch (EOFException e) {
                        break;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                System.out.println(Sum);
                in.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        try {
            Scanner input = new Scanner(new FileReader("in.txt"));
            PrintWriter output = new PrintWriter(new FileWriter("out.txt"));
            int Sum = 0;
            while (input.hasNext()) {
                int a = input.nextInt();
                Sum += a;
                output.print(a + " ");

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}