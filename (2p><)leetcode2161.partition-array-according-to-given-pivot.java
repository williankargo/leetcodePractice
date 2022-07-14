
// Time O(N)
// Space O(N)
// 相向雙指針
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {

        if (nums == null) {
            return null;
        }

        int[] ans = new int[nums.length];

        int left = 0;
        int right = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] < pivot) {
                ans[left] = nums[i];
                left++;
            }

            int j = nums.length - 1 - i; // (nums.length - 1) => last index, 如果i往右前進一個，j也往左前進一個，小於pivot不打緊，因為i最後還是會遍歷到
            if (nums[j] > pivot) {
                ans[right] = nums[j];
                right--;
            }
        }

        // 剩下沒有填的就是pivot
        while (left <= right) {
            ans[left] = pivot;
            left++;
        }

        return ans;
    }
}