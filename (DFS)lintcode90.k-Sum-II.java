import java.util.ArrayList;
import java.util.List;

class Solution {

    // Recursion - BT
    // Time: O(N^k)
    // Space: O(N^k)
    public List<List<Integer>> kSumII(int[] a, int k, int target) {

        List<List<Integer>> subsets = new ArrayList<>();
        if (a == null || a.length == 0) {
            return subsets;
        }

        dfs(0, k, target, new ArrayList<Integer>(), subsets, a);
        return subsets;
    }

    private void dfs(int index, int k, int target, List<Integer> subset, List<List<Integer>> subsets, int[] a) {

        // exit
        if (k == 0 && target == 0) {
            subsets.add(new ArrayList<Integer>(subset)); // 記得要deep copy，不然會被一直改動
            return;
        }

        // prunning
        if (k == 0 || target <= 0) { // 題目說都是正數
            return;
        }

        // decide
        for (int i = index; i < a.length; i++) {

            subset.add(a[i]);
            dfs(i + 1, k - 1, target - a[i], subset, subsets, a);
            subset.remove(subset.size() - 1);
        }
    }
}