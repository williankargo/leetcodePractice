import java.util.HashSet;
import java.util.Set;

class Solution1 {

    // O(N^2) => TLE
    public int subarraysWithKDistinct(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k == 0) {
            return 0;
        }

        int ans = 0;
        // 此題的j固定，i往左邊一直找(但會走重複的路徑，所以此方法會超時)
        for (int j = 0; j < nums.length; j++) {

            int i = j;
            Set<Integer> set = new HashSet<>();
            set.add(nums[i]);

            while (i >= 0) {
                set.add(nums[i]);
                if (set.size() == k) {
                    ans++;
                } else if (set.size() > k) {
                    break;
                }
                i--;
            }
        }

        return ans;
    }
}


// Time: O(N)
// Space: O(N)
class Solution2 {
    public int subarraysWithKDistinct(int[] nums, int k) {

        if (nums.length == 0 || nums == null || k == 0) {
            return -1;
        }

        // 固定左端點i，找到右邊走最遠且滿足k的j1，再找到右邊走最遠滿足(k - 1)的j2，每個i的j2-j1加起來就是答案
        int[] higher = new int[nums.length];
        int[] lower = new int[nums.length];
        int ans = 0;

        find(higher, k, nums);
        find(lower, k - 1, nums);

        for (int i = 0; i < nums.length; i++) {
            ans += (higher[i] - lower[i]);
        }

        return ans;
    }

    private void find(int[] arr, int k, int[] nums) {

        int[] cnt = new int[nums.length + 1]; // 題目：1 <= nums[i] <= nums.length
        int j = 0;
        int genre = 0;
        // i 固定在左邊，j往右走且不會重複走
        for (int i = 0; i < nums.length; i++) {

            while (j < nums.length && ((genre < k) || (genre == k && cnt[nums[j]] != 0))) { // &&後要放j條件，以判斷臨界點

                if (cnt[nums[j]] == 0) {
                    genre += 1;
                }
                cnt[nums[j]]++;
                j++;
            }

            // 因為上面 j 多加了一次，所以此時的 j 是答案後一個

            arr[i] = (j - 1);

            // move forward
            cnt[nums[i]]--;
            if (cnt[nums[i]] == 0) {
                genre--;
            }
        }
    }
}