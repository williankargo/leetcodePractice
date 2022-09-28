import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {

    // Time: O(N)
    // Space: O(N)
    // https://github.com/SharingSource/LogicStack-LeetCode/blob/main/LeetCode/131-140/138.%20%E5%A4%8D%E5%88%B6%E5%B8%A6%E9%9A%8F%E6%9C%BA%E6%8C%87%E9%92%88%E7%9A%84%E9%93%BE%E8%A1%A8%EF%BC%88%E4%B8%AD%E7%AD%89%EF%BC%89.md
    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Node dummy = new Node(-1);
        Node curr = dummy;

        // copy the next relationships
        Node pointToHead = head;
        while (head != null) {
            Node copy = new Node(head.val);
            curr.next = copy;
            map.put(head, curr.next);
            head = head.next;
            curr = curr.next;
        }

        // copy the random relationships
        head = pointToHead;
        curr = dummy.next;
        while (head != null) {
            curr.random = map.get(head.random);
            head = head.next;
            curr = curr.next;
        }

        return dummy.next;
    }
}