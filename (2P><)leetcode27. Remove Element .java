// TC: O(N)
// SC: O(1)
class Solution {
    public int removeElement(int[] nums, int val) {

        if (nums.length == 0 || nums == null) {
            return 0;
        }

        int left = 0; // find val
        int right = nums.length - 1; // find non-val

        while (left < right) {
            while (left < right && nums[left] != val) {
                left++;
            }
            while (right > left && nums[right] == val) {
                right--;
            }
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
        }

        return nums[left] == val ? left : left + 1;
    }
}