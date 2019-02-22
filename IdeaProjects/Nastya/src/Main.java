import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Main {
    public static String solution(ArrayList<String> strings, int first, int last, boolean[] b) {
        String result = "";
        for (int i = first, j = last; i < j; ++i) {
            if (i+1 == j){
                if (!strings.get(i).equals(strings.get(j))){
                    b[0]=true;
                    result+=strings.get(i);
                }
            }
            if (strings.get(i).equals(strings.get(j))) {
                --j;
                result += strings.get(i);
            } else {
                solution(strings, i + 1, j, b);
                solution(strings, i, j - 1, b);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        String str = scanner.next();
        strings.addAll(Arrays.asList(str.split("")).subList(0, str.length()));
        boolean[] b = new boolean[] {false, false};
        str = solution(strings, 0, strings.size()-1, b);
        if (b[0]==false) {
            str += new StringBuffer(str).reverse().toString();
        }
        else {
            str += new StringBuffer(str).reverse().deleteCharAt(0).toString();
        }
        FileWriter fout;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            fout.write(str.length()+"\n");
            fout.write(str);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}