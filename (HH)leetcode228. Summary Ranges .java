// TC: O(N)
// SC: O(N)

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> summaryRanges(int[] nums) {

        List<String> result = new ArrayList<>();

        if (nums.length == 0 || nums == null) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (i + 1 < nums.length && nums[i] + 1 == nums[i + 1]) {
                i++;
            }
            if (num != nums[i]) {
                result.add(num + "->" + nums[i]);
            } else {
                result.add(nums[i] + "");
            }
        }

        return result;
    }
}