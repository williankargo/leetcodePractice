class Solution {

    // Time O(h)
    // Space O(1)
    public int closestValue(TreeNode root, double target) {

        TreeNode upperBound = root;
        TreeNode lowerBound = root;

        while (root != null) {
            if (root.val < target) {
                upperBound = root.right;
                lowerBound = root;
            } else if (root.val > target) {
                upperBound = root;
                lowerBound = root.left;
            } else {
                return root.val;
            }
        }

        Boolean upper = Math.abs(upperBound.val - target) <= Math.abs(target - lowerBound.val);

        return upper ? upperBound.val : lowerBound.val;
    }

}