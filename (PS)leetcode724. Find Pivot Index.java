// Sol1: 直觀做法，SC: O(N)
class Solution1 {

    // TC: O(N)
    // SC: O(N)
    public int pivotIndex(int[] nums) {

        if (nums.length == 0) {
            return -1;
        }

        int[] leftSum = new int[nums.length];
        int[] rightSum = new int[nums.length];

        leftSum[0] = 0; // now in index 0, the sum in the left is 0
        for (int i = 0; i < nums.length - 1; i++) {
            leftSum[i + 1] = leftSum[i] + nums[i];
        }

        rightSum[nums.length - 1] = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            rightSum[i - 1] = rightSum[i] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            if (leftSum[i] == rightSum[i]) {
                return i;
            }
        }

        return -1;
    }
}

// Sol2: SC: O(1)
class Solution2 {

    // TC: O(N)
    // SC: O(1)
    public int pivotIndex(int[] nums) {

        if (nums.length == 0) {
            return -1;
        }

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == (sum - nums[i])) {
                return i;
            }
            leftSum += nums[i];
            sum -= nums[i];
        }

        return -1;
    }
}