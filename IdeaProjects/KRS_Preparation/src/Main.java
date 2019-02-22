import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader fr= new FileReader("file.txt");
        Scanner scan = new Scanner(fr);

        String[] strings=new String[10];
        for (int i=0;scan.hasNextLine();++i)
            strings[i]=scan.nextLine();
        for (int i=0;i<strings.length&&strings[i]!=null;++i)
            System.out.println(strings[i]);
        fr.close();

    }
}
