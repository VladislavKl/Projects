import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>(n);
        for (int i = 0; i < n; ++i){
            arrayList.add(new ArrayList<>());
        }
        int a,b;
        for (int i = 0; i < m; ++i){
            a = scanner.nextInt();
            b = scanner.nextInt();
            arrayList.get(a-1).add(b);
            arrayList.get(b-1).add(a);
        }
        FileWriter fout;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            for (ArrayList<Integer> i : arrayList) {
                fout.write(i.size() + "");
                for (Integer j : i) {
                        fout.write(" " + j);
                }
                fout.write("\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
