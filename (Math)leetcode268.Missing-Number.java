class Solution {

    // Time: O(N)
    // Space: O(1)
    // 高斯和公式
    public int missingNumber(int[] nums) {

        int length = nums.length; // [0, length]
        int sum = length * (length + 1) * 1 / 2;

        for (int num : nums) {
            sum -= num;
        }

        return (int) sum;
    }
}