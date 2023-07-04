import java.util.Arrays;

class Solution {

    // TC: O(N)
    // SC: O(1)
    public int maxOperations(int[] nums, int k) {

        // sort the array first
        Arrays.sort(nums);

        int l = 0, r = nums.length - 1;
        int count = 0;
        while (l < r) {
            if (nums[l] + nums[r] < k) {
                l++;
            } else if (nums[l] + nums[r] > k) {
                r--;
            } else {
                count++;
                l++;
                r--;
            }
        }

        return count;
    }
}