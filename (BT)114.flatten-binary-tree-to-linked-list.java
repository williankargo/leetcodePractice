/*
 * @lc app=leetcode id=114 lang=java
 *
 * [114] Flatten Binary Tree to Linked List
 */

// @lc code=start
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
    public void flatten(TreeNode root) {

        DFS_flatten(root);
    }

    // 定義：找到最尾的那個點
    private TreeNode DFS_flatten(TreeNode root) {

        // 0. out
        if (root == null) {
            return null;
        }

        // 1. divide
        TreeNode leftLast = DFS_flatten(root.left);
        TreeNode rightLast = DFS_flatten(root.right);

        // 2. decide
        // flatten works
        if (leftLast != null) {
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        if (rightLast != null) {
            return rightLast;
        }

        if (leftLast != null) {
            return leftLast;
        }

        return root;
    }
}
// @lc code=end
