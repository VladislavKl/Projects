import java.util.Iterator;

public class RIter <T extends IList&Comparable<T>> implements Iterator<T> {
    private RLjv<T> CortegeForIter;
    private int idx;
    RIter(RLjv<T> q) {
        CortegeForIter = q;
        idx = -1;
    }

    @Override
    public boolean hasNext() {
        return idx<(CortegeForIter.size() - 1);
    }

    @Override
    public T next() {
        return (T) CortegeForIter.getValue(++idx);
    }
}

