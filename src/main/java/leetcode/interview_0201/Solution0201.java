package leetcode.interview_0201;

import leetcode.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author：Cheng.
 * @since： 2020/05/14
 */
public class Solution0201 {

    public ListNode removeDuplicateNodes(ListNode head) {
        if(head==null){
            return head;
        }

        Set<Integer> set = new HashSet<Integer>();
        set.add(head.val);
        ListNode temp = head;
        while (temp.next != null){
            if(set.contains(temp.next.val)){
                temp.next = temp.next.next;
            }else{
                set.add(temp.next.val);
                temp = temp.next;
            }
        }
        return head;
    }


    public static void main(String[] args) {
        Solution0201 solution0201 = new Solution0201();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(2);
        ListNode n6 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        ListNode newHead = solution0201.removeDuplicateNodes(n1);
        System.out.println(newHead.val);
        while (newHead.next!=null){
            System.out.println(newHead.next.val);
            newHead = newHead.next;
        }
    }

}


