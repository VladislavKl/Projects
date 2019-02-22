package com.company;

import java.util.Scanner;

public class Main {

    public static double Mult(int x, int y, int n)
    {
        double sum=0, t=1, m=x, mult=1;
        for (int i=1;i<=n;++i)
        {
            t*=y;
            sum+=t;
            m*=-x*x/sum;
            mult*=1+m;
        }
        return mult;
    }
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int x=in.nextInt(),y=in.nextInt(),n=in.nextInt();
        System.out.println(Mult(x,y,n));
    }
}
