/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// itereate version
// Time: O(N)
// Space: O(1)
class Solution1 {
    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        return prev;
    }
}

// recurrsion version
// Time: O(N)
// Space: O(N) 遞歸了N層
class Solution2 {

    // https://www.jiuzhang.com/problem/reverse-linked-list/
    // 0. 會返回已經翻轉好後的head
    public ListNode reverseList(ListNode head) {

        // corner case
        if (head == null) {
            return head;
        }

        // 1.
        if (head.next == null) {
            return head;
        }

        // [1] > [2] > null
        // 2.
        ListNode tail = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return tail;
    }
}