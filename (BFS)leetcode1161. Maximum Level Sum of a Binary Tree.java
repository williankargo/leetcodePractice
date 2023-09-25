import java.util.ArrayDeque;
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

    // TC: O(N)
    // SC: O(N)
    public int maxLevelSum(TreeNode root) {

        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        int maxValue = -Integer.MAX_VALUE;
        int maxLayer = 0;
        int curLayer = 0;
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int curValue = 0;
            curLayer++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                curValue += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            if (curValue > maxValue) {
                maxValue = curValue;
                maxLayer = curLayer;
            }
        }

        return maxLayer;
    }
}