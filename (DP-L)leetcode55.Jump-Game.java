// TC: O(N)
// SC: O(N)
class Solution1 {
    public boolean canJump(int[] nums) {

        // 0.
        // how many fuel left to move forward
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        // 1.
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] == 0) {
                return false;
            }
            dp[i] = Math.max(nums[i], dp[i - 1] - 1);
        }

        // 2.
        return true;
    }
}

// Optimize Space!
// TC: O(N)
// SC: O(1)
class Solution2 {
    public boolean canJump(int[] nums) {
        
        // 0.
        // how many fuel left to move forward
        // int[] dp = new int[nums.length];
        // dp[0] = nums[0];
        int prev = nums[0];

        // 1.
        for (int i = 1; i < nums.length; i++){
            if (prev == 0){
                return false;
            }
            prev = Math.max(nums[i], prev - 1);
        }

        // 2.
        return true;
    }
}