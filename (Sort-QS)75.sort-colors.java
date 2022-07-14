/*
 * @lc app=leetcode id=75 lang=java
 *
 * [75] Sort Colors
 */

// @lc code=start

// time : N
// space : 1 (in-place)
class Solution {

    // quick sort (partition)
    public void sortColors(int[] nums) {
        quickSortPartition(nums, 1);
        quickSortPartition(nums, 2);
    }

    private void quickSortPartition(int[] nums, int k){

        int lastIndex = 0;
        for(int i = 0; i < nums.length; i++){

            if(nums[i] < k){
                swap(nums, i, lastIndex);
                lastIndex++;
            }
        }
    }

    private void swap(int[] nums, int i, int lastIndex){
        int temp = nums[i];
        nums[i] = nums[lastIndex];
        nums[lastIndex] = temp;
    }

}
// @lc code=end

