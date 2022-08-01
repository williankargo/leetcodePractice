// j 會再回來的滑動窗口
class Solution {

    // Time: O(N)
    // Space: O(1)
    public int numberOfArithmeticSlices(int[] nums) {

        if (nums.length == 0 || nums == null) {
            return 0;
        }

        // j 會再回來的滑動窗口
        int length = nums.length;
        int sum = 0;
        for (int i = 0; i < length - 2; i++) {

            int diff = nums[i + 1] - nums[i];
            int j = i + 1; // j為答案的後一個
            while (j < length && nums[j] - nums[j - 1] == diff) {
                j++;
            }

            if (j < length && nums[j] - nums[j - 1] != diff) {
                if (j - i == 3) {
                    sum++;
                } else if (j - i > 3) {
                    sum += (j - i - 2);
                }
            } else { // j >= length
                if (j - i == 3) {
                    sum++;
                } else if (j - i > 3) {
                    sum += (j - i - 2);
                }
                continue;
            }

            // i move forward
        }

        return sum;
    }
}