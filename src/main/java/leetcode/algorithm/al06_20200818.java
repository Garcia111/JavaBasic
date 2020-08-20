package leetcode.algorithm;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * <p>比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 *    L   C   I   R
 *    E T O E S I I G
 *    E   D   H   N
 *
 *
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"
 *
 *
 * 思路：其实是一个N字形，对每行设置一个StringBuilder,向其中增加数据，StringBuilder的索引在向下时+1，加到
 *      临界点（numRows-1时）变为-1，-1直至为0后，又变为+1，最后把所有StringBuilder进行拼接就行
 *
 * https://www.youtube.com/channel/UC6sXxjf9HMntbtDu5SsWLAg
 *
 * @author：Cheng.
 * @since： 20200818
 */
public class al06_20200818 {
    public String convert(String s, int numRows) {
        if(s==null || s.length()==0 || numRows==1) return s;
        StringBuilder[] array = new StringBuilder[numRows];
        for(int i=0; i<numRows; i++) array[i] = new StringBuilder();

        int dierction = 1;
        int index = 0;
        for(char c : s.toCharArray()){
            array[index].append(c);
            index+=dierction;
            if(index==0 || index==numRows-1){
                dierction = -dierction;
            }
        }
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<numRows; i++){
            builder.append(array[i]);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        al06_20200818 al = new al06_20200818();
        String s = al.convert("LEETCODEISHIRING",3);
        System.out.println("s = " + s);
    }
}
