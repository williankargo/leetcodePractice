import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=658 lang=java
 *
 * [658] Find K Closest Elements
 */

// @lc code=start
class Solution {
    // Time O(logn + k)
    // Space O(k)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        int left = findSmallerThanTargetButBiggest(arr, x);
        int right = left + 1;

        int l = right; // 預防 l 都不會被用到
        int r = left; // 預防 r 都不會被用到

        for (int i = 0; i < k; i++) {
            if (isLeftCloser(arr, left, right, x)) {
                l = left;
                left--;
            } else {
                r = right;
                right++;
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        while (l <= r) {
            // List add value
            ans.add(arr[l]);
            l++;
        }

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

        return Math.abs(target - arr[left]) <= Math.abs(arr[right] - target);
    }
}
// @lc code=end
