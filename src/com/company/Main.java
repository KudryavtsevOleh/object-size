package com.company;

import com.company.test.A;

public class Main {

    public static void main(String[] args) {
        System.out.println(new ObjectSize().getSize(
                new A(
                        new Object(),
                        3L,
                        4,
                        new long[] {1, 2, 3, 4, 5},
                        "test",
                        new int [][]{{1,2,3}, {1,2}}))
        );
    }
}
