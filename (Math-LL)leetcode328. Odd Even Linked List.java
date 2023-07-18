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
class Solution {

    // TC: O(N)
    // SC: O(1)
    public ListNode oddEvenList(ListNode head) {

        // corner case
        if (head == null) {
            return head;
        }

        ListNode p_odd = head;
        ListNode p_even = head.next;
        ListNode p_even_head = head.next;

        while (p_odd.next != null && p_odd.next.next != null) {
            p_odd.next = p_odd.next.next;
            p_odd = p_odd.next;
            p_even.next = p_even.next.next;
            p_even = p_even.next;
        }

        p_odd.next = p_even_head;

        return head;
    }
}