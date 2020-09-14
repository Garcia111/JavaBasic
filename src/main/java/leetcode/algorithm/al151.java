package leetcode.algorithm;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *示例 1： 输入: "the sky is blue" 输出: "blue is sky the"
 *示例 2： 输入: " hello world! " 输出: "world! hello" 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *示例 3： 输入: "a good example" 输出: "example good a" 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * @author：Cheng.
 * @since： 0914
 */
public class al151 {

    public static void main(String[] args) {
        Solution151 solution151 = new Solution151();
        String s = "  Hello  World ";
        s = solution151.reverseWords(s);
        System.out.println("s="+s);
    }
}

class Solution151 {
    public String reverseWords(String s) {
        //1.首先去掉所有多余空格
        s = removeExtraSpaces(s);
        //2.将整个字符串反转
        s = reverse(s,0,s.length()-1);
        //3.将每个单词反转

        //单词的起始位置
        int start = 0;
        //单词的终止位置
        int end = 0;
        //是否进入单词区间
        boolean entryFlag = false;

        for(int i=0; i<s.length(); i++){
            if(!entryFlag || (s.charAt(i)!=' ' && s.charAt(i-1) == ' ')){
                entryFlag = true;
                start = i;
            }
            if(entryFlag && s.charAt(i)==' ' && s.charAt(i-1)!=' '){
                entryFlag = false;
                end = i-1;
                s = reverse(s,start, end);
            }
            //最后一个单词结尾没有空格
            if(entryFlag && i== s.length()-1 && s.charAt(i)!=' '){
                entryFlag = false;
                end = i;
                s = reverse(s,start, end);
            }
        }
        return s;
    }

    /**
     * 使用快慢指针消除多余空格
     * @param s
     */
    public String removeExtraSpaces(String s){
        int slowIndex = 0, fastIndex = 0;
        char[] array = s.toCharArray();
        //去掉字符串前面的空格
        while (array.length>0 && fastIndex<array.length && array[fastIndex] == ' '){
            fastIndex++;
        }

        //去掉字符串中间部分的冗余空格
        for(; fastIndex<array.length; fastIndex++){
            if(fastIndex -1 >0 && array[fastIndex-1]== array[fastIndex] && array[fastIndex] == ' '){
                continue;
            }else{
                array[slowIndex] = array[fastIndex];
                slowIndex++;
            }
        }

        //去除字符串结尾的空格
        if(slowIndex -1 >0 && array[slowIndex-1] == ' '){
            char[] arrayNew = new char[slowIndex-1];
            System.arraycopy(array,0,arrayNew,0,slowIndex-1);
            array = arrayNew;
        }else{
            char[] arrayNew = new char[slowIndex];
            System.arraycopy(array,0,arrayNew,0,slowIndex);
            array = arrayNew;
        }

        return String.valueOf(array);
    }

    /**
     * 反转字符串
     * @param s
     * @param start
     * @param end
     */
    public String reverse(String s, int start, int end){
        char[] array = new char[s.length()];
        for(int i=start,j=end; j>i; i++,j--){
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
       return String.valueOf(array);
    }

}