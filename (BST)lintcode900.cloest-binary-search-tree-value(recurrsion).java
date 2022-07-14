class Solution {

    // Time O(h)
    // Space O(1)
    public int closestValue(TreeNode root, double target) {

        if (root == null) {
            return 0;
        }

        TreeNode lowBound = findLowBound(root, target);
        TreeNode highBound = findHighBound(root, target);

        if (lowBound != null && highBound == null) {
            return lowBound.val;
        }

        if (lowBound == null && highBound != null) {
            return highBound.val;
        }

        if (highBound.val - target < target - lowBound.val) {
            return highBound.val;
        }
        return lowBound.val;
    }

    // 找到小於等於target的最大值
    private TreeNode findLowBound(TreeNode root, double target) {

        // 微觀
        if (root == null) {
            return null;
        }

        // 宏觀
        if (root.val > target) {
            return findLowBound(root.left, target);
        }

        // 此時root.val一定 <= target

        TreeNode node = findLowBound(root.right, target);

        return node != null ? node : root;
    }

    // 找到大於target的最小值
    private TreeNode findHighBound(TreeNode root, double target) {

        // 微觀
        if (root == null) {
            return null;
        }

        // 宏觀
        if (root.val < target) {
            return findHighBound(root.right, target);
        }

        TreeNode node = findHighBound(root.left, target);

        return node != null ? node : root;
    }
}