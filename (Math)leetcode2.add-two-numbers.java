
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        int sum = 0;
        while (l1 != null || l2 != null) {
            int num1 = (l1 == null ? 0 : l1.val);
            int num2 = (l2 == null ? 0 : l2.val);
            sum = num1 + num2 + sum;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            // 看sum有沒有要進位，如果有進位，下一次就要加上去
            sum = sum / 10;
            l1 = (l1 != null ? l1.next : null);
            l2 = (l2 != null ? l2.next : null);
        }

        // 最高一位如果需要進位
        if (sum > 0) {
            curr.next = new ListNode(sum % 10);
        }

        return dummy.next;
    }
}