import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=18 lang=java
 *
 * [18] 4Sum
 */

// @lc code=start
// Time O(N^3 + NlogN)
// Space O(K)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> results = new ArrayList<>();

        if (nums.length < 4 || nums == null) {
            return results;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                // j != i + 1 => 第二圈非第一次了！
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int fixFirst = nums[i];
                int fixSecond = nums[j];
                int leftIndex = j + 1;
                int rightIndex = nums.length - 1;
                TwoPointerFind(fixFirst, fixSecond, leftIndex, rightIndex, nums, target, results);
            }
        }

        return results;
    }

    private void TwoPointerFind(int fixFirst, int fixSecond, int leftIndex, int rightIndex, int[] nums, int target,
            List<List<Integer>> results) {

        while (leftIndex < rightIndex) {
            int sum = fixFirst + fixSecond + nums[leftIndex] + nums[rightIndex];
            if (sum == target) {
                // java不能這樣一次加完，要一個一個加
                // result.add(fixFirst, fixSecond, nums[leftIndex], nums[rightIndex]);
                List<Integer> result = generateResult(fixFirst, fixSecond, nums[leftIndex], nums[rightIndex]);
                results.add(result);
                leftIndex++;
                rightIndex--;

                while (leftIndex < rightIndex && nums[leftIndex - 1] == nums[leftIndex]) {
                    leftIndex++;
                }

                while (leftIndex < rightIndex && nums[rightIndex + 1] == nums[rightIndex]) {
                    rightIndex--;
                }

            } else if (sum < target) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }
    }

    private List<Integer> generateResult(int fixFirst, int fixSecond, int leftValue, int rightValue) {
        List<Integer> result = new ArrayList<>();
        result.add(fixFirst);
        result.add(fixSecond);
        result.add(leftValue);
        result.add(rightValue);

        return result;
    }
}
// @lc code=end
