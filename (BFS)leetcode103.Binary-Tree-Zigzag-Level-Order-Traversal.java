import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

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

// Time: O(N)
// Space: O(N)
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // odd -> left to right
        // even -> right to left
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        int count = 1;

        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> sonResult = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = deque.pollFirst();
                sonResult.add(curr.val);
                if (curr.left != null) {
                    deque.offerLast(curr.left);
                }
                if (curr.right != null) {
                    deque.offerLast(curr.right);
                }
            }
            if (count % 2 != 0) {
                result.add(sonResult);
            } else {
                Collections.reverse(sonResult);
                result.add(sonResult);
            }
            count++;
        }

        return result;
    }
}