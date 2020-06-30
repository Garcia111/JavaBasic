package leetcode.interview_0109;

/**
 * @author：Cheng.
 * @since： 20200512
 */
public class Solution0109 {

    public boolean isFlipedString(String s1, String s2) {

        if(s1.length()!= s2.length()){
            return false;
        }else if(s1.equals(s2)){
            return true;
        }else {
            //好厉害
            s1+=s1;
            return s1.contains(s2);
        }

    }

    public static void main(String[] args) {
        Solution0109 solution0109 = new Solution0109();
        Boolean flag = solution0109.isFlipedString("waterbottle", "erbottlewat");
        System.out.println(flag);
    }

}
