import java.io.*;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;


class Triple implements Comparator<Triple>, Comparable<Triple>{
    public int kol, x, y;

    public Triple( int k, int xx, int yy) {
        kol = k;
        x = xx;
        y = yy;
    }

    @Override
    public int compare(Triple i, Triple j) {
        if (i.kol == j.kol) {
            if (i.x == j.x)
                return i.y - j.y;
            return i.x - j.x;
        }
        return j.kol - i.kol;
    }

    @Override
    public int compareTo(Triple j) {
        if (kol == j.kol) {
            if (x == j.x)
                return y - j.y;
            return x - j.x;
        }
        return j.kol - kol;
    }
}

class Pair {
    public int first;
    public int second;

    public Pair() {
        first = 0;
        second = 0;
    }

    public Pair(int tfirst, int tsecond) {
        first = tfirst;
        second = tsecond;
    }
}



class Main {
    public static int MAX_AMOUNT = 1000;
    public static int MAX_KOL = 1000;
    public static int MAX_N = 1000;
    public static int MAX_M = 1000;


    public static int k, n, m, blocked, flagged;
    public static int[][] board;
    public static boolean queens[][] = new boolean[MAX_N][MAX_M];
    public static int amount, min_kol;
    public static boolean flag, write;
    public static Pair location[][] = new Pair[MAX_AMOUNT][MAX_KOL];
    public static Pair combination[] = new Pair[MAX_KOL];
    public static Pair comb[][] = new Pair[MAX_KOL][MAX_KOL];
    public static int yk[] = new int[MAX_KOL];
    public static TreeSet<Triple> first_max;

    public static Pair[] mustBe = new Pair[1000];

    public static void input() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        k = scanner.nextInt();
        n = scanner.nextInt();
        m = scanner.nextInt();

        board = new int [n][n];
        first_max = new TreeSet<>();
        blocked = scanner.nextInt();
        for (int i = 0; i < 1000; i++) {
            combination[i] = new Pair();
        }
        for (int i = 0; i < blocked; i++) {
            board[scanner.nextInt()][scanner.nextInt()] = -1;
        }
        scanner.close();
    }

    public static void output() {
        FileWriter fout;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            fout.write(amount);
            for (int i = 0; i < amount; i++) {
                fout.write("\n");
                for (int j = 0; j < min_kol - 1; j++) {
                    fout.write(location[i][j].first + " " + location[i][j].second + " ");
                }
                fout.write( location[i][min_kol - 1].first + " " + location[i][min_kol - 1].second);
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(int x, int y, int oper) {
        for (int i = y; i < m; i++) {
            if (board[x][i] == -1) break;
            if (oper == -1 && board[x][i] == k) flagged--;
            board[x][i] += oper;
            if (oper == 1 && board[x][i] == k) flagged++;
        }
        for (int i = y - 1; i > -1; i--) {
            if (board[x][i] == -1) break;
            if (oper == -1 && board[x][i] == k) flagged--;
            board[x][i] += oper;
            if (oper == 1 && board[x][i] == k) flagged++;
        }
        for (int i = x + 1; i < n; i++) {
            if (board[i][y] == -1) break;
            if (oper == -1 && board[i][y] == k) flagged--;
            board[i][y] += oper;
            if (oper == 1 && board[i][y] == k) flagged++;
        }
        for (int i = x - 1; i > -1; i--) {
            if (board[i][y] == -1) break;
            if (oper == -1 && board[i][y] == k) flagged--;
            board[i][y] += oper;
            if (oper == 1 && board[i][y] == k) flagged++;
        }
        for (int i = 1; i < Integer.min(n - x, m - y); i++) {
            if (board[x + i][y + i] == -1) break;
            if (oper == -1 && board[x + i][y + i] == k) flagged--;
            board[x + i][y + i] += oper;
            if (oper == 1 && board[x + i][y + i] == k) flagged++;
        }
        for (int i = 1; i < Integer.min(x + 1, y + 1); i++) {
            if (board[x - i][y - i] == -1) break;
            if (oper == -1 && board[x - i][y - i] == k) flagged--;
            board[x - i][y - i] += oper;
            if (oper == 1 && board[x - i][y - i] == k) flagged++;
        }
        for (int i = 1; i < Integer.min(x + 1, m - y); i++) {
            if (board[x - i][y + i] == -1) break;
            if (oper == -1 && board[x - i][y + i] == k) flagged--;
            board[x - i][y + i] += oper;
            if (oper == 1 && board[x - i][y + i] == k) flagged++;
        }
        for (int i = 1; i < Integer.min(n - x, y + 1); i++) {
            if (board[x + i][y - i] == -1) break;
            if (oper == -1 && board[x + i][y - i] == k) flagged--;
            board[x + i][y - i] += oper;
            if (oper == 1 && board[x + i][y - i] == k) flagged++;
        }
    }

    public static void locate(int x, int y, int kol, int q) {
        if (kol == 0) {
            if (flagged + blocked == n * m) {
                flag = true;
                write = true;
            }
            return;
        }
        if (y == m) {
            y = 0;
            x++;
        }
        while (x != n) {
            if (board[x][y] != -1) {
                combination[q - kol].first = x;
                combination[q - kol].second = y;
                update(x, y, 1);
                locate(x, y + 1, kol - 1, q);
                update(x, y, -1);
                if (write) {
                    write = false;
                    for (int i = 0; i < q; i++) {
                        location[amount][i] = combination[i];
                    }
                    amount++;
                }
            }
            y++;
            if (y == m) {
                y = 0;
                x++;
            }
        }
    }

    public static int oper(int pointer) {
        int kol = -1;
        if (flagged + blocked == n * m) {
            return 0;
        }
        int neighbours = 0;
        int maximum = 0;
        int area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                area = 0;
                if (board[i][j] == -1 || queens[i][j]) continue;
                int l = 1;
                while (j + l < m) {
                    if (board[i][j + l] == -1) break;
                    neighbours++;
                    if (board[i][j + l] < k) area++;
                    l++;
                }
                l = 1;
                while (j - l > -1) {
                    if (board[i][j - l] == -1) break;
                    neighbours++;
                    if (board[i][j - l] < k) area++;
                    l++;
                }
                l = 1;
                while (i + l < n) {
                    if (board[i + l][j] == -1) break;
                    neighbours++;
                    if (board[i + l][j] < k) area++;
                    l++;
                }
                l = 1;
                while (i - l > -1) {
                    if (board[i - l][j] == -1) break;
                    neighbours++;
                    if (board[i - l][j] < k) area++;
                    l++;
                }
                l = 1;
                while (i - l > -1 && j - l > -1) {
                    if (board[i - l][j - l] == -1) break;
                    neighbours++;
                    if (board[i - l][j - l] < k) area++;
                    l++;
                }
                l = 1;
                while (i - l > -1 && j + l < m) {
                    if (board[i - l][j + l] == -1) break;
                    neighbours++;
                    if (board[i - l][j + l] < k) area++;
                    l++;
                }
                l = 1;
                while (i + l < n && j - l > -1) {
                    if (board[i + l][j - l] == -1) break;
                    neighbours++;
                    if (board[i + l][j - l] < k) area++;
                    l++;
                }
                l = 1;
                while (i + l < n && j + l < m) {
                    if (board[i + l][j + l] == -1) break;
                    neighbours++;
                    if (board[i + l][j + l] < k) area++;
                    l++;
                }
                if (board[i][j] < k) area++;
                if (maximum < area) {
                    maximum = area;
                    yk[pointer] = 1;
                    comb[pointer][0] = new Pair(i, j);
                } else if (maximum == area) {
                    comb[pointer][yk[pointer]++] = new Pair(i, j);
                }
            }
        }
        for (int i = 0; i < yk[pointer]; i++) {
            queens[comb[pointer][i].first][comb[pointer][i].second] = true;
            update(comb[pointer][i].first, comb[pointer][i].second, 1);
            if (kol == -1) kol = oper(pointer + 1) + 1;
            else kol = Integer.min(oper(pointer + 1) + 1, kol);
            queens[comb[pointer][i].first][comb[pointer][i].second] = false;
            update(comb[pointer][i].first, comb[pointer][i].second, -1);
        }
        return kol;
    }

    public static void find_min() {
        min_kol = Integer.MAX_VALUE;
        int area = 0;
        int neighbours = 0;
        int yk = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                area = 0;
                if (board[i][j] == -1 || queens[i][j]) continue;
                int l = 1;
                while (j + l < m) {
                    if (board[i][j + l] == -1) break;
                    neighbours++;
                    if (board[i][j + l] < k) area++;
                    l++;
                }
                l = 1;
                while (j - l > -1) {
                    if (board[i][j - l] == -1) break;
                    neighbours++;
                    if (board[i][j - l] < k) area++;
                    l++;
                }
                l = 1;
                while (i + l < n) {
                    if (board[i + l][j] == -1) break;
                    neighbours++;
                    if (board[i + l][j] < k) area++;
                    l++;
                }
                l = 1;
                while (i - l > -1) {
                    if (board[i - l][j] == -1) break;
                    neighbours++;
                    if (board[i - l][j] < k) area++;
                    l++;
                }
                l = 1;
                while (i - l > -1 && j - l > -1) {
                    if (board[i - l][j - l] == -1) break;
                    neighbours++;
                    if (board[i - l][j - l] < k) area++;
                    l++;
                }
                l = 1;
                while (i - l > -1 && j + l < m) {
                    if (board[i - l][j + l] == -1) break;
                    neighbours++;
                    if (board[i - l][j + l] < k) area++;
                    l++;
                }
                l = 1;
                while (i + l < n && j - l > -1) {
                    if (board[i + l][j - l] == -1) break;
                    neighbours++;
                    if (board[i + l][j - l] < k) area++;
                    l++;
                }
                l = 1;
                while (i + l < n && j + l < m) {
                    if (board[i + l][j + l] == -1) break;
                    neighbours++;
                    if (board[i + l][j + l] < k) area++;
                    l++;
                }
                if (neighbours + 1 < k) {
                    min_kol = 0;
                    amount = 0;
                    return;
                } else if (neighbours == k) {
                    mustBe[yk++] = new Pair(i, j);
                }
                if (board[i][j] < k)
                    area++;
                first_max.add(new Triple(area, i, j));
            }
        }
        for (int i = 0; i < k; i++) {
            queens[first_max.first().x][first_max.first().y] =true;
            update(first_max.first().x, first_max.first().y, 1);
            first_max.pollFirst();
        }
        int k1 = 0;
        for (int ii = 0; ii < yk; ii++) {
            int l = 1;
            int i = mustBe[ii].first;
            int j = mustBe[ii].second;
            while (j + l < m) {
                if (board[i][j + l] == -1) break;
                if (!queens[i][j + l]) {
                    update(i, j + l, 1);
                    k1++;
                }
                l++;
            }
            l = 1;
            while (j - l > -1) {
                if (board[i][j - l] == -1) break;

                if (!queens[i][j - l]) {
                    update(i, j - l, 1);
                    k1++;
                }
                l++;
            }
            l = 1;
            while (i + l < n) {
                if (board[i + l][j] == -1) break;

                if (!queens[i + l][j]) {
                    update(i + l, j, 1);
                    k1++;
                }
                l++;
            }
            l = 1;
            while (i - l > -1) {
                if (board[i - l][j] == -1) break;
                if (!queens[i - l][j]) {
                    update(i - l, j, 1);
                    k1++;
                }
                l++;
            }
            l = 1;
            while (i - l > -1 && j - l > -1) {
                if (board[i - l][j - l] == -1) break;
                if (!queens[i - l][j - l]) {
                    update(i - l, j - l, 1);
                    k1++;
                }
                l++;
            }
            l = 1;
            while (i - l > -1 && j + l < m) {
                if (board[i - l][j + l] == -1) break;
                if (!queens[i - l][j + l]) {
                    update(i - l, j + l, 1);
                    k1++;
                }
                l++;
            }
            l = 1;
            while (i + l < n && j - l > -1) {
                if (board[i + l][j - l] == -1) break;
                if (!queens[i + l][j - l]) {
                    update(i + l, j - l, 1);
                    k1++;
                }
                l++;
            }
            l = 1;
            while (i + l < n && j + l < m) {
                if (board[i + l][j + l] == -1) break;
                if (!queens[i + l][j + l]) {
                    update(i + l, j + l, 1);
                    k1++;
                }
                l++;
            }
            if (!queens[i][j]) {
                update(i, j, 1);
                k1++;
            }
        }

        min_kol = oper(0) + k + k1;
        if (min_kol == -1) min_kol = 0;
    }

    public static void process() {
        flag = false;                                                        // if find min amount
        write = false;                                                        // if we have to write positions in answer
        find_min();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != -1) board[i][j] = 0;
            }
        }
        flagged = 0;                                                    // initialize amount of beated k times fields													// initialize min amount of queens
        locate(0, 0, min_kol, min_kol);                                            // try to locate kol queens
    }

    public static void main(String[] args) {
        input();
        process();
        output();
    }
}