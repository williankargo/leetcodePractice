// https://www.jiuzhang.com/problem/window-sum/

class Solution {

    public int[] winSum(int[] nums, int k) {

        if (nums.length < k || k == 0 || nums.length == 0) {
            return new int[] {};
        }

        int j = 0, sum = 0;
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {

            while (j < nums.length && (j - i) < k) {
                sum += nums[j];
                j++;
            }

            if (j - i == k) {
                result[i] = sum;
            }

            sum -= nums[i];
        }

        return result;
    }

}