import java.util.Comparator;

public class CombinedComparator implements Comparator<Drink> {

    @Override
    public int compare(Drink o1, Drink o2) {
        int flag=Drink.CostComparator.compare(o1,o2);
        if(flag==0)
        {
            flag=Drink.CaffeineComparator.compare(o1,o2);
            if (flag==0)
            {
                return Drink.NameComparator.compare(o1,o2);
            }
            return flag;
        }
        return -flag;
    }
}