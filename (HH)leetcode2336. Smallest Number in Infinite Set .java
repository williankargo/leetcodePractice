import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

// Method 1: PriorityQueue
// TC: O((m+n)logn)
// SC: O(n)
class SmallestInfiniteSet1 {

    int currentNum;
    Queue<Integer> queue;
    Set<Integer> isVisited;

    public SmallestInfiniteSet1() {
        this.currentNum = 1;
        this.queue = new PriorityQueue<>();
        this.isVisited = new HashSet<>();
    }

    public int popSmallest() { // m
        if (queue.isEmpty()) {
            return currentNum++;
        } else {
            int smallest = queue.poll();
            isVisited.remove(smallest);
            return smallest;
        }
    }

    public void addBack(int num) { // n
        if (num >= currentNum || isVisited.contains(num)) {
            return;
        } else {
            queue.offer(num);
            isVisited.add(num);
        }
    }
}

// Method 2: TreeSet
// TC: O((m+n)logn)
// SC: O(n)
class SmallestInfiniteSet {

    int currentNum;
    TreeSet<Integer> set;

    public SmallestInfiniteSet() {
        this.currentNum = 1;
        this.set = new TreeSet<>();
    }

    public int popSmallest() { // m
        if (set.isEmpty()) {
            return currentNum++;
        } else {
            int smallest = set.first();
            set.remove(smallest);
            return smallest;
        }
    }

    public void addBack(int num) { // n
        if (num >= currentNum || set.contains(num)) {
            return;
        } else {
            set.add(num);
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */