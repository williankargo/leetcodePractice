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

    // TC: O(N)
    // SC: O(N)
    int maxHeight = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root.left, 1, false);
        dfs(root.right, 1, true);
        return maxHeight;
    }

    private void dfs(TreeNode root, int currentHeight, boolean prevFromRight) {

        // 0.
        if (root == null) {
            return;
        }

        // 1.
        dfs(root.left, prevFromRight ? currentHeight + 1 : 1, false);
        dfs(root.right, !prevFromRight ? currentHeight + 1 : 1, true);

        // 2.
        maxHeight = Math.max(maxHeight, currentHeight);
    }
}