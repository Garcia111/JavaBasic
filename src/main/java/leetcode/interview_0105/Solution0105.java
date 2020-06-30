package leetcode.interview_0105;

/**
 * @author：Cheng.
 * @since： 2020.04.23
 */
public class Solution0105 {
    public boolean oneEditAway(String first, String second) {
        //插入或删除
        if (first.length() == 0 && second.length() == 1) {
            return true;
        }
        if (second.length() == 0 && first.length() == 1) {
            return true;
        }

        if (first.length() != second.length() && Math.abs(first.length() - second.length()) == 1) {
            //插入或删除
            char[] s1 = first.length() > second.length() ? first.toCharArray() : second.toCharArray();

            char[] s2 = first.length() > second.length() ? second.toCharArray() : first.toCharArray();

            for (int i = 0; i < s2.length; i++) {
                if (s1[i] != s2[i]) {
                    if (i + 1 < first.length()) {
                        for(int j=i; j<s2.length; j++){
                            if(s1[j+1] != s2[j]){
                                return false;
                            }
                        }
                        return true;
                    } else {
                        return true;
                    }

                }
            }
            return true;
        } else if (first.length() == second.length()) {

            //需要0次替换
            if(first.equals(second)){
                return true;
            }

            //替换
            char[] s1 = first.toCharArray();

            char[] s2 = second.toCharArray();

            for (int i = 0; i < s1.length; i++) {
                if (s1[i] != s2[i]) {
                    if (i + 1 < first.length()) {
                        String str1 = first.substring(i + 1, first.length());
                        String str2 = second.substring(i + 1, second.length());
                        if (str1.equals(str2)) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return true;
                    }

                }
            }


        }
        return false;
    }

    public static void main(String[] args) {
        Solution0105 solution0105 = new Solution0105();
        System.out.println(solution0105.oneEditAway("teacher", "treacher"));
    }

}
