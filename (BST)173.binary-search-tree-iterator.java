import java.util.Stack;

/*
 * @lc app=leetcode id=173 lang=java
 *
 * [173] Binary Search Tree Iterator
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

// Time O(N)
// https://www.jiuzhang.com/problem/binary-search-tree-iterator/
class BSTIterator {

    private Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public int next() {

        // TreeNode curt = stack.peek(); // Stack -> peek()
        TreeNode node = stack.pop();

        // 如果遇到右node != null，把node.right的左邊都拉進來
        if (node.right != null) {
            TreeNode curt = node.right;
            stack.push(curt);
            while (curt.left != null) {
                stack.push(curt.left);
                curt = curt.left;
            }
        }

        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
// @lc code=end
