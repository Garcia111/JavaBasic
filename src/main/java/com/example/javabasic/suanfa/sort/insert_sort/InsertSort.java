package com.example.javabasic.suanfa.sort.insert_sort;

import com.example.javabasic.suanfa.sort.Example;

/**
 * @author：Cheng.
 * @date：Created in 14:32 2019/12/24
 */
public class InsertSort extends Example {

    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i=1; i<N; i++){
            for(int j = i; j>0&& less(a[j],a[j-1]); j--){
                exch(a,j,j-1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{3,6,8,1,23,2,8,4,1};
        sort(a);
        show(a);
    }

    //todo 提高插入排序的速度，需要在内循环中将较大的元素都向右移动而不总是交换两个元素，这样访问数组的次数能减半

}
