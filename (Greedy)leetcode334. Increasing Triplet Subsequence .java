// Solution 1
class Solution1 {
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

// Solution 2
class Solution2 {

    // method 2: https://youtu.be/yEFlGWOVH8g?t=117
    // TC: O(N)
    // SC: O(1)
    public boolean increasingTriplet(int[] nums) {

        if (nums.length < 3){
            return false;
        }

        // [i(find smallest), j(find smaller), k(the biggest of three)]
        int i = Integer.MAX_VALUE, j = Integer.MAX_VALUE;
        for (int in = 0; in < nums.length; in++){
            if (nums[in] <= i){ // = => 避免[1, 1, ...]為true
                i = nums[in];
            } else if (nums[in] <= j){ // = => 避免[1, 2, 2]為true
                j = nums[in];
            } else {
                return true;
            }
        }
        return false;
    }
}