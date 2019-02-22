import java.io.*;
import java.util.Scanner;

public class Main {

    public static long max(long a, long b) {
        return (a > b ? a : b);
    }

    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int m, n;

        n = scanner.nextInt();

        int[] sequence = new int[n + 1];
        int[] quantityOfEdges = new int[n + 1];
        long[] processTimes = new long[n + 1];
        long[] deadlines = new long[n + 1];

        for (int i = 1; i <= n; ++i) {
            processTimes[i] = scanner.nextLong();
            deadlines[i] = scanner.nextLong();
        }

        m = scanner.nextInt();

        int[][] a = new int[m][n + 1];

        int j;

        for (int i = 1; i <= m; ++i) {
            j = scanner.nextInt();
            a[quantityOfEdges[j]++][j] = scanner.nextInt();
        }

        int place = 0;
        int workNumber = 0;


        while (place < n) {
            long maxDeadline = -1;
            for (int i = n; i > 0; --i)
                if (quantityOfEdges[i] == 0 && deadlines[i] > maxDeadline) {
                    maxDeadline = deadlines[i];
                    workNumber = i;
                }

            sequence[place] = workNumber;
            quantityOfEdges[workNumber] = -1;

            int temp;
            for (int i = 1; i <= n; ++i) {
                workNumber = quantityOfEdges[i];
                if (workNumber > 0) {
                    for (j = 0; j < workNumber; ++j) {
                        if (a[j][i] != sequence[place]) {
                            temp = a[j][i];
                            a[j][i] = 0;
                            a[j - workNumber + quantityOfEdges[i]][i] = temp;
                        } else {
                            a[j][i] = 0;
                            --quantityOfEdges[i];
                        }
                    }
                }
            }
            ++place;
        }

        long c = 0, maximum = -1;
        int maximumIndex = -1;

        for (int i = --place; i >= 0; --i) {
            c += processTimes[sequence[i]];
            if (Long.max(c - deadlines[sequence[i]], 0) >= maximum) {
                maximum = Long.max(c - deadlines[sequence[i]], 0);
                maximumIndex = sequence[i];
            }
        }

        FileWriter fout;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            fout.write(maximumIndex + " " + maximum + "\n");
            for (int i = place; i > 0; --i)
                fout.write(sequence[i] + "\n");
            fout.write(Integer.toString(sequence[0]));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}