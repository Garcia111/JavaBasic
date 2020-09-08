package leetcode;

/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 * @author：Cheng.
 * @since： 0908
 */
public class Qus344 {


    public static void main(String[] args){
        Solution344 solution344 = new Solution344();
        char[] s = new char[]{'H','E','L','L','O'};
        solution344.reverseString(s);
    }

}

class Solution344 {
    public void reverseString(char[] s) {

        for(int i = 0, j=s.length-1; i<s.length/2; i++,j--){
            //交换i j两个指针指向的元素
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
        System.out.println(String.valueOf(s));
    }
}
