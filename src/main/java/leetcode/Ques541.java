package leetcode;

/**
 * 反转字符串 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
 *
 * <p>如果剩余字符少于 k 个，则将剩余字符全部反转。 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 * 【思路】：当需要固定规律一段一段去处理字符串的时候，要想想在for循环的表达式上做文章
 * @author：Cheng.
 * @since： 0908
 */
public class Ques541 {

    public static void main(String[] args) {
        Solution541 solution541 = new Solution541();
        String reverseStr = solution541.reverseStr("abcdefg",2);
        System.out.println("reverseStr = " + reverseStr);
    }
}


class Solution541{
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();

       for(int i=0; i<chars.length;i+=2*k){
           if(i+k <= chars.length){
               reverse(chars,i,i+k-1);
               continue;
           }else {
               //将剩余的字符全部反转
               reverse(chars,i,s.length()-1);
           }
       }
       return String.valueOf(chars);
    }

    public void reverse(char[] s, int start, int end){

        for(int i= start, j=end; i<start+(end-start+1)/2; i++,j--){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }

    }
}