import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Vector;


class Vertex implements Comparable<Vertex>{
    public int vertexNumber;
    public long length;

    public Vertex(int tVertexNumber, long tLength){
        vertexNumber = tVertexNumber;
        length = tLength;
    }

    @Override
    public int compareTo(Vertex o) {
        return Long.compare(length, o.length);
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }

        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();


        Vector<Vector<Vertex>> arcs = new Vector<>(n);
        for (int i = 0; i < n; ++i){
            arcs.add(new Vector<>());
        }

        int u, v, w;

        for (int i = 0; i < m; ++i) {
            u = scanner.nextInt();
            v = scanner.nextInt();
            w = scanner.nextInt();
            arcs.get(u - 1).add(new Vertex(v - 1, w));
            arcs.get(v - 1).add(new Vertex(u - 1, w));
        }
        FileWriter fout;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);

            fout.write(dijkstra(n, arcs) + "");

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static long dijkstra(int n, Vector<Vector<Vertex>> arcs) {

        long distances[] = new long[n];

        for (int i = 1; i < n; ++i)
            distances[i] = Integer.MAX_VALUE;
        distances[0] = 0;

        TreeSet<Vertex> queue = new TreeSet<>();
        queue.add(new Vertex(0, 0));

        while (!queue.isEmpty()) {

            int current = queue.first().vertexNumber;
            queue.remove(queue.first());

            for (int i = 0; i < arcs.get(current).size(); ++i) {
                if (distances[arcs.get(current).get(i).vertexNumber] > distances[current] + arcs.get(current).get(i).length) {
                    queue.remove(new Vertex(arcs.get(current).get(i).vertexNumber, distances[arcs.get(current).get(i).vertexNumber]));
                    distances[arcs.get(current).get(i).vertexNumber] = distances[current] + arcs.get(current).get(i).length;
                    queue.add(new Vertex(arcs.get(current).get(i).vertexNumber, distances[arcs.get(current).get(i).vertexNumber]));
                }
            }
        }

        return distances[n - 1];

    }


}