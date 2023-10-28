// TC: O(N)
// SC: O(N)

import java.util.ArrayList;
import java.util.List;

class Solution {

    int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // right, down, left, up

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        int x_length = matrix[0].length;
        int y_length = matrix.length;
        int matrix_size = x_length * y_length;

        int y = 0;
        int x = -1;
        while (result.size() != matrix_size) {
            for (int[] direction : directions) {
                // 搞清楚 matrix[y_index][x_index] !!!
                while (isValid(y + direction[0], x + direction[1], y_length, x_length) &&
                        matrix[y + direction[0]][x + direction[1]] != -200) { // valid and not gone
                    y += direction[0];
                    x += direction[1];
                    result.add(matrix[y][x]);
                    matrix[y][x] = -200;
                    if (result.size() == matrix_size) {
                        return result;
                    }
                }
            }
        }

        return result;
    }

    private boolean isValid(int y, int x, int y_length, int x_length) {
        return y >= 0 && y < y_length && x >= 0 && x < x_length;
    }
}