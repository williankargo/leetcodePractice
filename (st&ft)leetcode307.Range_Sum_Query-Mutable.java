
/** segmentTree version */
// leetcode 307
class NumArray {

    class SegmentTreeNode {
        private int start, end;
        private SegmentTreeNode leftNode, rightNode;
        private int sum;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.leftNode = null;
            this.rightNode = null;
            this.sum = 0;
        }
    }

    SegmentTreeNode root = null;

    public NumArray(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }

    // 建構 segment tree
    // Time: O(N)
    private SegmentTreeNode buildTree(int[] nums, int start, int end) {

        // exit
        if (start > end) {
            return null;
        }

        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start == end) {

            root.sum = nums[start]; // leaf node
        } else {

            int mid = start + (end - start) / 2;
            root.leftNode = buildTree(nums, start, mid);
            root.rightNode = buildTree(nums, mid + 1, end);
            root.sum = root.leftNode.sum + root.rightNode.sum;
        }

        return root;
    }

    // 更新一點 segment tree
    // 每一個節點存儲的值都是左右節點進行對應運算(求max or 求sum)得出的
    // Time: O(logN)
    public void update(int index, int val) {
        updateHelper(root, index, val);
    }

    private void updateHelper(SegmentTreeNode root, int index, int val) {

        // exit
        if (root == null) {
            return;
        }

        // found left node to update
        if (root.start == root.end) {
            root.sum = val;
            return;
        }

        int mid = root.start + (root.end - root.start) / 2;
        if (index >= root.start && index <= mid) {

            updateHelper(root.leftNode, index, val); // 往左邊找
        } else if (index > mid && index <= root.end) {

            updateHelper(root.rightNode, index, val); // 往右邊找
        }

        // 因為leaf node更新了，所以father node也要更新
        root.sum = root.leftNode.sum + root.rightNode.sum;
    }

    // 查詢segment tree
    // 取得樹的區間和
    public int sumRange(int left, int right) {
        return sumRangeHelper(root, left, right);
    }

    private int sumRangeHelper(SegmentTreeNode root, int left, int right) {

        if (left > right || root == null) {
            return 0;
        }

        if (root.start == left && root.end == right) {
            return root.sum;
        }

        int mid = root.start + (root.end - root.start) / 2;
        if (right <= mid) { // 所求區間都在左半邊

            return sumRangeHelper(root.leftNode, left, right);
        } else if (left >= (mid + 1)) { // 所求區間都在右半邊

            return sumRangeHelper(root.rightNode, left, right);
        } else { // 所求區間同時存在於左半邊和右半邊

            return sumRangeHelper(root.leftNode, left, mid) + sumRangeHelper(root.rightNode, mid + 1, right);
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */

/* Binary Indexed Tree / Fenwick Tree version */
// https://www.youtube.com/watch?v=WbafSgetDDk
// https://github.com/SharingSource/LogicStack-LeetCode/blob/main/LeetCode/301-310/307.%20%E5%8C%BA%E5%9F%9F%E5%92%8C%E6%A3%80%E7%B4%A2%20-%20%E6%95%B0%E7%BB%84%E5%8F%AF%E4%BF%AE%E6%94%B9%EF%BC%88%E4%B8%AD%E7%AD%89%EF%BC%89.md
// leetcode 307
class NumArray2 {

    int[] ft;

    private int lowbit(int x) {
        return x & -x;
    }

    // Time: O(logN)
    // 增加特定index的值
    // 一路往上加上去
    public void add(int index, int value) {
        while (index < ft.length) {
            ft[index] += value;
            index += lowbit(index);
        }
    }

    // Time: O(logN)
    // 得到[1 ~ index]的總和
    public int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += ft[index];
            index -= lowbit(index);
        }

        return sum;
    }

    int[] nums;

    public NumArray2(int[] nums) {
        this.nums = nums;
        ft = new int[nums.length + 1]; // 因為fenwick tree是從1開始的
        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        add(index + 1, val - nums[index]); // 加上新值和舊值的差，變成更新後的值
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        // 前right的總和 - 前(left - 1)個總和
        // 因為ft從1開始，所以要+1
        return query(right + 1) - query(left);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */