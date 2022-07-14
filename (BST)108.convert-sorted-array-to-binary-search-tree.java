/*
 * @lc app=leetcode id=108 lang=java
 *
 * [108] Convert Sorted Array to Binary Search Tree
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
    public TreeNode sortedArrayToBST(int[] nums) {

        if (nums == null) {
            return null;
        }

        return find(nums, 0, nums.length - 1);
    }

    // return root node
    private TreeNode find(int[] nums, int start, int end) {

        // 0.
        if (start > end) {
            return null;
        }

        // 1. divide
        TreeNode root = new TreeNode(nums[(start + end) / 2]);
        root.left = find(nums, start, (start + end) / 2 - 1);
        root.right = find(nums, (start + end) / 2 + 1, end);

        // 2. decide

        return root;
    }
}
// @lc code=end
