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

 // iterate version
class Solution {

    // Time: O(N)
    // Space: O(1)
    // https://github.com/SharingSource/LogicStack-LeetCode/blob/main/LeetCode/21-30/25.%20K%20%E4%B8%AA%E4%B8%80%E7%BB%84%E7%BF%BB%E8%BD%AC%E9%93%BE%E8%A1%A8%EF%BC%88%E5%9B%B0%E9%9A%BE%EF%BC%89.md
    public ListNode reverseKGroup(ListNode head, int k) {

        // dummy node可以裝飾於原題目！
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr != null) {
            reverse(curr, k);
            int times = k;
            for (int i = 0; i < times; i++) {
                curr = curr.next;
                if (curr == null) {
                    break;
                }
            }
        }

        return dummy.next;
    }

    private void reverse(ListNode root, int times) {

        ListNode curr = root;
        for (int i = 0; i < times; i++) {
            curr = curr.next;
            if (curr == null) {
                return;
            }
        }
        ListNode currNext = curr.next;
        ListNode a = root.next, b = root.next.next;
        for (int i = 0; i < times - 1; i++) {
            ListNode c = b.next;
            b.next = a;
            a = b;
            b = c;
        }
        root.next.next = currNext;
        root.next = curr;
    }
}