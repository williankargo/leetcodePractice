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
    public ListNode deleteMiddle(ListNode head) {

        ListNode temp = head;
        int length = 0;
        while (temp != null) {
            temp = temp.next;
            length++;
        }

        // corner case: length = 1
        if (length == 1) {
            return null;
        }

        int deletePoint = length / 2;
        int currPoint = 0;
        ListNode curr = head;
        while (currPoint + 1 != deletePoint) { // 停在要deleted point的前一個
            curr = curr.next;
            currPoint++;
        }

        curr.next = curr.next.next;

        return head;
    }
}