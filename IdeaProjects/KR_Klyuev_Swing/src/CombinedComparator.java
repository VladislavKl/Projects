import java.util.Comparator;
/*
public class CombinedComparator implements Comparator<Drink> {

    @Override
    public int compare(Drink o1, Drink o2) throws NullPointerException {
        int flag=Drink.CostComparator.compare(o1,o2);
        if(flag!=0) return -flag;
        flag=Drink.CaffeineComparator.compare(o1,o2);
        if (flag!=0) return flag;
        return Drink.NameComparator.compare(o1,o2);
    }
}
*/