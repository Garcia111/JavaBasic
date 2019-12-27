package com.example.javabasic.suanfa.sort.quick_sort;

import com.example.javabasic.suanfa.sort.Example;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


/**
 * @author：Cheng.
 * @date：Created in 16:00 2019/12/27
 */
public class QuickSortWithStack extends Example {

    public static final String START_INDEX = "startIndex";
    public static final String END_INDEX = "endIndex";

    //绝大多数用递归实现的问题，都可以用栈代替。因为我们代码中一层一层的方法调用，本身就是一个
    public static <T extends Comparable<? super T>> void sort(T[] array,int startIndex, int endIndex){
        Stack<Map<String,Integer>> paramStack = new Stack<Map<String,Integer>>();

        Map<String,Integer> map = new HashMap<>();
        map.put(START_INDEX,startIndex);
        map.put(END_INDEX,endIndex);
        paramStack.push(map);

        while (!paramStack.isEmpty()){
            Map<String,Integer> param = paramStack.pop();
            int start = param.get(START_INDEX);
            int end = param.get(END_INDEX);
            int pivot = partion(array,start,end);

            if(start< pivot-1){
                Map<String,Integer> leftParam = new HashMap<String,Integer>();
                leftParam.put(START_INDEX,startIndex);
                leftParam.put(END_INDEX,pivot-1);
                paramStack.push(leftParam);
            }

            if(pivot+1<endIndex){
                Map<String,Integer> rightParam = new HashMap<String,Integer>();
                rightParam.put(START_INDEX,pivot+1);
                rightParam.put(END_INDEX,endIndex);
                paramStack.push(rightParam);
            }
        }
    }


    public static <T extends Comparable<? super T>> Integer partion(T[] array, int startIndex, int endIndex){
        T pivot = array[startIndex];
        int left = startIndex;
        int right = endIndex;

        while (left != right){
            while (left < right && array[right].compareTo(pivot)>0){
                right--;
            }

            while (left<right && array[left].compareTo(pivot)<0){
                left++;
            }

            if(left < right){
                T temp = array[right];
                array[right] = array[left];
                array[left] = temp;
            }
        }

        //交换pivot与left索引处的元素
        T temp = array[left];
        array[left] = pivot;
        array[startIndex] = temp;
        return left;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{3,6,8,1,23,2,8,4,1,3};
        sort(a,0,a.length-1);
        show(a);
    }

}
