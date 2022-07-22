class Solution {

    // Time: O(logN)
    // Space: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int left = 0;
        int row = matrix.length;
        int column = matrix[0].length;
        int right = row * column - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (convert(matrix, mid) > target) {
                right = mid;
            } else if (convert(matrix, mid) < target) {
                left = mid;
            } else {
                return true;
            }
        }

        if (convert(matrix, left) == target) {
            return true;
        }

        if (convert(matrix, right) == target) {
            return true;
        }

        return false;
    }

    private int convert(int[][] matrix, int index) {
        int x = index % matrix[0].length;
        int y = index / matrix[0].length;
        return matrix[y][x];
    }
}