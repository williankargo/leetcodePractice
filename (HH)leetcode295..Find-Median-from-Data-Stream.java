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

// 我自己想的版本
class MedianFinder2 {

    // Space: O(N)
    // <-- Left Right-->
    Queue<Integer> Right = new PriorityQueue<>(); // -->
    Queue<Integer> Left = new PriorityQueue<>((o1, o2) -> (o2 - o1)); // <--

    boolean isFirstTime;

    public MedianFinder2() {
        this.isFirstTime = true;
    }

    // Time: O(logN)
    public void addNum(int num) {

        // Left會Right多一個
        if (isFirstTime) {
            Left.offer(num);
            isFirstTime = false;
            return;
        }

        // 把數字寫下來判斷
        if (num >= Left.peek()) {
            Right.offer(num);
            // 維持Left比Right多一個
            if (Right.size() - Left.size() > 0) {
                Left.offer(Right.poll());
            }
        } else {
            Left.offer(num);
            if (Left.size() - Right.size() > 1) {
                Right.offer(Left.poll());
            }
        }
    }

    // Time: O(1)
    public double findMedian() {
        if (Left.size() == Right.size()) {
            return (Left.peek() + Right.peek()) / 2.0;
        } else {
            return Left.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */