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

    // DC
    // TC: O(N)
    // SC: O(N)
    public int goodNodes(TreeNode root) {

        return DFS(root, root.val);
    }

    private int DFS(TreeNode root, int max) {

        int res = 0;

        if (root == null) {
            return 0;
        }

        if (root.val >= max) {
            res += 1;
            max = root.val;
        }

        res += DFS(root.left, max);
        res += DFS(root.right, max);

        return res;
    }

}


class Solution2 {

    // BFS
    // TC: O(N)
    // SC: O(N)
    public int goodNodes(TreeNode root) {

        Queue<Pair> queue = new ArrayDeque<>();
        int count = 0;
        queue.offer(new Pair(root, root.val));

        while (!queue.isEmpty()){
            Pair curr = queue.poll();
            if (curr.node.val >= curr.max){
                count++;
            }

            if (curr.node.left != null){
                queue.offer(new Pair(curr.node.left, Math.max(curr.max, curr.node.left.val)));
            }

            if (curr.node.right != null){
                queue.offer(new Pair(curr.node.right, Math.max(curr.max, curr.node.right.val)));
            }
        }

        return count;
    }
}

class Pair {
    TreeNode node;
    int max;
    Pair (TreeNode node, int max){
        this.node = node;
        this.max = max;
    }
}

