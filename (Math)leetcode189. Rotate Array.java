// solution: https://www.youtube.com/watch?v=BHr381Guz3Y&ab_channel=NeetCode
// TC: O(n)
// SC: O(1)
class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length; // in case k is longer than nums.length
        reverse(0, nums.length - 1, nums);
        reverse(0, k - 1, nums);
        reverse(k, nums.length - 1, nums);
    }

    private void reverse(int start, int end, int[] nums) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}