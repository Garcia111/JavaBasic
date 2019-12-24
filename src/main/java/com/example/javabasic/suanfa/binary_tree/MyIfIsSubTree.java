package com.example.javabasic.suanfa.binary_tree;

/**
 * @author：Cheng.
 * @date：Created in 11:06 2019/12/24
 */
public class MyIfIsSubTree {

    private static class TreeNode{
        int data;
        TreeNode leftChild;
        TreeNode rightChild;
    }


    public boolean isSubTree(TreeNode treeNode1,TreeNode treeNode2){
        if(treeNode1 == null){
            return false;
        }else if(treeNode2 == null){
            return true;
        }else if(treeNode1.data == treeNode2.data && isSame(treeNode1,treeNode2)){
            return true;
        }else{
            return isSubTree(treeNode1.leftChild,treeNode2)|| isSubTree(treeNode1.rightChild,treeNode2);
        }
    }


    private boolean isSame(TreeNode treeNode1,TreeNode treeNode2){
        if(treeNode2 == null){
            return true;
        }if(treeNode1 == null){
            return false;
        }else if(treeNode1.data == treeNode2.data){
            return isSame(treeNode1.leftChild,treeNode2.leftChild) && isSame(treeNode1.rightChild,treeNode2.rightChild);
        }
        return false;
    }

}
