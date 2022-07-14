import java.util.ArrayList;
import java.util.List;

class Solution {

    // Recursion - BT
    // permutation
    // Space: O(N!)
    // Time: O(N*N!)
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }

        dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), results);

        return results;
    }

    // 模板背起來
    private void dfs(int[] nums, boolean[] gone, List<Integer> currentList, List<List<Integer>> results) {

        // 出口
        if (nums.length == currentList.size()) { // N! 個循環
            results.add(new ArrayList<Integer>(currentList)); // deep copy
            return;
        }

        // for 拆解可能性個數
        for (int i = 0; i < nums.length; i++) { // N

            if (gone[i]) {
                continue;
            }

            currentList.add(nums[i]);
            gone[i] = true;
            dfs(nums, gone, currentList, results); // 對稱
            gone[i] = false;
            currentList.remove(currentList.size() - 1); // 刪除尾部
        }
    }

}