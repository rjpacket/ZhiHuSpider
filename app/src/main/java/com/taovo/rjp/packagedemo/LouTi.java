package com.taovo.rjp.packagedemo;

import java.math.BigDecimal;

/**
 * @author Gimpo create on 2017/8/3 14:20
 * @email : jimbo922@163.com
 */

public class LouTi {
    public static void main(String[] args){
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        for (Integer i : arr) {
            System.out.println("---------->" + i);
        }

        BigDecimal a = BigDecimal.valueOf(1);
        BigDecimal b = BigDecimal.valueOf(2);
        BigDecimal c = BigDecimal.valueOf(4);
        BigDecimal sum = BigDecimal.valueOf(0);
        for (int i = 0; i < 97; i++) {
            sum = a.add(b).add(c);
            a = b;
            b = c;
            c = sum;
        }
        System.out.println("-------->" + sum);
    }
}
