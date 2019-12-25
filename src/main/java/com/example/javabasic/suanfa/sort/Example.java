package com.example.javabasic.suanfa.sort;

/**
 * @author：Cheng.
 * @date：Created in 13:53 2019/12/24
 */
public abstract class Example {

    public static void sort(Comparable[] a){};

    public static boolean less(Comparable v, Comparable w){
        //v小于w返回true,否则返回false
        return v.compareTo(w)<0;
    }

    /**
     * 交换元素
     * @param a
     * @param i
     * @param j
     */
    public static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i]=a[j];
        a[j]=t;
    }

    /**
     * 打印数组中的元素
     */
    public static void show(Comparable[] a){
        for(int i=0; i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    /**
     * 检验数组元素是否排序，从小到大排序
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a){
        for(int i=1; i<a.length; i++){
            if(less(a[i],a[i-1])){
                return false;
            }
        }
        return true;
    }





}
