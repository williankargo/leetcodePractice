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

    // Time: O(2^(N-1))
    // Space: O(N)
    public TreeNode insertNode(TreeNode root, TreeNode node) {

        if (root == null) {
            return node;
        }

        TreeNode curr = root;

        while (curr != null) {

            if (node.val < curr.val) {
                if (curr.left == null) {
                    curr.left = node;
                    break;
                } else {
                    curr = curr.left;
                    // 畫圖就可以知道， curr = curr.left不會改變本來樹的結構，只是curr指到樹不同的地方了
                }
            } else {
                if (curr.right == null) {
                    curr.right = node;
                    break;
                } else {
                    curr = curr.right;
                }
            }
        }

        return root;
    }
}