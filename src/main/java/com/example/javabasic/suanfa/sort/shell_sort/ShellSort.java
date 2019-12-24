package com.example.javabasic.suanfa.sort.shell_sort;

import com.example.javabasic.suanfa.sort.Example;

/**
 * @author：Cheng.
 * @date：Created in 15:21 2019/12/24
 */
public class ShellSort extends Example {

    public static void sort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h<N/3){
            h = 3*h+1;
        }
        while (h>=1){
            for(int i = h; i<N; i++){
                for(int j = i; j>=h && less(a[j],a[j-h]); j-=h){
                    exch(a,j,j-h);
                }
            }
            h=h/3;
        }
    }


    public static void main(String[] args) {
        Integer[] a = new Integer[]{3,6,8,1,23,2,8,4,1};
        sort(a);
        show(a);
    }


}
