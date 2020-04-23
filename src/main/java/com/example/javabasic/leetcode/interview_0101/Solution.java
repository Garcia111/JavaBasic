package com.example.javabasic.leetcode.interview_0101;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：Cheng.
 * @since： 1.2.2
 */
public class Solution {
    public boolean isUnique(String astr){

        Map<Character,Integer> map = new HashMap<>();
        char[] chars = astr.toCharArray();
        for(int i = 0; i< astr.length(); i++){
            if(!map.containsKey(chars[i])){
                map.put(chars[i],1);
            }else{
                int count = map.get(chars[i]);
                map.put(chars[i],count+1);
            }

        }
        if(map.size() == chars.length){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        String s = "abd";
        Solution solution = new Solution();
        System.out.println(solution.isUnique(s));

    }

}
