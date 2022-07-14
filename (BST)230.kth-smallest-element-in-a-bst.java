import java.util.Stack;

/*
 * @lc app=leetcode id=230 lang=java
 *
 * [230] Kth Smallest Element in a BST
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

// Time: O(h+k), h是樹高，k是題目給的
class Solution {

    // BST:
    // 1. 不存在任何key/value的node
    // 2. 任意node的左右子樹都符合左小右大相等

    // 非遞歸實現
    // in-order會得到aasc結果
    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();

        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        for (int i = 0; i < k - 1; i++) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }

        return stack.peek().val;
    }
}
// @lc code=end
