package leetcode.interview_0203;


import leetcode.ListNode;

/**
 * @author：Cheng.
 * @since： 2020/05/14
 */
public class Solution0203 {

    //删除节点，直接将下一个节点的值复制给本节点，然后删除下一个节点
    public void deleteNode(ListNode node) {
        ListNode next=node.next;
        node.val = next.val;
        node.next = next.next;
        next.next = null;
    }


}

