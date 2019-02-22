import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(buff.readLine());

        if(n <= 0) {
            System.out.println("Invalid size: " + n);
            System.exit(1);
        }

        int[][] matrix = new int[n][n];
        int[][] final_matrix = new int[n][n];

        final Random rand = new Random();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = -n + rand.nextInt(2 * n + 1);

        System.out.println("Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }

        System.out.println();

        if(args.length != 1)
        {
            System.out.println("Invalid number of arguments!");
            System.exit(1);
        }

        int k = Integer.parseInt(args[0]);
        if(k < 0 || k > n)
        {
            System.out.println("Invalid argument: " + k);
            System.exit(1);
        }

        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = matrix[i][k];

        Arrays.sort(arr);

        for(int i = 0; i < n; i++) {
            for (int l = 0; l < n; l++) {
                if (matrix[l][k] == arr[i]) {
                    for (int j = 0; j < n; j++)
                        final_matrix[i][j] = matrix[l][j];
                }
            }

        }

        System.out.println("Result:");
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(final_matrix[i][j] + " ");
            System.out.println();
        }
    }

}
