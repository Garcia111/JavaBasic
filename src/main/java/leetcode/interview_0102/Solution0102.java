package leetcode.interview_0102;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class Solution0102 {
    public boolean CheckPermutation(String s1, String s2) {
        if(s1==null || s2== null){
            return false;
        }

        if (s1.length() != s2.length()) {
            return false;
        }

        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        for(char c : char1){
          if(!map1.containsKey(c)){
              map1.put(c,1);
          }  else{
              int count = map1.get(c);
              map1.put(c,count+1);
          }
        }

        for(char c : char2){
            if(!map2.containsKey(c)){
                map2.put(c,1);
            }  else{
                int count = map2.get(c);
                map2.put(c,count+1);
            }
        }

        for(char c :char1){
            if(!map2.containsKey(c) || !map1.get(c).equals(map2.get(c))){
                return false;
            }
        }

        for(char c :char2){
            if(!map1.containsKey(c) || !map1.get(c).equals(map2.get(c))){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
       String s1 = "a", s2 = "ab";
        Solution0102 solution0102 = new Solution0102();
       System.out.println( solution0102.CheckPermutation(s1, s2));
    }
}
