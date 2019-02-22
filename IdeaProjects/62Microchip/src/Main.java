import java.io.*;
import java.util.Scanner;

import static java.lang.Integer.min;

class MatrixSize{
    public int k;
    public int m;

    public MatrixSize(int a, int b) {
        k=a;
        m=b;
    }
};

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
        MatrixSize[] matrixSizes = new MatrixSize[n];
        for (int i = 0; i < n; ++i) {
            matrixSizes[i] = new MatrixSize(scanner.nextInt(), scanner.nextInt());
        }

        FileWriter fout;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            fout.write(  minimumQuantityOfOperations(matrixSizes, n)+"");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int mQOORecursiveHelper(int[][] matrixOfMatrixes, MatrixSize[] mass, int n, int i, int j) {
        if (i == j) {
            return 0;
        }

        if (j == i + 1) {
            matrixOfMatrixes[i - 1][j - 1] = mass[i - 1].k * mass[i - 1].m * mass[i].m;
            return matrixOfMatrixes[i - 1][j - 1];
        }

        int minimum = mQOORecursiveHelper(matrixOfMatrixes, mass, n, i + 1, j) + mass[i - 1].k * mass[i - 1].m * mass[j - 1].m;

        for (int q = i + 1; q < j; ++q) {
            if (matrixOfMatrixes[i - 1][q - 1] == 0)
                mQOORecursiveHelper(matrixOfMatrixes, mass, n, i, q);
            if (matrixOfMatrixes[q][j - 1] == 0)
                mQOORecursiveHelper(matrixOfMatrixes, mass, n, q + 1, j);
            minimum = min(minimum,
                    matrixOfMatrixes[i - 1][q - 1] +
                            matrixOfMatrixes[q][j - 1] +
                            mass[i - 1].k * mass[q - 1].m * mass[j - 1].m);
        }
        matrixOfMatrixes[i - 1][j - 1] = minimum;
        return minimum;
    }

    public static int minimumQuantityOfOperations(MatrixSize[] array, int n) {
        int[][] matrixOfMatrixes = new int [n][n];
        return mQOORecursiveHelper(matrixOfMatrixes, array, n, 1, n);
    }
}