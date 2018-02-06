package com.company.test;

public class A { //8

    private Object o1; //8
    private long l; //8
    private boolean b; //1
    private boolean b2; //1
    private Integer iW; //8 + 4
    private String s; //32 + 2 * 4
    private long array[]; //12 + 40
    private int multiArray[][];


    public A(Object o1, long l, Integer iW, long[] array, String s, int multiArray[][]) {
        this.o1 = o1;
        this.l = l;
        this.iW = iW;
        this.array = array;
        this.s = s;
        this.multiArray = multiArray;
    }
}
