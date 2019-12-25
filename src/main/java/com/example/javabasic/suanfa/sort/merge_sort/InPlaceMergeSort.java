package com.example.javabasic.suanfa.sort.merge_sort;

/**
 * @author：Cheng.
 * @date：Created in 16:31 2019/12/25
 */

import java.util.Arrays;

/**
 * Created by damon on 9/18/16.
 * 原地归并排序
 */

public class InPlaceMergeSort {
    //详见: http://www.cnblogs.com/xiaorenwu702/p/5880841.html

    public static void main(String[] args){
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 49, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        mergeSort(a);

    }

    private static void swip(int[] a, int p,int q){
        int temp = a[p];
        a[p]=a[q];
        a[q]=temp;
    }
    private static void reverse(int[] a, int p,int q){
        int i = p;
        int j = q;
        while(i<j){
            swip(a,i,j);
            i++;
            j--;
        }
    }

    //旋转对调
    private static void exchange(int[] a, int p,int q,int r){
        reverse(a,p,q);
        reverse(a,q+1,r);
        reverse(a,p,r);
    }
    public static void mergeSort(int[] a){
        realMergeSort(a,0,a.length-1);
        System.out.println("mergeSort-->>" + Arrays.toString(a));

    }
    private static void realMergeSort(int[] a,int p,int r){

        if(p<r){
            int q= (p+r)/2;
            //分组打擂台比赛
            realMergeSort(a,p,q);
            realMergeSort(a,q+1,r);
            merge(a,p,q,r);
        }
    }
    private static void merge(int[] a, int p,int q,int r){
        int q1 = q+1;
        int step=0;
        while(p<=q&&q1<=r){
            while(p<=q&&a[p]<=a[q1]){
                p++;
            }
            while(q1<=r&&a[q1]<a[p]){
                q1++;
                step++;
            }
            exchange(a,p,q,q1-1);
            p=p+step;
        }
    }
}