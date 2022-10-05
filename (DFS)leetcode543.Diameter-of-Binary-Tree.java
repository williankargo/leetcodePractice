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

// similar to Binary Tree Maximum Path Sum
class Solution {

    // Time: O(N)
    // Space: O(logN)
    public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[] { 0 };
        helper(root, diameter);
        return diameter[0];
    }

    // 返回最長的一邊
    private int helper(TreeNode root, int[] diameter) {

        if (root == null) {
            return 0;
        }

        int left = helper(root.left, diameter);
        int right = helper(root.right, diameter);

        diameter[0] = Math.max(diameter[0], left + right);

        return Math.max(left, right) + 1;
    }
}