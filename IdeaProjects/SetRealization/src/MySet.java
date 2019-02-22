import java.util.*;

public class MySet<T> implements Collection<T>, Iterable<T>, Comparable<MySet<T>>{

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int CurrentIndex = 0;

            @Override
            public boolean hasNext() {
                return CurrentIndex < set.size()-1 && set.get(CurrentIndex)!= null;
            }

            @Override
            public T next() {
                if (hasNext())
                    return set.get(CurrentIndex++);
                else
                    throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
                if(!hasNext())
                    set.remove(CurrentIndex);
            }
        };
        return it;
    }

    @Override
    public int compareTo(MySet<T> o) {
        if (set==o.set)
            return 0;
        else
            return -1;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        if (set.size()==0)
            return true;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (this.contains(o)) {
            set.remove(o);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String str="{ ";
       for (T o: set) {
           str += o;
           str+=" ";
       }
        str+="}";
        return str;
    }

    public MySet() {
        set=new ArrayList<T>();
        set.trimToSize();
    }

    @SafeVarargs
    public MySet(T ... args) {
        for (T o : args)
            set.add(o);
        set.trimToSize();
    }

    public T at(int index){
        return set.get(index);
    }

    public void assign(T[] args){
        set.clear();
        Collections.addAll(set, args);
        set.trimToSize();
    }

    public boolean equalsTo(MySet t){
        return (set==t.set ? true : false);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public int size(){
        set.trimToSize();
        return set.size();
    }

    @Override
    public void clear(){
        set.clear();
        set.trimToSize();
    }

    @Override
    public boolean contains(Object s){
        boolean b=false;
        for (Object o: set)
            if (o==s)
                return true;
        return false;
    }

    public boolean swap(MySet<T> s){
        if (s.set==this.set)
            return false;
        MySet<T> temp=new MySet<T>();
        temp.set=s.set;
        s.set=this.set;
        set=temp.set;
        set.trimToSize();
        s.set.trimToSize();
        temp.set.trimToSize();
        return true;
    }

    public boolean equalTo(MySet<T> s){
        if (s.set==this.set)
            return true;
        else
            return false;
    }

    public boolean notEqualTo(MySet<T> s){
        if (s.set==this.set)
            return false;
        else
            return true;
    }

    @Override
    public boolean add(T s){
       if (!set.contains(s)) {
           set.add(s);
           set.trimToSize();
           return true;
       }
       return false;
    }

    public MySet<T> plus(MySet<T> s){
        MySet<T> temp=new MySet<T>();
        temp.assign((T[])s.set.toArray());
        for (T o : this.set) {
            boolean b=false;
            for (T j : s.set)
                if (o==j)
                    b=true;
            if(b==false)
                temp.add((T)o);
        }
        set.trimToSize();
        s.set.trimToSize();
        temp.set.trimToSize();
        return temp;
    }

    public void assignPlus(MySet<T> s){
        this.assign((T[])this.plus(s).set.toArray());
        set.trimToSize();
        s.set.trimToSize();
    }


    public MySet<T> intersection(MySet<T> s){
        MySet<T> temp=new MySet<T>();
        for (T o : this.set) {
            boolean b=false;
            for (T j : s.set)
                if (o==j)
                    b=true;
            if(b==true)
                temp.add((T)o);
        }
        set.trimToSize();
        s.set.trimToSize();
        temp.set.trimToSize();
        return temp;
    }

    public void assignIntersection(MySet<T> s){
        this.assign((T[])this.intersection(s).set.toArray());
        set.trimToSize();
        s.set.trimToSize();
    }

    public MySet<T> difference(MySet<T> s){
        MySet<T> temp=new MySet<T>();
        temp.assign((T[])this.intersection(s).set.toArray());
        ArrayList<T> t=new ArrayList<T>(temp.set);
        temp.assign((T[])this.set.toArray());
        for (T o: t)
            temp.set.remove(o);
        set.trimToSize();
        s.set.trimToSize();
        temp.set.trimToSize();
        return temp;
    }

    public void assignDifference(MySet<T> s){
        this.assign((T[])this.difference(s).set.toArray());
        set.trimToSize();
        s.set.trimToSize();
    }

    public MySet<T> symmetricDifference(MySet<T> s){
        MySet<T> temp=new MySet<T>();
        temp.assign((T[])(this.plus(s).difference(this.intersection(s)).set.toArray()));
        set.trimToSize();
        s.set.trimToSize();
        temp.set.trimToSize();
        return temp;
    }

    public void assignSymmetricDifference(MySet<T> s){
        this.assign((T[])this.symmetricDifference(s).set.toArray());
        set.trimToSize();
        s.set.trimToSize();
    }

    public boolean isLess(MySet<T> s){
        if (this.equalsTo(s))
            return false;
        for (T o : this.set) {
           boolean b=false;
           for (T j : s.set)
               if(o==j)
                   b=true;
           if (b==false)
               return false;
       }
       return true;
    }

    public boolean isLessOrEqual(MySet<T> s){
        if (this.equalsTo(s))
            return true;
        return false;
    }

    public boolean isGreater(MySet<T> s){
        if (this.equalsTo(s))
            return false;
        for (T o : s.set) {
            boolean b=false;
            for (T j : this.set)
                if(o==j)
                    b=true;
            if (b==false)
                return false;
        }
        return true;
    }

    public boolean isGreaterOrEqual(MySet<T> s){
        if (this.equalsTo(s))
            return true;
        return false;
    }

    private ArrayList<T> set;
}