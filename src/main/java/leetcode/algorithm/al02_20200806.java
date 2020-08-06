package leetcode.algorithm;

import leetcode.ListNode;

/**
 * @author：Cheng.
 * @since：
 */
public class al02_20200806 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;

        ListNode node11 = new ListNode(5);
        ListNode node22 = new ListNode(6);
        ListNode node33 = new ListNode(4);
        node11.next = node22;
        node22.next = node33;

        Solution solution = new Solution();
        ListNode node = solution.addTwoNumbers(node1,node11);
        while (node != null){
            System.out.println( node.val);
            node = node.next;
        }
    }
}

class Solution{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        int jinwei = 0;
        ListNode headNode = null;
        ListNode sumNode;

        if(l1 != null && l2 != null){
            int sum = l1.val+l2.val+jinwei;
            if(sum>=10){
                headNode = new ListNode(sum-10);
                jinwei = 1;
            }else{
                headNode = new ListNode(sum);
                jinwei = 0;
            }
        }else if(l1 == null && l2 != null){
            headNode = new ListNode(l2.val);
            jinwei = 0;
        }else if(l1 != null){
            headNode = new ListNode(l1.val);
            jinwei = 0;
        }
        sumNode = headNode;


        while (l1.next != null && l2.next !=null){
            l1 = l1.next;
            l2 = l2.next;
            int sum = l1.val+l2.val+jinwei;
            ListNode node = null;
            if( sum >= 10 ){
                node = new ListNode(sum-10);
                jinwei = 1;
            }else{
                node = new ListNode(sum);
                jinwei = 0;
            }
            sumNode.next = node;
            sumNode = node;
        }

        while (l1.next != null){
            l1 = l1.next;
            int sum = l1.val+jinwei;
            ListNode node = null;
            if( sum >= 10 ){
                node = new ListNode(sum-10);
                jinwei = 1;
            }else{
                node = new ListNode(sum);
                jinwei = 0;
            }
            sumNode.next = node;
            sumNode = node;
        }

        while (l2.next != null){
            l2 = l2.next;
            int sum = l2.val+jinwei;
            ListNode node = null;
            if( sum >= 10 ){
                node = new ListNode(sum-10);
                jinwei = 1;
            }else{
                node = new ListNode(sum);
                jinwei = 0;
            }
            sumNode.next = node;
            sumNode = node;
        }

        if(jinwei >0){
            ListNode node = new ListNode(jinwei);
            sumNode.next = node;
            sumNode = node;
        }
        return headNode;

    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2){
        //首先新建一个头结点，最后返回的结点为dummyHead.next
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, cur = dummyHead;
        int carry = 0;
        while ( p!= null || q!= null){
            int x = (p != null )?p.val :0;
            int y = (q != null )?q.val :0;
            int sum = carry+x+y;
            carry = sum/10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if(p != null){
                p = p.next;
            }
            if(q != null){
                q = q.next;
            }
        }
        if(carry >0){
            cur.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}

