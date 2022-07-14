/*
 * @lc app=leetcode id=700 lang=java
 *
 * [700] Search in a Binary Search Tree
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

    // 返回找到的那個node
    public TreeNode searchBST(TreeNode root, int val) {

        // 0. 想最後的值
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }

        // 1. 想做後的大局
        if (val < root.val) {
            root = searchBST(root.left, val);
        } else {
            root = searchBST(root.right, val);
        }

        return root;
    }
}
// @lc code=end
