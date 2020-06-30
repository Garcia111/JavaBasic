package leetcode.interview_0208;

import leetcode.ListNode;

/**
 * @author：cheng
 * @since： 2020/5/27
 */
public class Solution0208 {
//
//    ①快慢指针跑，直到相遇。②一个指针从head跑，一个指针从相遇点跑，相遇点就是环路入口。
//
//    入环前路程为a，入环点到相遇点距离b，相遇点到入环点距离c。一个快指针fast，每次走2步，一个慢指针，每次走1步；
//
//    快指针路程=a+(b+c)k+b ，k>=1  其中b+c为环的长度，k为绕环的圈数（k>=1,即最少一圈，不能是0圈，不然和慢指针走的一样长，矛盾）。
//    慢指针路程=a+b
//    快指针走的路程是慢指针的两倍，所以：
//            （a+b）*2=a+(b+c)k+b
//    化简可得：
//    a=(k-1)(b+c)+c 这个式子的意思是： 链表头到环入口的距离=相遇点到环入口的距离+（k-1）圈环长度。其中k>=1,所以k-1>=0圈。
// 所以两个指针分别从链表头和相遇点出发，最后一定相遇于环入口。



    public ListNode detectCycle(ListNode head){
        if(head==null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        if(fast.next==null || fast.next.next==null){
            return null;
        }

        while(fast.next!=null && fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            //如果有环一定会相遇
            if(fast==slow){
                break;
            }
        }
        if(fast.next==null || fast.next.next==null){
            return null;
        }
        fast=head;
        while (fast != slow){
            fast=fast.next;
            slow=slow.next;
        }
        return slow;
    }

    public static void main(String[] args){
        ListNode node1= new ListNode(3);
        ListNode node2= new ListNode(2);
        ListNode node3= new ListNode(0);
        ListNode node4= new ListNode(-4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        Solution0208 solution0208= new Solution0208();
        ListNode cycleHead = solution0208.detectCycle(node1);
        System.out.println(cycleHead.val);
    }
}
