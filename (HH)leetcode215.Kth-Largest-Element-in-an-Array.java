import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

// heap version
// Time: O(NLogK)
// Space: O(K)
class Solution1 {
    public int findKthLargest(int[] nums, int k) {

        Queue<Integer> queue = new PriorityQueue<>(); // size = k
        for (int i = 0; i < nums.length; i++) {

            if (queue.size() < k) {
                queue.offer(nums[i]);
            } else if (queue.size() == k) {
                if (nums[i] > queue.peek()) {
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }

        }

        return queue.peek();
    }
}

// quick-select version
// TC: O(N) worst O(N^2)
// SC: O(N)
class Solution2 {
    public int findKthLargest(int[] nums, int k) {

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        return quickSelect(list, k);
    }

    private int quickSelect(List<Integer> list, int k) {

        int pivotIndex = new Random().nextInt(list.size());
        int pivot = list.get(pivotIndex);

        List<Integer> left = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int num : list) {
            if (num > pivot) {
                left.add(num);
            } else if (num < pivot) {
                right.add(num);
            } else if (num == pivot) {
                mid.add(num);
            }
        }

        if (k <= left.size()) {
            return quickSelect(left, k);
        } else if (k > left.size() + mid.size()) {
            return quickSelect(right, k - (left.size() + mid.size())); // no need this 2 big list
        }

        return pivot;
    }
}