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

    // Time: O(h)
    // Space: O(h + (k2-k1))
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = getStack(root, k1);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node.val >= k1 && node.val <= k2) {
                result.add(node.val);
                moveUpper(stack);
            } else {
                stack.pop();
            }
        }

        return result;
    }

    // 產生 >= k1 的stack
    private Stack<TreeNode> getStack(TreeNode root, int k1) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            if (root.val >= k1) {
                stack.push(root);
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return stack;
    }

    private void moveUpper(Stack<TreeNode> stack) {

        TreeNode node = stack.pop().right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

}