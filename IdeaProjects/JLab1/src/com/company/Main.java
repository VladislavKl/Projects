package com.company;

import java.util.Scanner;

public class Main {

    public static double Mult(int x, int y, int n)
    {
        if(n==0)
            return
        double z=x, d=1, sum=0, mult=1, res=1;
        for (int i=1;i<=n;++i)
        {
            z*=(-1)*x*x;
            d*=y;
            sum+=d;
            mult*=sum;
            res*=1+z/mult;
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int x=in.nextInt(),y=in.nextInt(),n=in.nextInt();
        System.out.println(Mult(x,y,n));
    }
}
