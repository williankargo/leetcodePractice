import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://www.youtube.com/watch?v=dw-tlJYjzco
class Solution {

    // 區間：想到線段樹
    class SegmentTreeNode {
        int start, end;
        int sum;
        SegmentTreeNode leftNode;
        SegmentTreeNode rightNode;

        SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.sum = 0;
            this.leftNode = null;
            this.rightNode = null;
        }
    }

    // build O(N)
    // 建沒有sum的tree
    private SegmentTreeNode buildTree(int start, int end) {

        if (start > end) {
            return null;
        }

        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start == end) {
            return root;
        }

        int mid = start + (end - start) / 2;
        root.leftNode = buildTree(start, mid);
        root.rightNode = buildTree(mid + 1, end);

        return root;
    }

    // update O(logN)
    // 設定走到就會加一
    private void update(SegmentTreeNode root, int index) {

        // exit
        if (root == null) {
            return;
        }

        if (root.start == index && root.end == index) {
            root.sum += 1;
            return;
        }

        int mid = root.start + (root.end - root.start) / 2;
        if (index >= root.start && index <= mid) {
            update(root.leftNode, index);
        } else if (index > mid && index <= root.end) {
            update(root.rightNode, index);
        }

        root.sum = root.rightNode.sum + root.leftNode.sum;
    }

    // sumRange O(logN)
    private int sumRange(SegmentTreeNode root, int start, int end) {

        // exit
        if (root == null || start > end) {
            return 0;
        }
        if (root.start == start && root.end == end) {
            return root.sum;
        }

        int mid = root.start + (root.end - root.start) / 2;
        if (start >= (mid + 1)) {
            return sumRange(root.rightNode, start, end);
        } else if (end <= mid) {
            return sumRange(root.leftNode, start, end);
        } else {
            return sumRange(root.leftNode, start, mid) + sumRange(root.rightNode, mid + 1, end);
        }
    }

    public List<Integer> countSmaller(int[] nums) {

        List<Integer> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        // 找出min和Max以建樹
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        SegmentTreeNode root = buildTree(min, max);
        for (int i = nums.length - 1; i >= 0; i--) {
            // 從後往前遍歷，遍歷到就sum++
            update(root, nums[i]);
            result.add(sumRange(root, min, nums[i] - 1));
        }

        Collections.reverse(result); // 倒轉List
        return result;
    }
}