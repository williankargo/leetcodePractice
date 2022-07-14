import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=236 lang=java
 *
 * [236] Lowest Common Ancestor of a Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // 0. out
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        // 1. divide
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        // 2. decide
        if (leftNode != null && rightNode != null) {
            return root;
        }

        if (leftNode != null) {
            return leftNode;
        }

        if (rightNode != null) {
            return rightNode;
        }

        return null;
    }
}
// @lc code=end
