package leetcode.algorithm;

/**
 * @author：Cheng.
 * @since： 0907
 */
public class al10 {

    public static void main(String[] args) {
        Solution10 solution10 = new Solution10();
        System.out.println(solution10.isMatch("aaa","ab*a*c*a"));
    }
}


class Solution10{
    public boolean isMatch(String s, String p){
        //首先创建一个[s.length+1][p.length+1]的二维矩阵
        boolean[][] table = new boolean[s.length()+1][p.length()+1];
        table[0][0] = true;
        //然后填充此矩阵的第一行
        for(int index=1; index<table[0].length; index++){
            char pchar = p.charAt(index-1);
            if(index==1){
                if(pchar == '*'){
                    table[0][index] = true;
                }else{
                    table[0][index] = false;
                }
            }else{
                if(pchar == '*'){
                    //如果pattern中字符为*,则倒回两个去
                    table[0][index] = table[0][index-2];
                }else{
                    table[0][index] = false;
                }
            }
        }
        for(int row =1; row<table.length; row++){
            char schar = s.charAt(row-1);
            for (int col=1; col<table[0].length; col++){
                char pchar = p.charAt(col-1);
                if(pchar == schar || pchar == '.'){
                    //与其左上角元素一致
                    table[row][col] = table[row-1][col-1];
                }else if(pchar == '*'){
                    //同一行倒回两个去看是否为true
                    if(table[row][col - 2]){
                        table[row][col] = true;
                    }else{
                        char prechar = p.charAt(col-2);
                        if(prechar == schar || prechar == '.'){
                            table[row][col] = table[row-1][col];
                        }else{
                            table[row][col] = false;
                        }
                    }
                }
            }
        }

        return table[s.length()][p.length()];
    }
}
















