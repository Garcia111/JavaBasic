package com.example.javabasic.leetcode.interview_0202;

import com.example.javabasic.leetcode.ListNode;
import com.example.javabasic.leetcode.interview_0201.Solution0201;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：Cheng.
 * @since： 2020/05/14
 */
public class Solution0202 {
    public int kthToLast(ListNode head, int k) {
        List<Integer> valList = new ArrayList<>();
        if(head!=null){
            valList.add(head.val);
        }
        ListNode temp = head;
        while (temp.next!=null){
            valList.add(temp.next.val);
            temp = temp.next;
        }
        int num = valList.size();
        int index = num-k;
        return valList.get(index);
    }

    public static void main(String[] args) {
        Solution0202 solution0202 = new Solution0202();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        int n = solution0202.kthToLast(n1,2);
        System.out.println(n);
    }
}
