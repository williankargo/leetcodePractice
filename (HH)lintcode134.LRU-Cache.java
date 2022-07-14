import java.util.HashMap;
import java.util.Map;

class LRUCache {

    int size;
    ListNode dummy;
    ListNode tail;
    Map<Integer, ListNode> keyToPrev; // key : prevListNode 每次改node連接都要記得改這個！

    public LRUCache(int capacity) {
        this.size = capacity;
        this.dummy = new ListNode(0, 0);
        this.tail = dummy;
        this.keyToPrev = new HashMap<>();
    }

    public int get(int key) {

        if (!keyToPrev.containsKey(key)) {
            return -1;
        }

        ListNode current = keyToPrev.get(key).next;
        kickToTail(current);

        return current.value;
    }

    public void set(int key, int value) {

        // 本來就存在
        if (get(key) != -1) {
            ListNode current = keyToPrev.get(key).next;
            current.value = value;
            return;
        }

        // 本來不存在
        ListNode newNode = new ListNode(key, value);
        keyToPrev.put(key, tail);
        tail.next = newNode;
        tail = newNode;

        // 如果加上後超過大小了，要把LRU移除掉
        if (keyToPrev.size() > size) {
            ListNode LRU = dummy.next;
            dummy.next = LRU.next;
            keyToPrev.remove(LRU.key);
            keyToPrev.put(LRU.next.key, dummy);
        }
    }

    private void kickToTail(ListNode node) {
        // 本來就在tail了
        if (node == tail) {
            return;
        }
        ListNode prev = keyToPrev.get(node.key);
        prev.next = node.next;
        keyToPrev.put(prev.next.key, prev);
        node.next = null;

        tail.next = node;
        keyToPrev.put(node.key, tail);
        tail = node;
    }

    class ListNode {
        int key, value;
        ListNode next;

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}