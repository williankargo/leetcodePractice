import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {

    // backtracking + DC + DFS + PrefixSum
    // TC: O(N)
    // SC: O(N)

    int count = 0;
    int T = 0;
    Map<Long, Integer> map = new HashMap<>(); // sum : count

    public int pathSum(TreeNode root, int targetSum) {
        T = targetSum;
        dfs(root, 0L);
        return count;
    }

    private void dfs(TreeNode node, long currentSum) {

        // 0.
        if (node == null) {
            return;
        }

        // 1.
        currentSum += node.val;
        if (currentSum == T) {
            count++;
        }
        count += map.getOrDefault((currentSum - T), 0); // important ! prefixSum

        // 2.
        map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        dfs(node.left, currentSum);
        dfs(node.right, currentSum);
        map.put(currentSum, map.get(currentSum) - 1); // rollback
    }
}