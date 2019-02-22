public class MatrixChain {
    /**
     * Возвращает ответ на задачу об оптимальном перемножении матриц, используя
     * динамическое программирование.
     * Асимптотика решения - O(N^3) время и O(N^2) память.
     *
     * @param p массив размеров матриц, см.статью
     * @return минимальное количество скалярных умножений, необходимое для решения задачи
     */
    public static int multiplyOrder(int[] p) {
        int n = p.length;
        int[][] dp = new int[n][n];

        for (int l = 2; l < n; l++) {
            for (int i = 1; i <= n - l; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
                }
            }
        }
        return dp[1][n-1];
    }

    public static void main(String[] args) {
        int[] test = { 10, 100, 5, 50 };
        System.out.println(MatrixChain.multiplyOrder(test));
    }
}