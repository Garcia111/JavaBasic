package leetcode.interview_0204;

import leetcode.ListNode;

/**
 * @author：Cheng.
 * @since： 2020/05/16
 */
public class Solution0204 {

    public ListNode partition(ListNode head, int x) {
        if(head==null) return null;
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode prev=head;
        head=head.next;
        while(head!=null){
            //不会比较pre.val与x的值，如果pre.val的值小于x，正好；
            // 如果pre.val的值大于x，后续的值会添加到pre之前，因此不会影响
            if(head.val<x){
                prev.next=head.next;
                head.next=dummy.next;
                dummy.next=head;
                head=prev.next;
            }else{
                prev=prev.next;
                head=head.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(8);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(10);
        ListNode node6 = new ListNode(2);
        ListNode node7 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        Solution0204 solution0204 = new Solution0204();
        ListNode head = solution0204.partition(node1,5);

        while (head !=null){
            System.out.print(head.val+" ");
            head = head.next;
        }

    }
}
