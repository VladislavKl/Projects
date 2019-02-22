import java.util.Scanner;
import java.io.File;
import java.util.Collections;
import java.util.ArrayList;
import java.io.FileNotFoundException;

class ComplexNumbers implements Comparable<ComplexNumbers> {
    
    public ComplexNumbers(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(x);
        if (y >= 0)
            stringBuilder.append("+");
        stringBuilder.append(y);
        stringBuilder.append("i");
        return stringBuilder.toString();
    }

    public double getModulus() {
        return Math.sqrt(x * x + y * y);
    }
    @Override
    public int compareTo(ComplexNumbers other) {
        return -Double.compare(this.getModulus(), other.getModulus());
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && this.getClass() == o.getClass()) {
            return (Double.compare(x, ((ComplexNumbers)o).x)==0) && (Double.compare(y, ((ComplexNumbers)o).y)==0);
        }
        return false;
    }

    private double x;
    private double y;

}

public class Main {

    static ArrayList<ComplexNumbers> complexNumbers = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Scanner scanner;
            try {
                scanner = new Scanner(new File("input.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error! File is not found");
                return;
            }
            while (scanner.hasNext()) {
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                complexNumbers.add(new ComplexNumbers(x, y));
            }
            if (complexNumbers.size() != 0) {
                Collections.sort(complexNumbers);
                System.out.println("Max module complex number: \n"+complexNumbers.get(0));
                Collections.reverse(complexNumbers);
                System.out.println("Min module complex number: \n"+complexNumbers.get(0));
                System.out.println("Quantity of 2.5-3.6i: \n" + Collections.frequency(complexNumbers, new ComplexNumbers(2.5, -3.6)));
            } else {
                System.err.println("No data");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! Complex numbers interpretation has semicolon!  Delete dots or other signs!!!");
            return;
        }
    }
}