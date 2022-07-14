import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=658 lang=java
 *
 * [658] Find K Closest Elements
 */

// @lc code=start
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        int left = findSmallerThanTargetButBiggest(arr, x);
        int right = left + 1;

        // fixed size List
        List<Integer> ans = Arrays.asList(new Integer[k]);
        for (int i = 0; i < k; i++) {
            if (isLeftCloser(arr, left, right, x)) {
                // set value in List ,(not ans.get(i) = arr[left])
                ans.set(i, arr[left]);
                left--;
            } else {
                ans.set(i, arr[right]);
                right++;
            }
        }

        // Arrays.sort(ans); ==> can only sort int[] ans
        // Collections.sort(ans); // ==> can sort List and int[]

        return ans;
    }

    // 二分法：log(N)
    private int findSmallerThanTargetButBiggest(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] < target) {
                start = mid;
            }
            // >=
            // 如果找到 arr[mid] == target ，那給end接住mid
            // 不用急著處理等於的狀況
            else {
                end = mid;
            }
        }

        // 找小於的最大數
        // end經過上面的調教，最大是等於，再沒有是小於，反正會 >= start，所以要先找
        if (arr[end] < target) {
            return end;
        }
        if (arr[start] < target) {
            return start;
        }

        // 說明所有數都 >= target
        return -1;
    }

    private boolean isLeftCloser(int[] arr, int left, int right, int target) {
        if (left < 0) {
            return false;
        }
        if (right > arr.length - 1) {
            return true;
        }

        // todo
        return (arr[left] - target) >= (target - arr[right]);
    }
}
// @lc code=end
