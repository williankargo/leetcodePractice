import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    // Space: O(K+1)
    int size;
    Queue<Integer> minHeap;

    public Solution(int k) {
        this.size = k;
        minHeap = new PriorityQueue<>();
    }

    // Time: O(logk) #(Heap: offer() -> logK)
    public void add(int num) {

        minHeap.offer(num);

        if (minHeap.size() > size) {
            minHeap.poll();
        }
    }

    // Time: O(K + KlogK) #(quicksort: logk)
    public List<Integer> topk() {
        List<Integer> result = new ArrayList<>(minHeap);
        Collections.sort(result, Collections.reverseOrder());

        return result;
    }
}