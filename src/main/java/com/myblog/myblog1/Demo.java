package com.myblog.myblog1;

public class Demo {
    public static void main(String[] args) {
        int x=100;
        int y=200;
        Demo d=new Demo();
        long p=d.test(x,y);
        System.out.println();


    }
    public int test(int a,int b){
        Demo d=new Demo();
       int x=d.evalute(a,b);

        return x;
    }
    private int evalute(int ram,int sam){

        return ram+sam;
    }

}
