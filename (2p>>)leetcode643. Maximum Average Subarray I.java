class Solution {
    // TC: O(N)
    // SC: O(1)
    public double findMaxAverage(int[] nums, int k) {

        if (nums.length == 0) {
            return 0;
        }

        // 0. def
        int j = 0; // window end + 1

        // 1. ds
        double max = -Double.MAX_VALUE;
        double sum = 0;

        // 2. for
        for (int i = 0; i < nums.length; i++) {

            // 3. while
            while (j < nums.length && (j - i) < k) {
                sum += nums[j];
                j++;
            }

            // 4. discuss
            System.out.println(sum / k);
            max = Math.max(max, (sum / k));

            // 5. slide
            sum -= nums[i];
            if (j >= nums.length) {
                break;
            }
        }

        return max;
    }
}