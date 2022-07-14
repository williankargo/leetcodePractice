/*
 * @lc app=leetcode id=283 lang=java
 *
 * [283] Move Zeroes
 */

// @lc code=start

// Time: N
// Space: 1
// 同向雙指針
class Solution {
    public void moveZeroes(int[] nums) {
        
        int fillPointer = 0;
        int movePointer = 0;

        while (movePointer < nums.length) {


            if (nums[movePointer] == 0) {
                // fillPointer要留在原地待命
            } else {
                // 如果兩指針所指的東西不一樣，代表fillPointer在待命要swap
                if (nums[movePointer] != nums[fillPointer]) {
                    //int temp = nums[fillPointer];
                    nums[fillPointer] = nums[movePointer];
                    //nums[movePointer] = temp;
                }
                fillPointer++;
            }
            movePointer++;
        }

        // faster way
        while(fillPointer < nums.length){
            if (nums[fillPointer] != 0) {
                nums[fillPointer] = 0;
            }
            fillPointer++;
        }
        
    }
}
// @lc code=end

