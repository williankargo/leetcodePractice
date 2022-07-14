import java.util.Arrays;

class Solution {

    // 相向雙指針
    // 重複的部分可以進行剪枝
    // Time: O(N^2)
    // Space: O(1)
    public int threeSumClosest(int[] nums, int target) {

        if (nums.length == 0 || nums == null) {
            return -1;
        }

        Arrays.sort(nums); // 記得排序 Time: O(NlogN)

        int closest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {

            // prunning
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1, right = nums.length - 1;
            int currentSum = 0;
            while (left < right) { // 不滿足才會出去，因此無法在while外面紀錄到所有和

                currentSum = nums[i] + nums[left] + nums[right];

                if (Math.abs(currentSum - target) < Math.abs(closest - target)) {
                    closest = currentSum;
                }

                if (currentSum == target) {
                    return target;
                } else if (currentSum < target) {
                    left++;
                    // prunning
                    while (left < nums.length && nums[left] == nums[left - 1]) {
                        left++;
                    }
                } else if (currentSum > target) {
                    right--;
                    while (right >= 0 && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }

        return closest;
    }
}