import java.util.HashMap;

/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 */

// @lc code=start
class Solution {

    // <sol1>HashMap: time O(N) /space O(N)
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // 想法：遍歷nums[]一次，並用HashMap紀錄下 nums[j] = target - nums[i]，再用nums[i]的數值查找map一次，看有無對應

        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null) {
                int[] answer = { map.get(nums[i]), i };
                return answer;
            }
            map.put(target - nums[i], i);
        }

        return null;
    }
}
// @lc code=end

