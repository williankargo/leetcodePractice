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

    // Time: O(N)
    // Space: O(N)
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {

        List<Integer> result = new ArrayList<>();
        travel(k1, k2, root, result);
        return result;
    }

    private void travel(int k1, int k2, TreeNode node, List<Integer> list) {

        if (node == null) {
            return;
        }

        // in-order traversal , 1&3行可以再加入if進行剪枝(畫圖想就明白)
        if (!(k1 > node.val)) {
            travel(k1, k2, node.left, list);
        }
        if (node.val >= k1 && node.val <= k2) {
            list.add(node.val);
            // 每次時間 1，操作遞歸 N 次（節點總數），得到時間複雜度 O(N)
            // 每次空間 1，操作遞歸 N 次（節點總數），得到空間複雜度 O(N)
        }
        if (!(k2 < node.val)) {
            travel(k1, k2, node.right, list);
        }
    }
}