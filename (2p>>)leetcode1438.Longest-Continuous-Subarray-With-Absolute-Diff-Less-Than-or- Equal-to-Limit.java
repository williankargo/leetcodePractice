import java.util.TreeSet;

class Solution {

    // 同向雙指針
    public int longestSubarray(int[] nums, int limit) {

        if (nums == null) {
            return 0;
        }

        int j = 0; // 指向substring末尾的後一個
        int longest = 0;
        TreeSet<Integer> set = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]); // 讓set可以排到相等，而不會被忽略
        set.add(0); // 為了防止nullException，先塞東西進去
        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length && nums[set.last()] - nums[set.first()] <= limit) { // 這裡記得要判斷『末尾的後一個』
                j++;
                if (j < nums.length) {
                    set.add(j);
                }
            }

            if (j < nums.length && nums[set.last()] - nums[set.first()] > limit) {
                // 當前末尾下一位會超過limit
                longest = Math.max(longest, (j - i));
            } else {
                // j走完了
                return Math.max(longest, (j - i));
            }

            set.remove(i); // i要前進一位
        }

        return longest;
    }
}