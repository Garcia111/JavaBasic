package com.example.javabasic.suanfa.binary_tree;



/**
 * @author：Cheng.
 * @date：Created in 9:41 2019/12/24
 */
public class SubTree {

    /**
     * 判断root2是不是root1的子结构：递归取root1的子树与root2进行比较
     * @param root1
     * @param root2
     * @return
     */
    public boolean isSubree(TreeNode root1,TreeNode root2){
            if(root1 == null){
                return false;
            }if(root2 == null){
                return true;
            }else if(root1.data == root2.data && isSame(root1,root2)){
                //如果root1.datda==root2.data，就判断两棵树结构是否相同，如果相同就返回true，否则继续递归匹配左右子树
                return true;
            }else{
                return isSubree(root1.leftNode,root2)||isSubree(root1.rightNode,root2);
            }

    }


    /**
     * 用于判断两棵树是否相等
     * @param root1
     * @param root2
     * @return
     */
    private boolean isSame(TreeNode root1, TreeNode root2){
        if(root2 == null){
            return true;
        }if(root1 == null){
            return false;
        }if(root1.data != root2.data){
            return false;
        }

        return isSame(root1.leftNode,root2.leftNode) &&
                isSame(root1.rightNode,root2.rightNode);

    }


    private static class TreeNode{
        int data;
        TreeNode leftNode;
        TreeNode rightNode;

        public TreeNode(int data){
            this.data = data;
        }

    }

}
