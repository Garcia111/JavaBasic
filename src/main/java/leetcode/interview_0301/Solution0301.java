package leetcode.interview_0301;

/**
 * @author：Cheng.
 * @since： 2020/06/11
 */
public class Solution0301 {

    public static void main(String[] args) {
        TripleInOne tripleInOne = new TripleInOne(18);
        System.out.println(tripleInOne.peek(1));

        tripleInOne.push(0,2);
        int result1= tripleInOne.pop(0);
        System.out.println(result1);
        int result2 = tripleInOne.pop(0);
        System.out.println(result2);
        int result3 = tripleInOne.pop(0);
        System.out.println(result3);
        boolean emptyResult = tripleInOne.isEmpty(1);
        System.out.println(emptyResult);
    }
}


/**
 * 使用数组来实现栈
 */
class TripleInOne{


    int[] stackArray;
    int count = 0;

    public TripleInOne(int statckSize){
        stackArray = new int[statckSize];
    }


    public void push(int statckNum, int value){
        if(count >= stackArray.length){
        }else {
            stackArray[count] = value;
            count++;
        }
    }


    public int pop(int statckNum){
        if(count>0){
            int result = stackArray[count-1];
            stackArray[count-1] = 0;
            count--;
            return result;
        }else{
            return -1;
        }
    }


    public int peek(int statckNum){
        if(count>0){
            return stackArray[count-1];
        }else{
            return -1;
        }
    }

    public boolean isEmpty(int stackNum) {
        if(count>=0){
            return true;
        }
        return false;
    }
}