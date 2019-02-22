

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by stunba on 5/19/16.
 */
public class Main {
    static ArrayList<Integer> detali;
    static TreeSet<Integer> set = new TreeSet<>();
    static PriorityQueue<Integer> myqueue;
    static int n;
    static int m;

    static public void write(String text) {

        try {
            FileWriter write = new FileWriter("output.txt");

            write.write(text);
            write.close();
        } catch (IOException e) {

        }

    }

    static public void readFile() {
        Scanner scan;
            String filename = "input.txt";
        FileReader file = null;
        try {
            file = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scan = new Scanner(file);
        n = scan.nextInt();
        m = scan.nextInt();
        detali = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            detali.add(scan.nextInt());
        }
    }

    static public void algo() {
        myqueue = new PriorityQueue<>(set.comparator());
        Collections.sort(detali);
        int k = detali.size() - 1;
        int max = detali.get(0);
        int ogr = Math.min(n, m);

        for (int i = 0; i < ogr; i++) {
            myqueue.add(detali.get(k));
            if (max < detali.get(k)) {
                max = detali.get(k);
            }
            k--;
        }

        while (k >= 0) {
            int el = myqueue.poll();
            el = el + detali.get(k);
            myqueue.add(el);
            if (max < el) {
                max = el;
            }
            k--;
        }

        write(Integer.toString(max));

    }

    public static void main(String[] args) {
        readFile();
        algo();
    }
}