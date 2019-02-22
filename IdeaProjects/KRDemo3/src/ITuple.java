
public interface ITuple
{
    public abstract ITuple max (ITuple o);
    //	public abstract ITuple<T> max (ITuple<T> o);
//	public abstract int less(ITuple<? extends T> t);
    public abstract int less(ITuple t);
    public abstract int value();
}
