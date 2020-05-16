package com.example.javabasic.leetcode.interview_0107;


/**
 * @author：Cheng.
 * @since： 2020/05/11
 */
public class Solution0107 {


    //矩阵旋转：先转置然后再将每一行倒序
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
        printMatrix(matrix);
    }




    public static void printMatrix(int[][] matrix){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Solution0107 solution0107 = new Solution0107();

        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        printMatrix(matrix);
        System.out.println("==========");
        solution0107.rotate(matrix);

    }

}
