import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class DataStream {
    
    // Time: O(1)
    // Space: O(N)

    // dummyNode
    ListNode dummy;
    ListNode tail;
    Map<Integer, ListNode> prevMap;
    Set<Integer> duplicate;

    public DataStream() {
        dummy = new ListNode(0);
        tail = dummy;
        prevMap = new HashMap<>();
        duplicate = new HashSet<>();
    }

    public void add(int num) {

        // 存在超過兩個
        if (duplicate.contains(num)) {
            return;
        }

        // 沒有存在過
        if (!prevMap.containsKey(num)) {
            tail.next = new ListNode(num);
            prevMap.put(num, tail);
            tail = tail.next;
            return; // void記得return跳出
        }

        // 有存在過
        ListNode prevNode = prevMap.get(num);
        prevMap.remove(num);

        if (prevNode.next.next == null) {
            tail = prevNode;
            prevNode.next = null;
        } else {
            prevMap.put(prevNode.next.next.value, prevNode);
            prevNode.next = prevNode.next.next;
        }

        duplicate.add(num);
    }

    public int firstUnique() {

        if (dummy.next == null) {
            return -1;
        }

        return dummy.next.value;
    }

    // ListNode
    class ListNode {
        ListNode prev;
        ListNode next;
        int value;

        ListNode(int value) {
            this.value = value;
        }
    }

}