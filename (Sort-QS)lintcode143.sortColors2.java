// https://www.lintcode.com/problem/143/

public class sortColors2 {
    /**
     * @param colors: A list of integer
     * @param k:      An integer
     * @return: nothing
     */

    // Time: N(logK) 因為每層有N個東西要遍歷，有logK層(遞迴的時間複雜度！！！！！)
    // Space: logK 因為每次是對K分成左右來處理，因此有logK層
    public void sortColors2(int[] colors, int k) {
        // write your code here
        if (colors == null || colors.length == 2) {
            return;
        }
        quickSortPartition(colors, 0, colors.length - 1, 0, k);
    }

    // 1. definition
    private void quickSortPartition(int[] nums, int left, int right, int colorFrom, int colorTo) {

        // 3. exit
        if (colorFrom == colorTo) {
            return;
        }

        // 2. divide
        int midColor = (colorFrom + colorTo) / 2;
        int l = left;
        int r = right;
        while (l <= r) {

            while (l <= r && nums[l] <= midColor) {
                l++;
            }

            while (l <= r && nums[r] > midColor) {
                r--;
            }

            if (l <= r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l++;
                r--;
            }
        }

        // left part
        quickSortPartition(nums, left, r, colorFrom, midColor);
        // right part
        quickSortPartition(nums, l, right, midColor + 1, colorTo);

    }

}