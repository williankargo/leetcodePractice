class Solution1 {

    // https://www.lintcode.com/problem/578/
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {

        Result result = helper(root, A, B);
        return result.a_exists & result.b_exists ? result.node : null;
    }

    private Result helper(TreeNode root, TreeNode A, TreeNode B) {

        // 0. out
        if (root == null) {
            return new Result(false, false, null);
        }

        // 1. divide
        Result left_result = helper(root.left, A, B);
        Result right_result = helper(root.right, A, B);

        boolean a_exists = left_result.a_exists || right_result.a_exists || root == A;
        boolean b_exists = left_result.b_exists || right_result.b_exists || root == B;

        // 2. decide
        if (root == A || root == B) {
            return new Result(a_exists, b_exists, root);
        }

        if (left_result.node != null && right_result.node != null) {
            return new Result(a_exists, b_exists, root);
        }

        if (left_result.node != null) {
            return new Result(a_exists, b_exists, left_result.node);
        }

        if (right_result.node != null) {
            return new Result(a_exists, b_exists, right_result.node);
        }

        return new Result(a_exists, b_exists, null);
    }

}

class Result {
    boolean a_exists;
    boolean b_exists;
    TreeNode node;

    Result(boolean a_exists, boolean b_exists, TreeNode node) {
        this.a_exists = a_exists;
        this.b_exists = b_exists;
        this.node = node;
    }
}

class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

// new version
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution2 {

    // Time: O(# of recursion)
    // Space: O(# of recursion)
    // divide and conquer
    // find lowest common ancestor
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // exit
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        // left and right
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        if (leftNode != null && rightNode != null) {
            return root;
        }
        if (leftNode != null) {
            return leftNode;
        }
        if (rightNode != null) {
            return rightNode;
        }

        return null;
    }
}