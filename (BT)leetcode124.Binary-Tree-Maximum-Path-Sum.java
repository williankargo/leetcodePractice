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

    // Time: O(N)
    // Space: O(logN)
    // https://www.youtube.com/watch?v=Hr5cWUld4vU

    // Binary tree node常用divide and conquer!
    public int maxPathSum(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int[] currMax = new int[] { Integer.MIN_VALUE };
        dfs(root, currMax);

        return currMax[0]; // 用一個空間來存目前的最大值
    }

    // 0.
    // 傳入root得到當前包含root的unsplit最大值
    private int dfs(TreeNode root, int[] currMax) {

        // 1.
        if (root == null) {
            return 0;
        }

        // 2.
        int leftNodeVal = Math.max(0, dfs(root.left, currMax)); // 0代表不選左子，因為可能會是負的
        int rightNodeVal = Math.max(0, dfs(root.right, currMax));

        // spilt並和目前的結果和目前的max比大小
        currMax[0] = Math.max(root.val + leftNodeVal + rightNodeVal, currMax[0]);

        // no spilt
        return root.val + Math.max(leftNodeVal, rightNodeVal);
    }
}
