public class PathFinder {

    public static void findExit(int[][] labyrinth, int i, int j, int k, int g) {
        if (findWay(labyrinth, i, j, k, g) == -1)
            System.out.println("I HAVE FOUND AN EXIT!!!");
    }

    public static int findWay(int[][] labyrinth, int i, int j, int k, int g) {
        if (labyrinth[i][j + 1] == 1) {
            if (i == k && j == g) {
                return -1;
            }
            ++labyrinth[i][j + 1];
            if (findWay(labyrinth, i, j + 1, k, g) == -1)
                return -1;
        }
        if (labyrinth[i][j - 1] == 1) {
            if (i == k && j == g) {
                return -1;
            }
            ++labyrinth[i][j - 1];
            if (findWay(labyrinth, i, j - 1, k, g) == -1)
                return -1;
        }
        if (labyrinth[i - 1][j] == 1) {
            if (i == k && j == g) {
                return -1;
            }
            ++labyrinth[i - 1][j];
            if (findWay(labyrinth, i - 1, j, k, g) == -1)
                return -1;
        }
        if (labyrinth[i + 1][j] == 1) {
            if (i == k && j == g) {
                return -1;
            }
            ++labyrinth[i + 1][j];
            if (findWay(labyrinth, i + 1, j, k, g) == -1)
                return -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] labyrinth = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        findExit(labyrinth, 6, 3, 1, 1);

    }
}
