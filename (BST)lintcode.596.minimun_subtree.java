class Solution {

    public TreeNode findSubtree(TreeNode root) {
        ResultType result = helper(root);
        return result.minNode;
    }

    private ResultType helper(TreeNode root) {
        // 3. 出口
        if (root == null) {
            return new ResultType(0, Integer.MAX_VALUE, null);
        }

        // 1.拆解
        ResultType leftTree = helper(root.left);
        ResultType rightTree = helper(root.right);
        int sum = root.val + leftTree.sum + rightTree.sum;
        int minSum = sum;
        TreeNode minNode = root;

        // 2. 比較
        if (leftTree.minSum < minSum) {
            minSum = leftTree.minSum;
            minNode = leftTree.minNode;
        }

        if (rightTree.minSum < minSum) {
            minSum = rightTree.minSum;
            minNode = rightTree.minNode;
        }

        return new ResultType(sum, minSum, minNode);
    }

    class ResultType {
        int sum;
        int minSum;
        TreeNode minNode;

        ResultType(int sum, int minSum, TreeNode minNode) {
            this.minSum = minSum;
            this.sum = sum;
            this.minNode = minNode;
        }
    }

    // 題目條件
    class TreeNode {

        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}