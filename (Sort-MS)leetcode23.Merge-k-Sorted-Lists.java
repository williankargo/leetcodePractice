import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    
    // Time: O(NlogK), k is the current queue size
    // Space: O(500)
    // https://github.com/SharingSource/LogicStack-LeetCode/blob/main/LeetCode/21-30/23.%20%E5%90%88%E5%B9%B6K%E4%B8%AA%E5%8D%87%E5%BA%8F%E9%93%BE%E8%A1%A8%EF%BC%88%E5%9B%B0%E9%9A%BE%EF%BC%89.md
    public static final int INF = Integer.MIN_VALUE;
    public ListNode mergeKLists(ListNode[] lists) {
        
        Queue<ListNode> queue = new PriorityQueue<>((a, b) -> (a.val - b.val));
        
        ListNode dummy = new ListNode(INF);
        ListNode curr = dummy;
        
        for (ListNode node : lists){
            if (node != null){
                queue.offer(node);    
            }
        }
        
        // Queue:
        // dummy -> NodeA -> NodeB
        //                   curr
        while (!queue.isEmpty()){
            ListNode poll = queue.poll();
            curr.next = poll;
            curr = curr.next;
            if (poll.next != null){
                queue.offer(poll.next);
            }
        }
        
        return dummy.next;
    }
}