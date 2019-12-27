package com.example.javabasic.suanfa.sort.quick_sort;

import com.example.javabasic.suanfa.sort.Example;

/**
 * @author：Cheng.
 * @date：Created in 10:36 2019/12/27
 * 快速排序的递归实现
 */
public class QuickSortWithRecursive extends Example {


    public static <T extends Comparable<? super T>> void sort(T[] array, int startIndex, int endIndex){
        while (startIndex >= endIndex){
            return;
        }
        int pivotIndex = partion(array,startIndex, endIndex);

        //对数组分成的两个子数组进行排序
        sort(array,startIndex,pivotIndex-1);
        sort(array,pivotIndex+1,endIndex);
    }


    /**
     * 将数组分为两段，选定基准元素为数组的第一个元素，左边是小于该元素的值，右边是大于该元素的值
     * @param array
     * @param startIndex
     * @param endIndex
     * @param <T>
     * @return 返回基准元素最后所处的索引位置
     */
    public static  <T extends Comparable<? super T>> int  partion(T[] array, int startIndex, int endIndex){
        T pivot = array[startIndex];
        int left = startIndex;
        int right = endIndex;

        //left != right的条件既设定的循环停止的条件，也保证了left right指针不会越界
        while (left != right){
            //每次排序都要从右侧开始
            while (left< right && array[right].compareTo(pivot)>0){
                right--;
            }

            while (left <right && array[left].compareTo(pivot)<0){
                left++;
            }

            if(left <right){
                T p = array[left];
                array[left] = array[right];
                array[right] = p;
            }
        }
        //交换pivot与startIndex endIndex所指向的值‘
        T temp = array[left];
        array[left] = array[startIndex];
        array[startIndex] = temp;
        return left;
    }





    public static void main(String[] args) {
        Integer[] a = new Integer[]{9,8,7,6,5,4,3,2,1};
        sort(a,0,a.length-1);
        show(a);
    }

}
