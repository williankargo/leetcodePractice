
/*
 * @lc app=leetcode id=701 lang=java
 *
 * [701] Insert into a Binary Search Tree
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

    // return root node
    public TreeNode insertIntoBST(TreeNode root, int val) {

        // 0. out
        if (root == null) {
            return new TreeNode(val);
        }

        // 1. divide
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }
}
// @lc code=end
