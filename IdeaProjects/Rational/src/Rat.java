public class Rat implements Cloneable, Comparable<Rat> {
    public Rat(){
        num = 0;
        denum = 1;
        simplify();
    }

    public void setDenum(int denum) {
        this.denum = denum;
        simplify();
    }

    public void setNum(int num) {
        this.num = num;
        simplify();
    }

    public Rat(int _num){
        num = _num;
        denum = 1;
        simplify();
    }

    public Rat(int _num, int _denum){
        num = _num;
        denum = _denum;
        simplify();
    }

    public Rat(Rat r){
        num = r.num;
        denum = r.denum;
    }

    public int getNum()
    {
        return this.num;
    }

    public int getDenum()
    {
        return this.denum;
    }

    public void simplify()
    {
        int a = this.getNum();
        for (int i = 2; i <= a; i++)
        {
            if ((this.num % i == 0) && (this.denum % i == 0))
            {
                while ((this.num % i == 0) && (this.denum % i == 0))
                {
                    this.num = this.num / i;
                    this.denum = this.denum / i;
                }
            }
        }
    }


    public Rat mult (Rat r) {
        Rat temp = new Rat(this);
        temp.num = this.num * r.num;
        temp.denum = this.denum * r.denum;
        temp.simplify();
        return temp;
    }

    public Rat multTo (Rat r) {
        this.num = this.num * r.num;
        this.denum = this.denum * r.denum;
        this.simplify();
        Rat temp = new Rat(this);
        return temp;
    }

    public Rat divide (Rat r) {
        Rat temp = new Rat(this);
        temp.num = this.num * r.denum;
        temp.denum = this.denum * r.num;
        temp.simplify();
        return temp;
    }

    public Rat divideTo (Rat r) {
        this.num = this.num * r.denum;
        this.denum = this.denum * r.num;
        this.simplify();
        Rat temp = new Rat(this);
        return temp;
    }

    public Rat plus (Rat r){
        Rat temp = new Rat(this);
        temp.num = r.num*this.denum + r.denum*this.num;
        temp.denum = r.denum*this.denum;
        temp.simplify();
        return temp;
    }

    public Rat plusTo (Rat r){
        this.num = r.num*this.denum + r.denum*this.num;
        this.denum = r.denum*this.denum;
        this.simplify();
        return this;
    }

    public Rat plusTo (int n)
    {
        this.num += n * this.denum;
        this.simplify();
        Rat temp = new Rat(this);
        return temp;
    }

    public Rat plus (int n)
    {
        Rat temp = new Rat(this);
        temp.num += n * this.denum;
        temp.simplify();
        return temp;
    }

    public Rat minusTo (int n)
    {
        this.num -= n * this.denum;
        this.simplify();
        return this;
    }

    public Rat minus (int n)
    {
        Rat temp = new Rat(this);
        temp.num -= n * this.denum;
        temp.simplify();
        return temp;
    }

    public Rat multTo (int n)
    {
        this.num *= n ;
        this.simplify();

        return this;
    }

    public Rat mult (int n)
    {
        Rat temp = new Rat(this);
        temp.num *= n;
        temp.simplify();
        return temp;
    }

    public Rat divideTo (int n)
    {

        this.denum *= n ;
        this.simplify();
        return this;
    }

    public Rat divide (int n)
    {
        Rat temp = new Rat(this);
        temp.denum *= n ;
        temp.simplify();
        return temp;
    }

    public Rat minus (Rat r){
        Rat temp = new Rat(this);
        temp.num = r.num*this.denum - r.denum*this.num;
        temp.denum = r.denum*this.denum;
        temp.simplify();
        return temp;
    }

    public Rat minusTo (Rat r){
        this.num = r.num*this.denum - r.denum*this.num;
        this.denum = r.denum*this.denum;
        this.simplify();
        return this;
    }

    public boolean isSmaller(Rat r)
    {
        r.simplify();
        this.simplify();
        return (this.num*r.denum)/(this.denum*r.denum) < (r.num*this.denum)/(this.denum*r.denum);
    }

    public boolean isBigger(Rat r)
    {
        r.simplify();
        this.simplify();
        return (this.num*r.denum)/(this.denum*r.denum) > (r.num*this.denum)/(this.denum*r.denum);
    }

    public boolean isEqualToFraction (Rat r)
    {
        r.simplify();
        this.simplify();
        if ((this.num == r.num) && (this.denum == r.denum))
            return true;
        else
            return false;
    }

    @Override
    public Rat clone() throws CloneNotSupportedException
    {
        return  (Rat) super.clone();
    }

    public boolean equalsTo(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Rat temp = (Rat)obj;
        if (num == temp.num && denum == temp.denum) return true;
        else return false;
    }



    @Override
    public int compareTo(Rat r){
        return num*r.denum - r.num*denum;
    }

    @Override
    public String toString() {
        return  Integer.toString(num) + "/" + Integer.toString(denum);
    }

    private int num;
    private int denum;

}
