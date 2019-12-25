package com.example.javabasic.suanfa.sort.merge_sort;

import com.example.javabasic.suanfa.sort.Example;

import static com.example.javabasic.suanfa.sort.Example.less;

/**
 * @author：Cheng.
 * @date：Created in 17:07 2019/12/25
 */
public class MyInPlaceMergeSort extends Example {


    public static void main(String[] args) {
        Integer a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 49, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        sort(a);
        show(a);
    }

    public static void sort(Comparable[] a){
        realMergeSort(a,0,a.length-1);
    }

    /**
     * 将数组进行分组拆分比较
     * @param a
     * @param start
     * @param end
     */
    public static void realMergeSort(Comparable[] a, int start, int end){

        if (start<end){
            //进行小组拆分
            int mid = (start+end)/2;
            realMergeSort(a,start,mid);
            realMergeSort(a,mid+1,end);
            //进行小组比较
            merge(a,start,mid,end);
        }
    }


    public static void merge(Comparable[] a, int p, int q, int r){
        int q1 = q+1;
        int step=0;
        while (p<=q && q1<=r){
            while (p<=q && less(a[p],a[q1])){
                p++;
            }
            while (q1<=r && less(a[q1],a[p])){
                q1++;
                step++;
            }
            //将p~q, q+1~q+step两部分进行对调旋转
            exchange(a,p,q,q1-1);
            p=p+step;
        }
    }


    /**
     * 将p~q，q+1~r两部分进行对调旋转，分为三步：
     * 1.将p~q进行反转，将q+1~r进行反转；
     * 2.将反转后的q~p r~q+1两部分进行拼接；
     * 3.将拼接后的q~p，r~q+1 进行反转 ：q+1~r p~q
     * @param a
     * @param p
     * @param q
     * @param r
     */
    public static void exchange(Comparable[] a, int p, int q, int r){
        reverse(a,p,q);
        reverse(a,q+1,r);
        reverse(a,p,r);
    }


    /**
     * 将数组中的元素p~q进行反转
     * @param a
     * @param p
     * @param q
     */
    public static void reverse(Comparable[] a, int p, int q){
        while (p<q){
            exch(a,p,q);
            p++;
            q--;
        }
    }
}
