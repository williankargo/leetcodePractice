import java.util.ArrayList;
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
class Solution1 {

    // sol 1: DFS
    // TC: O(N)
    // SC: O(N)
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> root1_list = new ArrayList<>();
        List<Integer> root2_list = new ArrayList<>();

        dfs(root1, root1_list);
        dfs(root2, root2_list);

        return root1_list.equals(root2_list);
    }

    private void dfs(TreeNode node, List<Integer> list) {

        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            list.add(node.val);
        }

        dfs(node.left, list);
        dfs(node.right, list);
    }
}

// sol 2: BFS ?
// can't use BFS, 因為把last node加入list的順序不會按照從左到右的順序！