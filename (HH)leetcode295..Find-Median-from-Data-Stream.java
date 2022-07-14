import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class MedianFinder {

    // Space: O(N)
    Queue<Integer> minHeap = new PriorityQueue<>();
    Queue<Integer> maxHeap = new PriorityQueue<>(
            new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) { // 要寫成Integer!
                    return o2 - o1;
                }
            });
    boolean isFirstAdded;

    public MedianFinder() {
        this.isFirstAdded = true;
    }

    // Time: O(logN)
    public void addNum(int num) {

        if (isFirstAdded) {
            maxHeap.offer(num);
            this.isFirstAdded = false;
            return;
        }

        if (num <= maxHeap.peek()) { // 要寫成 <= 不是 =<
            maxHeap.offer(num);
            if (maxHeap.size() - 1 > minHeap.size()) {
                minHeap.offer(maxHeap.poll());
            }
        } else {
            minHeap.offer(num);
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
    }

    // Time: O(1)
    public double findMedian() {

        if (minHeap.size() == maxHeap.size()) {

            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else {

            return Double.valueOf(maxHeap.peek()); // convert to Double
        }

    }
}