import java.util.TreeSet;

class Solution {

    // 同向雙指針
    // Time: O(n)
    // Space: O(n)
    public int longestSubarray(int[] nums, int limit) {

        if (nums == null) {
            return 0;
        }

        int j = 0; // 指向substring末尾的後一個
        int longest = 0;
        TreeSet<Integer> set = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]); // 讓set可以排到相等，而不會被忽略
        set.add(0); // 為了防止nullException，先塞東西進去
        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length && nums[set.last()] - nums[set.first()] <= limit) { // 這裡記得要判斷『末尾的後一個』，先判斷未來的
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

/** bs version */
class Solution2 {

    // Time: O(logN * N)
    // Space: O(N)
    public int longestSubarray(int[] nums, int limit) {

        if (nums.length == 0 || nums == null) {
            return 0;
        }

        // 因為有兩段性，低於某個長度後就正確，超過某個長度就不正確，所以可以用二分法
        // 依照長度來二分
        int leftLength = 1;
        int rightLength = nums.length;
        while (leftLength + 1 < rightLength) {
            int midLength = leftLength + (rightLength - leftLength) / 2;
            if (isAval(midLength, nums, limit)) {
                leftLength = midLength; // 當前長度可以，可以再長一點試試看
            } else if (!isAval(midLength, nums, limit)) {
                rightLength = midLength; // 當前長度不可以，縮短一點試試看
            }
        }

        // 長一點的先判斷
        if (isAval(rightLength, nums, limit)) {
            return rightLength;
        }

        if (isAval(leftLength, nums, limit)) {
            return leftLength;
        }

        return 0; // 沒有答案
    }

    // 滑動窗口
    private boolean isAval(int length, int[] nums, int limit) {

        TreeSet<Integer> treeset = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]); // 設計成可以存重複的
        int rightIndex = 0; // 當前長度的末尾後一個
        for (int leftIndex = 0; leftIndex < nums.length; leftIndex++) {

            while (rightIndex < nums.length && rightIndex - leftIndex < length) {
                treeset.add(rightIndex);
                rightIndex++;
            }

            if (rightIndex <= nums.length && rightIndex - leftIndex >= length) {

                if (nums[treeset.last()] - nums[treeset.first()] <= limit) {
                    return true;
                }
            } else {
                return false;
            }

            // 窗口要向右了
            treeset.remove(leftIndex);
        }
        return false;
    }

}