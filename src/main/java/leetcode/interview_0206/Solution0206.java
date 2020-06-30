package leetcode.interview_0206;

import leetcode.ListNode;

/**
 * @author：Cheng.
 * @since： 2020/05/23
 */
public class Solution0206 {
    //方法1：
    //1.使用快慢指针找到链表的中间节点
    //2.将链表的后半部分进行链表反转
    //3.比较中间节点前后两部分是否相等

    //方法2：
    //反转整个列表，比较翻转后的链表与原链表是否相等

    //方法3：
    //将链表的前半部分入栈，比较前后两部分是否相等

    /**
     * 判断是否是回文链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }
        ListNode middleNode = getMiddleNode(head);
        ListNode headPost = reverseList(middleNode.next);
        ListNode headPre = head;

        while (headPost != null){
            if(headPre.val != headPost.val){
                return false;
            }
            headPre = headPre.next;
            headPost = headPost.next;
        }
        return true;
    }


    /**
     * 使用快慢指针找到链表的中间节点
     * @param headNode
     * @return
     */
    public ListNode getMiddleNode(ListNode headNode){
        ListNode fast = headNode;
        ListNode slow = headNode;
        while (fast.next != null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * 反转链表
     * @param headNode
     * @return
     */
    public ListNode reverseList(ListNode headNode){

        ListNode pre = null;
        ListNode cur = headNode;
        while (cur != null){
            //将cur进行剔除
            ListNode nextNode = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextNode;


        }
        return pre;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        Solution0206 solution0206 = new Solution0206();
        Boolean flag = solution0206.isPalindrome(node1);
        System.out.println(flag);
    }
}
