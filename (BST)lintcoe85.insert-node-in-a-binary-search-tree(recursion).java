/**
 * Definition of TreeNode:
 * public class TreeNode {
 * public int val;
 * public TreeNode left, right;
 * public TreeNode(int val) {
 * this.val = val;
 * this.left = this.right = null;
 * }
 * }
 */

class Solution {

    // recursion method
    // Time: O(2^(N-1)) 一半的BST ， 深度為N
    // Space: O(2^(N-1))
    public TreeNode insertNode(TreeNode root, TreeNode node) {

        // 注意：如果root本來就是null，無法pass by value!
        return travel(root, node);
    }

    // 返回已經插入完成的tree
    private TreeNode travel(TreeNode root, TreeNode node) {

        if (root == null) {
            // 不可以寫成root = node
            return node;
        }

        if (node.val < root.val) {
            root.left = travel(root.left, node);
        } else {
            root.right = travel(root.right, node);
        }

        return root;
    }

}