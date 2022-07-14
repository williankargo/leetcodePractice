class Solution {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {

        List<Integer> list = new ArrayList<>();
        traverse(root, list);

        int i = 0;
        int n = list.size();

        while (i < n) {
            if (list.get(i) >= target) {
                break;
            }
            i++;
        }

        if (i == n) {
            return list.subList(n - k, n); // ArrayList: sublist(start, 不會到這裡);
        }

        List<Integer> result = new ArrayList<>();
        int leftIndex = i - 1;
        int rightIndex = i;
        while (result.size() != k) {

            if (leftIndex >= 0
                    && (rightIndex >= n || (target - list.get(leftIndex)) < (list.get(rightIndex) - target))) {
                result.add(list.get(leftIndex));
                leftIndex--;
            } else {
                result.add(list.get(rightIndex));
                rightIndex++;
            }
        }

        return result;
    }

    // in-order traverse，背起來
    private void traverse(TreeNode root, List<Integer> list) {

        if (root == null) {
            return;
        }

        traverse(root.left, list);
        list.add(root.val);
        traverse(root.right, list);
    }
}