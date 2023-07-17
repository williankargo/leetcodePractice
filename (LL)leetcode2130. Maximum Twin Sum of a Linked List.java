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
    public int pairSum(ListNode head) {

        if (head == null) {
            return 0;
        }

        // calculate the total length
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        // go to the mid point
        int midPoint = length / 2;
        ListNode rightPoint = head;
        for (int i = 0; i < midPoint; i++) {
            rightPoint = rightPoint.next;
        }

        // reverse the right part of LL
        ListNode prevP = null;
        ListNode nextP = rightPoint.next;
        while (nextP != null) {
            rightPoint.next = prevP;
            prevP = rightPoint;
            rightPoint = nextP;
            nextP = nextP.next;
        }
        rightPoint.next = prevP;

        // calculate the sum
        int sum = 0;
        int maxSum = -Integer.MAX_VALUE;
        for (int i = 0; i < midPoint; i++) {
            sum = head.val + rightPoint.val;
            maxSum = Math.max(sum, maxSum);
            head = head.next;
            rightPoint = rightPoint.next;
        }

        return maxSum;
    }
}