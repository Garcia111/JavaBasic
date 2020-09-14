package leetcode.algorithm;

import java.util.Stack;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * <p>示例 1:
 * 123 = 1*100+2*10+3
 * <p>输入: 123 输出: 321  
 *
 * 示例 2:
 *
 * <p>输入: -123 输出: -321
 *
 *
 * 示例 3:
 *
 * <p>输入: 120 输出: 21 注意:
 *
 * <p>假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 *
 *
 */
public class al06 {
    public static void main(String[] args){
        Solution06 solution06 = new Solution06();
//        System.out.println(solution06.reverse(123));
//        System.out.println(solution06.reverse(-123));
        System.out.println(solution06.reverse(-2147483412));
    }

}

class Solution06 {

    public int reverse(int x) {

        int max = 2147483647;
        int min = -2147483638;
        if(x<min || x>max) {
            return 0;
        }

        int result = 0;
        while (x!=0){
            int tmp = x%10;

            if(result>214748364||(result == 214748364 && tmp>7)){
                return 0;
            }
            if(result<-214748364 || (result==-214748364 && tmp<-8)){
                return 0;
            }
            result = result*10+tmp;
            x = x/10;

        }
        return result;
    }
}