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

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // https://www.jiuzhang.com/problem/closest-binary-search-tree-value-ii/
        // Time O(k + h), h是樹的高度
        // Space O(k + h)

        // step 1: 先搜集以tree來看，接近target的幾個點
        Stack<TreeNode> smallerStack = new Stack<>();
        Stack<TreeNode> biggerStack = new Stack<>();
        TreeNode node = root;

        while (node != null) {
            if (node.val > target) {
                biggerStack.push(node);
                node = node.left;
            } else {
                smallerStack.push(node);
                node = node.right;
            }
        }

        // step 2: 只有最接近target的那兩個點才保證大於和小於target且和target間不會再有其他值
        // 所以要取出來處理
        List<Integer> result = new ArrayList<>();
        while (result.size() != k) {

            // 如果stack是empty再peek()會跳出exception
            Double smallerToTarget = smallerStack.isEmpty() ? Double.MAX_VALUE
                    : Math.abs(smallerStack.peek().val - target); // Math.abs(num1-num2)
            Double biggerToTarget = biggerStack.isEmpty() ? Double.MAX_VALUE
                    : Math.abs(biggerStack.peek().val - target);

            if (smallerToTarget < biggerToTarget) {
                result.add(smallerStack.peek().val);
                moveBackward(smallerStack);
            } else {
                result.add(biggerStack.peek().val);
                moveForward(biggerStack);
            }
        }

        return result;
    }

    // 正向in-order，可以 小 > 大
    private void moveForward(Stack<TreeNode> stack) {
        TreeNode node = stack.pop();
        node = node.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    // 逆向in-order，可以 大 > 小
    private void moveBackward(Stack<TreeNode> stack) {
        TreeNode node = stack.pop();
        node = node.left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
    }
}