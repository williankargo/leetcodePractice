import java.util.Arrays;

// https://www.jiuzhang.com/solutions/two-sum-difference-equals-to-target

class Solution {

    // 同向雙指針
    // Time: O(N)
    // Space: O(1)
    public int[] twoSum7(int[] nums, int target) {

        if (nums.length < 2 || nums == null) {
            return new int[] { -1, -1 };
        }

        Arrays.sort(nums);
        target = Math.abs(target);

        // 同向雙指針
        int j = 1;
        for (int i = 0; i < nums.length; i++) {
            j = Math.max(i + 1, j);
            while (j < nums.length && nums[j] - nums[i] < target) { // j往大找，找到大於target後叫i跟上來，兩個指針都不會回頭
                j++;
            }
            if (j >= nums.length) {
                break;
            }
            if (nums[j] - nums[i] == target) {
                return new int[] { nums[i], nums[j] };
            }
        }

        // 無解
        return new int[] { -1, -1 };
    }

}