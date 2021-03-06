class Solution {

    // Time: O(N)
    // Space: O(1)
    public int removeDuplicates(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 同向雙指針
        int j = 0;
        int i = 0;
        for (i = 0; i < nums.length; i++) {

            while (j < nums.length && nums[i] == nums[j]) {
                j++;
            }

            if (j >= nums.length) {
                break;
            }

            nums[i + 1] = nums[j];
        }

        return (i + 1);
    }
}