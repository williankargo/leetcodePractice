class Solution {
    // method 1: https://youtu.be/xZ5FBqk-cFw?t=184
    // TC: O(n), SC: O(n)
    public boolean increasingTriplet(int[] nums) {

        if (nums.length < 3) {
            return false;
        }

        int n = nums.length;
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > leftMin[i] && nums[i] < rightMax[i]) {
                return true;
            }
        }

        return false;
    }
}