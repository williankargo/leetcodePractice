class Solution {

    // Time: O(N)
    // Space: O(N)
    // https://github.com/SharingSource/LogicStack-LeetCode/blob/main/LeetCode/231-240/238.%20%E9%99%A4%E8%87%AA%E8%BA%AB%E4%BB%A5%E5%A4%96%E6%95%B0%E7%BB%84%E7%9A%84%E4%B9%98%E7%A7%AF%EF%BC%88%E4%B8%AD%E7%AD%89%EF%BC%89.md
    public int[] productExceptSelf(int[] nums) {

        // 1 2 3 4 5
        // 前i個和 (0) 1 2 3 4 5
        int[] front = new int[nums.length + 1]; // 前0個 -> 前nums.length個
        // 從第i個後圈和 1 2 3 4 5 (6)
        int[] back = new int[nums.length + 2]; // nums.length + 1 -> 1 (但其實只會用到2)

        front[0] = back[nums.length + 1] = 1;
        for (int i = 1; i <= nums.length; i++) {
            front[i] = front[i - 1] * nums[i - 1];
        }
        for (int i = nums.length; i >= 1; i--) {
            back[i] = back[i + 1] * nums[i - 1];
        }

        int[] ans = new int[nums.length];
        for (int i = 1; i <= nums.length; i++) {
            ans[i - 1] = front[i - 1] * back[i + 1];
        }

        return ans;
    }
}