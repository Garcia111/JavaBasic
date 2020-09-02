package leetcode.algorithm;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 *
 * <p>首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 *
 * <p>如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 *
 * <p>注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 *
 * <p>在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 *
 *
 * @author：Cheng.
 * @since： 20200825
 */
public class al07_20200825 {
    public static void main(String[] args) {
        Solution07 solution07 = new Solution07();
//        System.out.println(solution07.myAtoi("42"));
        System.out.println(solution07.myAtoi("-"));
//        System.out.println(solution07.myAtoi("4193 with words"));
//        System.out.println(solution07.myAtoi("words and 987"));
//        System.out.println(solution07.myAtoi("-91283472332"));
    }
}


class Solution07 {

    public int myAtoi(String str) {

        char[] charArray = str.toCharArray();
        int n = charArray.length;
        int result = 0;
        int index = 0;
        //去除前面的空格
        while (index<n &&charArray[index]== ' '){
            index++;
        }
        //全部为空格，则返回0
        if(index == n){
            return 0;
        }
        //查看符号位
        boolean negative = false;
        if(charArray[index] == '-' ){
            negative = true;
            index++;
        }else if(charArray[index] == '+'){
            negative = false;
            index++;
        }
        //判断除符号位之外，第一个字母是否为数字
        if(index== n || !Character.isDigit(charArray[index])){
            return 0;
        }
        while (index< n && Character.isDigit(charArray[index])){
            int dis = charArray[index] - '0';
            if(result>(Integer.MAX_VALUE-dis)/10){
                //溢出了 因为不能用long型存储值，所以需要提前判断result
                //判断result*10+dis > MAX_VALUE
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result*10 + dis;
            index++;
        }
        return negative ? -result : result;
    }
}
