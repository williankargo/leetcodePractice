import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 相向雙指針
// Time O(N^2 + NlogN)
// Space O(K), K為解的個數
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 3) {
            return results;
        }

        // java sort
        Arrays.sort(nums); // Nlog(N)

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) { // 去重
                continue;
            }

            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            twoSum(results, nums, left, right, target);
        }
        return results;
    }

    public void twoSum(List<List<Integer>> results, int[] nums, int left, int right, int target) {
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (target == sum) {
                generateTriplet(results, nums, left, right, target);

                left++;
                right--;

                // 去重 left
                // index有調整的地方就要記得設下條件檢查
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }

                // 去重 right
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            } else if (target < sum) {
                right--;
            } else {
                left++;
            }
        }
    }

    private void generateTriplet(List<List<Integer>> results, int[] nums, int left, int right, int target) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(-target);
        result.add(nums[left]);
        result.add(nums[right]);
        results.add(result);
    }
}
// @lc code=end
