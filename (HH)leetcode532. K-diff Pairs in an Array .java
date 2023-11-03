
// solution: https://www.youtube.com/watch?v=YvydVpWBsqw&ab_channel=%E8%B4%BE%E8%80%83%E5%8D%9A
// TC: O(N)
// SC: O(N)

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int findPairs(int[] nums, int k) {

        if (k == 0) {

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }

            int count = 0;
            for (Integer key : map.keySet()) {
                if (map.get(key) >= 2) {
                    count++;
                }
            }

            return count;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int count = 0;
        for (Integer num : set) {
            if (set.contains(num - k)) {
                count++;
            }
            if (set.contains(num + k)) {
                count++;
            }
        }

        return count / 2;
    }
}