package leetcode.algorithm;

/**
 * @author：Cheng.
 * @since： 20200825
 */
public class al08_20200825 {
    public static void main(String[] args) {
        Solution08 solution08 = new Solution08();
        System.out.println(solution08.isPalindrome02(121));
        System.out.println(solution08.isPalindrome02(-121));
        System.out.println(solution08.isPalindrome02(10));
    }
}
class Solution08 {
    /**
     * 将整数转换为字符串判断是否是回文数
     * @param x
     * @return
     */
    public boolean isPalindrome01(int x) {
     if(x < 0) return false;
     if(x == 0|| (x%10 == 0)) return true;
     char[] charArray = String.valueOf(x).toCharArray();
     int left = 0, right = charArray.length-1;
     while (left <= right){
         if(charArray[left] != charArray[right]){
             return false;
         }
         left++;
         right--;
     }
     return true;
    }

    /**
     * 不将整数转换为字符串进行判断
     * @param x
     * @return
     */
    public boolean isPalindrome02(int x) {
        if(x < 0) return false;
        if(x == 0 || (x%10 == 0)) return true;
        int temp = 0;
        int origin = x;
        while (x!=0){
            int t = x%10;
            temp = temp*10 + t;
            x = (x-t)/10;
        }
        if(temp == origin){
            return true;
        }
        return false;
    }
}