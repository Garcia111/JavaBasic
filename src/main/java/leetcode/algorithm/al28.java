package leetcode.algorithm;

/**
 * 实现strStr()函数，给定一个hastack字符串和一个needle字符串，
 * 在hastack字符串中找出needle字符串出现的第一个位置（从0开始），如果不存在，则返回-1
 * 当needle是空字符串时，我们应当返回0
 *
 * @author：Cheng.
 * @since： 0911
 */
public class al28 {

    public static void main(String[] args) {
        Solution28 solution28 = new Solution28();
        System.out.println(solution28.strStr("HELLO","LL"));
    }
}


class Solution28{
    public int strStr(String haystack, String needle){
        if(needle.length()==0){
            return 0;
        }
        int M = needle.length();
        int[][] dp = new int[M][256];
        dp[0][needle.charAt(0)] = 1;
        // 影子状态
        int X = 0;
        for (int j = 1; j < needle.length(); j++) {
            for (int c = 0; c < 256; c++) {
                if (needle.charAt(j) == c) {
                    dp[j][c] = j + 1;
                } else {
                    dp[j][c] = dp[X][c];
                }
            }
            X = dp[X][needle.charAt(j)];
        }

        int N = haystack.length();
        int k = 0;
        for (int i = 0; i < N; i++) {
            k = dp[k][haystack.charAt(i)];
            if (k == needle.length()) {
                return i - k + 1;
            }
        }
        return -1;
    }





}