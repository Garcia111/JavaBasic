package leetcode.interview_0301;

/**
 * @author：Cheng.
 * @since： 2020/06/11
 */
public class Solution0301 {

    public static void main(String[] args) {
        TripleInOne tripleInOne = new TripleInOne(2);

        tripleInOne.push(0,1);
        tripleInOne.push(0,2);
        tripleInOne.push(0,3);
        int result1= tripleInOne.pop(0);
        System.out.println(result1);
        int result2 = tripleInOne.pop(0);
        System.out.println(result2);
        int result3 = tripleInOne.pop(0);
        System.out.println(result3);
        int result4= tripleInOne.peek(0);
        System.out.println(result4);
    }
}


/**
 * 使用数组来实现三个栈  三个栈 三个栈
 */
class TripleInOne{

    private int[] arr;//存放三个栈的所有元素
    private int[] stackTop; // 每个栈当前可push的索引（对应到arr中的索引）
    private int stackSize;//栈大小

    public TripleInOne(int stackSize) {
        this.stackSize = stackSize;
        arr = new int[stackSize * 3];
        stackTop = new int[]{0, 1, 2};//存放的是三个栈的栈顶元素在arr中的索引位置
    }

    /**
     *
     * @param stackNum 向哪个栈中添加元素
     * @param value 待添加的值
     */
    public void push(int stackNum, int value) {
        int curStackTop = stackTop[stackNum];//得到当前下标对应的栈的栈顶下标
        if (curStackTop / 3 == stackSize) {
            // 栈已满
            return;
        }
        arr[curStackTop] = value;//将这个元素加入当前栈的栈顶
        stackTop[stackNum] += 3;//栈顶下标+3
    }


    /**
     * pop哪个栈的元素
     * @param stackNum 栈的索引位置
     * @return
     */
    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {//当栈为空时，返回-1
            return -1;
        }
        int value = arr[stackTop[stackNum] - 3];//删除对应的栈顶元素,前面加入元素的时候加了3，现在要减掉
        stackTop[stackNum] -= 3;//栈顶下标减小3
        return value;
    }

    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        return arr[stackTop[stackNum] - 3];
    }

    public boolean isEmpty(int stackNum) {
        return stackTop[stackNum] < 3;//当栈顶下标小于3，说明没有元素了
    }

}