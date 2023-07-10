import java.util.ArrayDeque;
import java.util.Deque;

class RecentCounter {

    int requests = 0;
    Deque<Integer> queue;

    public RecentCounter() {
        this.requests = 0;
        queue = new ArrayDeque<>();
    }

    public int ping(int t) {

        // pay attention to empty situation !!!
        while (!queue.isEmpty() && t - 3000 > queue.peekFirst()) {
            queue.pollFirst();
        }

        queue.offerLast(t);
        return queue.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */