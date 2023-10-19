// solution: https://www.youtube.com/watch?v=ycAq8iqh0TI&ab_channel=NeetCodeIO
// TC: O(n)
// SC: O(1)
class Solution {
    public int removeDuplicates(int[] nums) {

        int l = 0;
        int r = 0;
        while (r < nums.length) {
            int count = 1;
            while (r + 1 < nums.length && nums[r] == nums[r + 1]) {
                r++;
                count++;
            }
            for (int i = 0; i < Math.min(count, 2); i++) {
                nums[l] = nums[r];
                l++;
            }
            r++;
        }
        return l;
    }
}