import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

class Line {
    private long x1;
    private long y1;
    private long x2;
    private long y2;
    private double dist;

    /**
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public Line(long x1, long y1, long x2, long y2) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.dist = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public long getX1() {
        return x1;
    }

    public void setX1(long x1) {
        this.x1 = x1;
    }

    public long getY1() {
        return y1;
    }

    public void setY1(long y1) {
        this.y1 = y1;
    }

    public long getX2() {
        return x2;
    }

    public void setX2(long x2) {
        this.x2 = x2;
    }

    public long getY2() {
        return y2;
    }

    public void setY2(long y2) {
        this.y2 = y2;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    @Override
    public String toString() {
        return x1 + " " + y1 + " " + x2 + " " + y2;
    }
}

class PointDog {
    private long x;
    private long y;

    /**
     * @param x
     * @param y
     */
    public PointDog(long x, long y) {
        super();
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

}

public class Main {
    public static boolean check(Line l, PointDog p) {
        double s1 = Math.sqrt(
                (p.getX() - l.getX1()) * (p.getX() - l.getX1()) +
                        (p.getY() - l.getY1()) * (p.getY() - l.getY1()));
        double s2 = Math.sqrt(
                (p.getX() - l.getX2()) * (p.getX() - l.getX2()) +
                        (p.getY() - l.getY2()) * (p.getY() - l.getY2()));
        if (2 * l.getDist() >= (s1 + s2))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        try {
            File file = new File("input5.txt");
            FileWriter writer = new FileWriter("output.txt", false);
            Scanner scannerIn = new Scanner(file);
            int manNum = scannerIn.nextInt();
            int dogNum = scannerIn.nextInt();
            long backX = scannerIn.nextLong();
            long backY = scannerIn.nextLong();
            long newX = 0;
            long newY = 0;
            ArrayList<Line> man = new ArrayList<>();
            for (int i = 0; i < manNum - 1; i++) {
                newX = scannerIn.nextLong();
                newY = scannerIn.nextLong();
                man.add(new Line(backX, backY, newX, newY));
                backX = newX;
                backY = newY;
            }


            ArrayList<PointDog> dog = new ArrayList<>();
            for (int i = 0; i < dogNum; i++) {
                dog.add(new PointDog(scannerIn.nextLong(), scannerIn.nextLong()));
            }

            int g[][] = new int[manNum + dogNum + 1][manNum + dogNum + 1];
            for (int i = 0; i < man.size(); i++) {
                g[0][i + 1] = 1;
            }
            for (int i = 0; i < dog.size(); i++) {
                g[man.size() + dog.size() - i][man.size() + dog.size() + 1] = 1;
            }

            for (int i = 0; i < man.size(); i++) {
                for (int j = 0; j < dog.size(); j++) {
                    if (check(man.get(i), dog.get(j))) {
                        g[i + 1][man.size() + j + 1] = 1;
                    }
                }
            }

            boolean ans = true;
            boolean visit[] = new boolean[man.size() + dog.size() + 2];
            int path[] = new int[man.size() + dog.size() + 2];
            while (ans) {
                for (int i = 0; i < man.size() + dog.size() + 2; i++) {
                    visit[i] = false;
                    path[i] = -1;
                }

                ArrayDeque<Integer> ver = new ArrayDeque<Integer>();
                ArrayList<Integer> pyt = new ArrayList<Integer>();
                boolean s = false;
                boolean check = false;
                ver.addLast(0);
                visit[0] = true;
                while (ver.peekLast() != null) {
                    check = false;
                    int ourVer = ver.peekLast();
                    for (int j = 0; j < man.size() + dog.size() + 2; j++) {
                        if ((g[ourVer][j] != 0) && (!visit[j])) {
                            visit[j] = true;
                            path[j] = ourVer;
                            ver.addLast(j);
                            check = true;
                            if (j == man.size() + dog.size() + 1)
                                s = true;
                            break;
                        }
                    }
                    if (!check)
                        ver.pollLast();
                    if (s)
                        break;
                }
                if (!s) ans = false;
                else {
                    pyt.add(man.size() + dog.size() + 1);
                    int k = path[man.size() + dog.size() + 1];
                    while (k >= 0) {
                        pyt.add(k);
                        k = path[k];
                    }

                    int min = 2147483647;
                    for (int i = pyt.size() - 1; i > 0; i--) {
                        if (g[pyt.get(i)][pyt.get(i - 1)] < min) {
                            min = g[pyt.get(i)][pyt.get(i - 1)];
                        }
                    }
                    for (int i = pyt.size() - 1; i > 0; i--) {
                        g[pyt.get(i)][pyt.get(i - 1)] -= min;
                        g[pyt.get(i - 1)][pyt.get(i)] += min;
                    }
                }
            }


            int answer = 0;
            for(int i = 0;i<manNum + dogNum + 1; i++){
                answer += g[i][0];
            }
            writer.write(Integer.toString(manNum+answer)+ " "+ Integer.toString(answer));
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
