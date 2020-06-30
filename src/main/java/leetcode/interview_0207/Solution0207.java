package leetcode.interview_0207;

import leetcode.ListNode;

/**
 * 链表相交
 * @author：Cheng.
 * @since： 2020/05/25
 */
public class Solution0207 {

    /**
     * 使用双指针方法求链表相交节点
     * 思路，指针A指向链表A，走完自己的链表A，然后去走链表B
     * 指针B指向链表B，走完自己的链表B，然后去走链表A
     * 两个指针走的路程同样是链表A+链表B，因为如果两个链表相交，肯定是有一段链表既在链表A上，又在链表B上
     * 两个指针的速度相同，因此如果两个链表相交，则两个指针肯定会相遇，否则如果两个指针均走完两个链表的路程，返回null
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB){
        if(headA == null || headB ==null){
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB){
            pA = (pA==null? headB: pA.next);
            pB = (pB==null? headA: pB.next);
        }
        return pA;
    }


}
