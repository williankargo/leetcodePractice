import java.util.Stack;

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

// Traversal version
class Solution0 {

    // Traversal version
    // Time: 最好O(logN) -> 最壞O(N) N是點的個數
    // Space: O(N)
    public boolean isValidBST(TreeNode root) {

        if (root == null) {
            return true;
        }

        // 0. 建立一個stack
        Stack<TreeNode> stack = new Stack<>();

        // 1. 一路往左通
        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        // 2. pop出來，往右再往左
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (prev != null && prev.val >= node.val) {
                return false;
            }
            prev = node;
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }

        return true;
    }
}

// Divide and Conquer Version
class Solution1 {

    // Time: 最好O(logN) -> 最壞O(N) N是點的個數
    // Space: O(h) h是樹的高度，遞歸深度為h，即為空間複雜度
    public boolean isValidBST(TreeNode root) {

        if (root == null) {
            return true;
        }

        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE); // 因為值的範圍可能有Integer的最大最小值，所以要用一個不可能達到的值當Max, min
    }

    // 0. def: 判斷以node為root的tree是不是BST
    private boolean helper(TreeNode node, long min, long max) {

        // 0. exit
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // 2. decide left, right
        boolean leftTree = helper(node.left, min, node.val);
        boolean rightTree = helper(node.right, node.val, max);

        // 3. return (left + right)
        return leftTree & rightTree;
    }
}