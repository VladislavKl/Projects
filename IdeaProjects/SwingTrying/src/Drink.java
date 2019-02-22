import java.util.Comparator;

public class Drink implements Comparable<Object>{

    @Override
    public String toString() {
        return "Название: "+getName()+"; количество кофеина: "+getCaffeine()+"; цена: "+getCost();
    }

    public int getCost() {
        return Cost;
    }

    public String getName() {
        return Name;
    }


    public void setCost(int cost) {
        Cost = cost;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCaffeine() {
        return Caffeine;
    }

    public void setCaffeine(int caffeine) {
        Caffeine = caffeine;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    //----------------------------------------------------------------------//
                                //COMPARATORS
    public static Comparator<Drink> CostComparator = new Comparator<Drink>() {

        @Override
        public int compare(Drink e1, Drink e2) {
            return Integer.compare(e1.getCost(), e2.getCost());
        }
    };

    public static Comparator<Drink> CaffeineComparator = new Comparator<Drink>() {

        @Override
        public int compare(Drink e1, Drink e2) {
            return Integer.compare(e1.getCaffeine(), e2.getCaffeine());
        }
    };

    public static Comparator<Drink> NameComparator = new Comparator<Drink>() {

        @Override
        public int compare(Drink e1, Drink e2) {
            return e1.getName().compareTo(e2.getName());
        }
    };

    private String Name;
    private int Caffeine;
    private int Cost;


}
