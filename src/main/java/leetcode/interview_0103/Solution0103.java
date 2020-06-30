package leetcode.interview_0103;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class Solution0103 {
    public String replaceSpaces(String S, int length) {
        return S.substring(0,length).replaceAll(" ","%20");
    }



    public static void main(String[] args) {
        Solution0103 solution0103 = new Solution0103();
        System.out.println(solution0103.replaceSpaces("Mr John Smith    ",13));
        System.out.println(solution0103.replaceSpaces("               ",5));
    }
}
