package leetcode.algorithm;

import org.apache.logging.log4j.util.Strings;

import java.util.*;

/**
 * leetcode **
 * @author：Cheng.
 * @since： 2020/08/06
 */
public class al03_20200806 {
    public static void main(String[] args) {
        Solution03 solution = new Solution03();
        int lenght = solution.lengthOfLongestSubstring("dvdf");
        System.out.println("lenght = " + lenght);
    }

}

/**
 * 求无重复字符的最长子串，使用窗口滑动方法
 */
class Solution03{
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        //i是左指针的位置，rk是右指针的位置，ans是当前最长无重复子串的长度
        int  rk=-1, ans = 0;

        //左指针从左向右进行移动
        for(int i=0; i<s.length(); i++){
            if(i!=0){
                //窗口左侧指针向右侧移动
                set.remove(s.charAt(i-1));
            }
            while (rk+1<s.length() && !set.contains(s.charAt(rk+1))){
                //滑动窗口右侧指针向右移动
                set.add(s.charAt(rk+1));
                rk++;
            }
            ans = Math.max(ans,rk-i+1);
        }
        return ans;
    }
}