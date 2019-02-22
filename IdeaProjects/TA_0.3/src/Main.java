import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Matrix> matrices=new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        scanner.next();
        while (scanner.hasNext()) {
            matrices.add(new Matrix(Integer.parseInt(scanner.next()),Integer.parseInt(scanner.next())));
        }
        FileWriter fout = null;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            int result=0;
            Solution.findMin(matrices,result,bufferedWriter);
            bufferedWriter.close();
        } catch (IOException e) {

        }
    }
}

class Solution{

    public static void findMin(ArrayList<Matrix> matrices, int atomaricResult, BufferedWriter bufferedWriter) throws IOException {
        int min=matrices.get(0).getM()*matrices.get(0).getN()*matrices.get(1).getN(), minIndex=0;
        for (int i=1;i<matrices.size()-1;++i){
            int tempMult=matrices.get(i).getM()*matrices.get(i).getN()*matrices.get(i+1).getN();
            if (min>tempMult){
                min=tempMult;
                minIndex=i;
            }
        }
        matrices.get(minIndex+1).setM(matrices.get(minIndex).getM());
        matrices.remove(minIndex);
        atomaricResult+=min;
        if (matrices.size()==1)
            bufferedWriter.write(atomaricResult + "\n");
        else
            findMin(matrices,atomaricResult,bufferedWriter);
    }
}

class Matrix{

    public Matrix(int a, int b){
        n=b; m=a;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public void setM(int m) {
        this.m = m;
    }

    public void setN(int n) {
        this.n = n;
    }

    private int n;
    private int m;
}