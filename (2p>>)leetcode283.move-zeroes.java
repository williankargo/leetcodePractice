class Solution {

    // TC: O(N)
    // SC: O(1)
    // 畫圖理解
    public void moveZeroes(int[] nums) {

        int l = 0; // stay to replace with non-zero
        int r = 0; // explore the front

        while (r < nums.length){
 
            if (nums[r] == 0){
                // r移動，l待命
                r++;
            } else {
                // 更新l的值
                nums[l] = nums[r];
                // r, l 一起移動
                r++;
                l++;
            }
        }

        // 填補0
        while (l < nums.length){
            nums[l] = 0;
            l++;
        }
    }
}