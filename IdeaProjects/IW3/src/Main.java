import java.util.*;
import java.lang.*;

class Value {
    private int index;
    private double value;
    Value(double value, int index) {
        this.index = index;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}

class Summary {

    public Summary(double xk, int ik, double dk, double sk) {
        this.xk = xk;
        this.ik = ik;
        this.dk = dk;
        this.sk = sk;
    }

    public double getDk() {
        return dk;
    }

    public double getSk() {
        return sk;
    }

    public double getXk() {
        return xk;
    }

    public void setDk(double dk) {
        this.dk = dk;
    }

    public int getIk() {
        return ik;
    }

    public void setIk(int ik) {
        this.ik = ik;
    }

    public void setSk(double sk) {
        this.sk = sk;
    }

    public void setXk(double xk) {
        this.xk = xk;
    }

    private double xk;
    private int ik;
    private double dk;
    private double sk;

}


public class Main {
    public static void main(String[] args) {
        ArrayList<Value> arrayList = new ArrayList<>(Arrays.asList(
                new Value(3., 0),
                new Value(6., 1),
                new Value(4., 2),
                new Value(2., 3),
                new Value(8., 4),
                new Value(7., 5),
                new Value(9., 6)
        ));
        ArrayList<Value> temporary = new ArrayList<>();
        arrayList.stream().reduce((a, b) -> {
            if (a.getIndex() % 2 == 1 && b.getIndex() % 2 == 0)
                temporary.add(new Value(a.getValue() * b.getValue(), b.getIndex()/2 + 1));
            return b;
        });
        System.out.println(temporary.stream()
                .map(a -> new Summary(a.getValue(), a.getIndex(), 1.0, 0.0))
                .reduce(new Summary(0, 1, arrayList.get(0).getValue(), arrayList.get(0).getValue()),
                        (a, b) -> {
                            a.setDk((a.getDk() * b.getXk()) / b.getIk());
                            a.setSk(a.getSk() + a.getDk());
                            return a;
                        }).getSk());
    }
}