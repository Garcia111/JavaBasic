package leetcode.interview_0106;


/**
 * @author：Cheng.
 * @since： 2020/05/06
 */
public class Solution0106 {
    public String compressString(String S) {
        char[] array = S.toCharArray();
        int count = 1;
        if(array.length==1){
            return S;
        }
        StringBuilder s = new StringBuilder();
        for(int i=0; i<array.length-1; i++){

            if(array[i] == array[i+1]){
                if((i+1)<array.length-1){
                    count++;
                }else if((i+1)==array.length-1){
                    count++;
                    s.append(array[i]).append(count);
                }
            }else{
                if((i+1)<array.length-1){
                    s.append(array[i]).append(count);
                    count=1;
                }else if((i+1)==array.length-1){
                    s.append(array[i]).append(count);
                    s.append(array[i+1]).append(1);
                }
            }
        }
        String str = s.toString();
        if(str.length()>=array.length){
            return S;
        }else{
            return s.toString();
        }
    }


    public static void main(String[] args) {
        Solution0106 s = new Solution0106();
        String result = s.compressString("bbbac");
        System.out.println(result);
    }
}
