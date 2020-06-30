package leetcode.interview_0104;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class Solution0104 {

    public boolean canPermutePalindrome(String s) {
        char[] array = s.toCharArray();
        int length = s.length();
        Map<Character,Integer> map = new HashMap<>();
        for(char c :array){
            if(!map.containsKey(c)){
                map.put(c,1);
            }else{
                int count = map.get(c);
                map.put(c,(count+1)%2);
            }
        }

        if(length%2==0){
            //s长度为偶数，值应该全部为0
            if(map.containsValue(1)){
                return false;
            }else {
                return true;
            }
        }else{
            //s长度为奇数，值应该包含一个1，其余全部为0
            if(!map.containsValue(1)){
                //不包含1
                return false;
            }else {
                map.values().remove(1);
                if(map.containsValue(1)){
                    return false;
                }
                return true;
            }
        }
    }


    public static void main(String[] args) {
        Solution0104 solution0104 = new Solution0104();
        System.out.println(solution0104.canPermutePalindrome("tactcoa"));
    }
}
