public interface IList {
    public abstract IList max (IList o);
    public abstract IList min (IList o);
    public abstract int less(IList t);
    public abstract int greater(IList t);
    public abstract int value(int i, int j)throws MyExcClass;
    public abstract int value()throws MyExcClass;
    public abstract IList Plus(IList o) throws MyExcClass;


}
