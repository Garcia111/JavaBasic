package com.example.javabasic.leetcode.interview_0108;

import com.example.javabasic.leetcode.interview_0107.Solution0107;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：Cheng.
 * @since： 2020/05/12
 */
public class Solution0108 {

    public void setZeroes(int[][] matrix) {
        boolean[] col = new boolean[matrix[0].length];
        boolean[] row = new boolean[matrix.length];

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                if(matrix[i][j]==0){
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for(int i=0; i<row.length; i++){
            if(row[i]==true){
                for(int j=0; j<matrix[i].length; j++){
                    matrix[i][j]=0;
                }
            }
        }

        for(int j=0; j<col.length; j++){
            if(col[j]==true){
                for(int i=0; i<matrix.length; i++){
                    matrix[i][j]=0;
                }
            }
        }
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
        Solution0108 solution0108 = new Solution0108();

        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        printMatrix(matrix);
        System.out.println("==========");
        solution0108.setZeroes(matrix);
        printMatrix(matrix);
    }
}
