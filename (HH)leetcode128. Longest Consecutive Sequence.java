// solution: https://www.youtube.com/watch?v=P6RZZMu_maU&ab_channel=NeetCode
// TC: O(N)
// SC: O(N)

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;
        for (int num : set) {
            // to check the nums[i] is head or not
            if (!set.contains(num - 1)) {
                int length = 1;
                while (set.contains(num + 1)) {
                    length++;
                    num++;
                }
                longest = Math.max(longest, length);
            }
        }

        return longest;
    }
}