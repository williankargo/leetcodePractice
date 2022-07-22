// 在未排序的數組上二分
class Solution {

    // Time: worst O(m+n)
    // Space: O(1)
    public int searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        // 從左下出發！
        int y = matrix.length - 1; // 往上走
        int x = 0; // 往右走
        int count = 0;
        while (y >= 0 && x < matrix[0].length) {
            if (matrix[y][x] == target) {
                count++;
                y--;
                x++;
            } else if (matrix[y][x] > target) {
                y--;
            } else if (matrix[y][x] < target) {
                x++;
            }
        }

        return count;
    }
}