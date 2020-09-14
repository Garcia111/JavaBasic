package leetcode.algorithm;

import java.util.Arrays;

/**
 * 剑指offer05
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * <p>示例 1： 输入：s = "We are happy." 输出："We%20are%20happy."
 *
 * @author：Cheng.
 * @since： al05
 */
public class al05 {

    public static void main(String[] args) {
        Solution05 solution05 = new Solution05();
        System.out.println(solution05.replaceSpace("We are happy."));
    }
}


class Solution05 {


    /**
     * 方法1.统计空格的个数，然后申请一个新的数组空间，填充该数组，然后将数组转换为字符串
     * 时间复杂度：需要遍历两遍原字符串，O(n)
     * 空间复杂度：需要申请一个新字符串长度大小的数组，O（n）
     * 新知识：如果将一个数组进行扩展，例如数组A中的元素为We are happy. 数组扩展之后的内容为We%20are%20happy.
     * 如何能在保证时间复杂度的情况下，缩小空间复杂度？
     * 直接将原数组进行扩展，扩展数组之后，使用双指针，分别指向原数组的尾部，和新数组的尾部，从后向前对新数组进行填充
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        //1.首先统计空格个数
        int blankCount = 0;
        int sOldSize = s.length();

        for(int i=0; i<sOldSize; i++){
            if(s.charAt(i)==' '){
                blankCount++;
            }
        }

        int sNewSize = sOldSize-blankCount + blankCount*3;
        char[] array = new char[sNewSize];
        for(int i=0,j=0;  ((i<s.length()) && (j<sNewSize)); i++,j++){
            if(s.charAt(i)!= ' '){
                array[j] = s.charAt(i);
            }else{
                array[j] = '%';
                array[j+1] = '2';
                array[j+2] = '0';
                j=j+2;
            }
        }
        return String.valueOf(array);
    }
}



















