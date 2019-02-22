package com.company;



public class Rat implements Cloneable, Comparable<Rat> {
    public Rat(){
        num = 0;
        denum = 1;

    }

    public Rat(int _num){
        num = _num;
        denum = 1;

    }

    public Rat(int _num, int _denum){
        num = _num;
        denum = _denum;

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

    public void uproshsh()
    {
        //if (this.num >= this.denum) {
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
        // }
        /*
        else
        {
            int b = this.getDenum();
            for (int i = 2; i <= b; i++)
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
        */
    }


    public Rat mult (Rat r) {
        Rat temp = new Rat(this);
        temp.num = this.num * r.num;
        temp.denum = this.denum * r.denum;
        temp.uproshsh();
        return temp;
    }

    public Rat multTo (Rat r) {
        this.num = this.num * r.num;
        this.denum = this.denum * r.denum;
        this.uproshsh();
        Rat temp = new Rat(this);
        return temp;
    }

    public Rat divide (Rat r) {
        Rat temp = new Rat(this);
        temp.num = this.num * r.denum;
        temp.denum = this.denum * r.num;
        temp.uproshsh();
        return temp;
    }

    public Rat divideTo (Rat r) {
        this.num = this.num * r.denum;
        this.denum = this.denum * r.num;
        this.uproshsh();
        Rat temp = new Rat(this);
        return temp;
    }

    public Rat plus (Rat r){
        //
        Rat temp = new Rat(this);
        temp.num = r.num*this.denum + r.denum*this.num;
        temp.denum = r.denum*this.denum;
        temp.uproshsh();
        return temp;
    }

    public Rat plusTo (Rat r){
        this.num = r.num*this.denum + r.denum*this.num;
        this.denum = r.denum*this.denum;
        this.uproshsh();
        Rat temp = new Rat(this);
        return temp;
    }

    public Rat plusTo (int n)
    {
        this.num += n * this.denum;
        this.uproshsh();
        Rat temp = new Rat(this);
        return temp;
    }

    public Rat plus (int n)
    {
        Rat temp = new Rat(this);
        temp.num += n * this.denum;
        temp.uproshsh();
        return temp;
    }

    public Rat minusTo (int n)
    {
        this.num -= n * this.denum;
        this.uproshsh();
        Rat temp = new Rat(this);
        return temp;
    }

    public Rat minus (int n)
    {
        Rat temp = new Rat(this);
        temp.num -= n * this.denum;
        temp.uproshsh();
        return temp;
    }

    public Rat multTo (int n)
    {
        this.num *= n ;
        this.uproshsh();
        Rat temp = new Rat(this);
        return temp;
    }

    public Rat mult (int n)
    {
        Rat temp = new Rat(this);
        temp.num *= n;
        temp.uproshsh();
        return temp;
    }

    public Rat divideTo (int n)
    {

        this.denum *= n ;
        this.uproshsh();
        Rat temp = new Rat(this);
        return temp;
    }

    public Rat divide (int n)
    {
        Rat temp = new Rat(this);
        temp.denum *= n ;
        temp.uproshsh();
        return temp;
    }

    public Rat minus (Rat r){
        Rat temp = new Rat(this);
        temp.num = r.num*this.denum - r.denum*this.num;
        temp.denum = r.denum*this.denum;
        temp.uproshsh();
        return temp;
    }

    public Rat minusTo (Rat r){
        this.num = r.num*this.denum - r.denum*this.num;
        this.denum = r.denum*this.denum;
        this.uproshsh();
        Rat temp = new Rat(this);
        return temp;
    }

    public boolean isSmaller(Rat r)
    {
        r.uproshsh();
        this.uproshsh();
        return (this.num*r.denum)/(this.denum*r.denum) < (r.num*this.denum)/(this.denum*r.denum);
    }

    public boolean isBigger(Rat r)
    {
        r.uproshsh();
        this.uproshsh();
        return (this.num*r.denum)/(this.denum*r.denum) > (r.num*this.denum)/(this.denum*r.denum);
    }

    public boolean isequale (Rat r)
    {
        r.uproshsh();
        this.uproshsh();
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
    /*
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    */
    // @Override
    public boolean equalsto(Object obj) {
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
