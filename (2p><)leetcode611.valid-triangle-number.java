import java.util.Arrays;

// 相向雙指針
// Time: O(N^2 + NlogN)
// Space: O(1)
class Solution {
    public int triangleNumber(int[] nums) {
        if (nums.length < 3 || nums == null) {
            return 0;
        }

        Arrays.sort(nums);

        int count = 0;

        // 從三邊中的最大邊開始找
        for (int i = 2; i < nums.length; i++) {
            int leftIndex = 0;
            int rightIndex = i - 1;
            count = count + findCount(leftIndex, rightIndex, i, nums);
        }
        return count;
    }

    private int findCount(int leftIndex, int rightIndex, int target, int[] nums) {

        int targetValue = nums[target];
        int count = 0;
        while (leftIndex < rightIndex) {
            int sum = nums[leftIndex] + nums[rightIndex];
            if (sum > targetValue) {
                count = count + (rightIndex - leftIndex); // 表示以 nums[leftIndex i], nums[rightIndex] 為兩條邊的三角形有
                                                          // (rightIndex - leftIndex) 個 ，本題不用去重
                rightIndex--;
            } else {
                leftIndex++;
            }
        }
        return count;
    }
}