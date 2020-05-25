package com.example.javabasic.leetcode.interview_0205;

import com.example.javabasic.leetcode.ListNode;

import java.util.*;


/**
 * @author：Cheng.
 * @since： 2020/05/17
 */
public class Solution0205 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode headPre = new ListNode(-1);
        ListNode pre = headPre;
        int jinwei = 0;
        while (l1!=null || l2 != null){
            int result1 = 0;
            int result2 = 0;
            if(l1!=null){
                result1 = l1.val;
            }
            if(l2!=null){
                result2 = l2.val;
            }
            int addResult = result1 + result2 + jinwei;
           if(pre.next == null){
               //pre的后面没有值，生成一个新的node，放在pre后面
               if(addResult>=10){
                   //在pre的next后面插入一个新值,pre.next指向新的值
                   ListNode newNode = new ListNode(addResult-10);
                   pre.next = newNode;
                   jinwei = 1;
               }else {
                   ListNode newNode = new ListNode(addResult);
                   pre.next = newNode;
                   jinwei = 0;
               }
               headPre.next = pre.next;
           }else{
               //pre的后面有值，生成一个新的node，pre后移一位，新的值放在pre的后面
               ListNode newNode;
               if(addResult>=10){
                   newNode = new ListNode(addResult-10);
                   jinwei = 1;
               }else {
                   newNode = new ListNode(addResult);
                   jinwei = 0;
               }
               pre = pre.next;
               pre.next = newNode;

           }
            if(l1!=null && l1.next!=null){
                l1 = l1.next;
            }else{
                l1 = null;
            }

            if(l2!=null && l2.next !=null){
                l2 = l2.next;
            }else{
                l2 = null;
            }

        }
        //最高位相加为10，生成一个新的node放在最后
        if(jinwei != 0 ){
            ListNode newNode = new ListNode(jinwei);
            pre = pre.next;
            pre.next = newNode;

        }
        return headPre.next;
    }

    public static void main(String[] args) {
//      617+295=
        ListNode node11 = new ListNode(1);
        ListNode node12 = new ListNode(8);

        ListNode node21 = new ListNode(0);


        node11.next = node12;

        Solution0205 solution0205 = new Solution0205();
        ListNode head = solution0205.addTwoNumbers(node11,node21);

        while (head !=null){
            System.out.print(head.val+" ");
            head = head.next;
        }

    }



}
