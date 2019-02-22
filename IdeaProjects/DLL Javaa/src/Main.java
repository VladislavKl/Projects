import java.util.Scanner;

///System/Library/Frameworks/JavaVM.framework/Versions/A/Headers
public class Main {

    native int dijkstra(int from, int to);
    native String creator();

    static {
        System.loadLibrary("Main");
    }

    static public void main(String argv[]) {
        Main m = new Main();
        try {
            System.out.println(m.creator());
        }
        catch (NullPointerException ex){ }
        System.out.println("Enter vertex from and vertex to");
        Scanner in = new Scanner(System.in);
        System.out.println("Antwort: " + m.dijkstra(in.nextInt(), in.nextInt()));
    }
}