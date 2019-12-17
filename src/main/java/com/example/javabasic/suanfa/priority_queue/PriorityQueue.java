package com.example.javabasic.suanfa.priority_queue;


import java.util.Arrays;

/**
 * @author：Cheng.
 * @date：Created in 16:08 2019/12/17
 */
public class PriorityQueue {

    int[] array;
    //数组中实际存储元素的数量
    int size;

    public PriorityQueue(){
        array = new int[32];
    }

    /**
     * 入队
     * @param key
     */
    public void enQueue(int key){
        //判断是否需要扩容,队列已满
        if(size >= array.length){
            resize(array);
        }
        //添加元素之后进行元素上浮
        array[size++] = key;
        upAdjust(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 扩容
     * @param array
     */
    public  void resize(int[] array){
       int size = 2*this.size;
       array = Arrays.copyOf(this.array,size);
    }

    /**
     * 出队
     * @return
     */
    public int deQueue() throws Exception {
        //判断队列中是否存在元素，不存在元素则抛出异常
        if(size<=0){
            throw new Exception("队列中不存在元素了");
        }

        //取出队首的元素
        int head = array[0];
        //将队尾元素放置到队首，然后进行下沉操作
        array[0]=array[--size];
        downAdjust(array,0);
        return head;
    }


    public void upAdjust(int[] array){
        int childIndex = size-1;
        int parentIndex = (childIndex-1)/2;
        int temp = array[childIndex];


        while(childIndex >0 && temp> array[parentIndex]){
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex-1)/2;
        }
        array[childIndex] = temp;
    }


    public void downAdjust(int[] array,int parentIndex){
        int childIndex = 2*parentIndex+1;
        int temp = array[parentIndex];

        while (childIndex<size){
            if(childIndex+1<size&& array[childIndex+1]>array[childIndex]){
                childIndex++;
            }

            if(temp>=array[childIndex]){
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*parentIndex+1;

        }
        array[parentIndex] = temp;
    }



    public static void main(String[] args) throws Exception{
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);

        System.out.println("出队元素："+priorityQueue.deQueue());
        System.out.println("出队元素："+priorityQueue.deQueue());
    }








}
