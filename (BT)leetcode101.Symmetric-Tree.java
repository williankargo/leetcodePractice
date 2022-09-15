import java.util.LinkedList;
import java.util.Queue;

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

    // recursion version
    // Time: O(N)
    // Space: O(N) -> recursion depth
    public boolean isSymmetric(TreeNode root) {

        return helper(root, root);
    }

    private boolean helper(TreeNode root1, TreeNode root2) {

        // 0.
        if (root1 == null && root2 == null) {
            return true;
        }

        // 1.
        if (root1 == null || root2 == null) {
            return false;
        }

        // 2.
        return (root1.val == root2.val)
                && helper(root1.left, root2.right)
                && helper(root1.right, root2.left);

    }
}

// iterate version
// Time: O(N)
// Space: O(N)
class Solution1 {
    public boolean isSymmetric(TreeNode root) {

        // ArrayDeque不能存null
        // LinkedList可以存null
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);

        while (!queue.isEmpty()) {

            TreeNode leftNode = queue.poll();
            TreeNode rightNode = queue.poll();

            if (leftNode == null && rightNode == null) {
                continue;
            }
            if (leftNode == null || rightNode == null) {
                return false;
            }
            if (leftNode.val != rightNode.val) {
                return false;
            }
            queue.offer(leftNode.left);
            queue.offer(rightNode.right);
            queue.offer(leftNode.right);
            queue.offer(rightNode.left);

            // queue滿時
            // add()：拋出異常
            // offer()：return false
        }

        return true;
    }
}