package leetcode.algorithm;


/**
 * @author：Cheng.
 * @since：
 */
public class al05_20200810 {
  public static void main(String[] args) {
//    Solution05 solution05 = new Solution05();
//    String s = solution05.longestPalindrome("cbbd");
//    System.out.println(s);


    Solution0503 solution0503 = new Solution0503();
    String s = solution0503.longestPalindrome("babad");
    System.out.println(s);
  }
}


/**
 * 暴力解法：取得所有的子字符串，判断字串是否为回文字符串
 */
class Solution0501 {


    public String longestPalindrome(String s) {
        int maxLength=0;
        int beginIndex = 0;
        char[] charArray = s.toCharArray();
        for(int i=0; i<charArray.length-1;i++){
            for(int j=i+1;j<charArray.length; j++){
                if(j-i+1>maxLength && isHuiWenStr(charArray,i,j)){
                    maxLength = j-i+1;
                    beginIndex = i;
                }
            }
        }
        return s.substring(beginIndex,beginIndex+maxLength);
    }


    public Boolean isHuiWenStr(char[] charArray, int i, int j){
        while (j>i){
            if(charArray[i] != charArray[j]){
                return false;
            }else{
                i++;
                j--;
            }
        }
        return true;
    }

}


/**
 * 取回文字符串的中心进行判断，回文的中心有两种情况，只有一个中心点，有两个中心点
 * 因为中心点有两种情况，每种情况的取值个数为 (n-1),所以枚举中心点的个数为2(n-1)，每个中心点的取值都需要判断回文字符串的长度，所以
 * 算法的时间复杂度为O（n^2）
 * 空间复杂度为O（1），因为只用到常数个变量
 */
class Solution0502 {
    //中心枚举法
    public String longestPalindrome(String s) {

        char[] charArray = s.toCharArray();

        if(charArray.length == 1){
            return s;
        }

        int maxLength = 0;
        int beginIndex = 0;
        for(int i = 0; i<charArray.length-1; i++){
            //中心位置没有必要取字符串的最后一个字符进行判断
            //取i为中心点的字符串进行判断是否为回文字符串，当回文字符串只有一个中心点时，字符串长度为奇数
            int oddLength = huiWenLength(charArray,i,i);
           //取i，i+1为中心点的字符串进行判断是否为回文字符串，当回文字符串有i,j两个中心点时，字符串长度为偶数
            int evenLength = huiWenLength(charArray,i,i+1);
            int length = Math.max(oddLength, evenLength);
            if(length>maxLength){
                maxLength = length;
                beginIndex = i-(maxLength-1)/2;
            }
        }
        return s.substring(beginIndex,beginIndex+maxLength);
    }


    /**
     * 取回文串的长度，
     * @param charArray
     * @param i 回文串的中心点
     * @param j 回文串的中心点，j有可能等于i，有可能等于i+1
     * @return
     */
    public int huiWenLength(char[] charArray, int i, int j){
        if(i != j && charArray[i] != charArray[j]){
            return 0;
        }else{
            while (i>=0 && j<charArray.length){
                if(charArray[i]== charArray[j]){
                    i--;
                    j++;
                }else{
                    return j-i-1;
                }
            }
            return j-i-1;
        }
    }
}


/**
 * 动态规划算法获得最长回文子串的长度
 * 如果s[i]=s[j]，那么以i j为两边界的字符串为回文字符串的条件为{s[i+1],...s[j-1]}为回文字符串
 * 而{s[i+1],...s[j-1]}为回文字符串的基本条件为此字符串的长度至少为2，也就是（j-1）-(i+1)+1>2,即j-i>3,即j-i+1>4
 * 也就是说当s[i]=s[j]时，如果 以i j为边界的字符串的长度为2 或者3，则可以直接说{s[i],s[j]}为回文字符串
 *                      否则则需要继续判断 {s[i+1],...s[j-1]} 是否为回文字符串
 *
 * 时间复杂度：取的是二维数组的写对角线的右上角部分，因此时间复杂度为O(n^2)，空间复杂度为O(n^2)，因为每次判断dp[i][j]基本上都需要
 * 判断下dp[i-1][j-1]
 */
class Solution0503 {

  public String longestPalindrome(String s) {
      int maxLength = 1;
      int beginIndex = 0;
      char[] charArray = s.toCharArray();
      int n = charArray.length;
      if(n < 2){
          return s;
      }
      boolean[][] dp= new boolean[n][n];

      //对角线上值为长度为1的字符串，肯定是回文字符串
      for(int i=0; i<charArray.length; i++){
          dp[i][i]=true;
      }


      for(int j=1; j<charArray.length; j++){
          for(int i=0; i<j; i++){
              if(charArray[i] != charArray[j]){
                  dp[i][j] = false;
              }else{
                  if(j-i<3){
                      dp[i][j]=true;
                  }else{
                      dp[i][j] = dp[i+1][j-1];
                  }
              }
              if(dp[i][j] && (j-i+1)>maxLength){
                  maxLength = j-i+1;
                  beginIndex = i;
              }
          }
      }
      return s.substring(beginIndex,beginIndex+maxLength);
  }
}