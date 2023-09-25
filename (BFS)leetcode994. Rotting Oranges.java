
// TC: O(N)
// SC: O(N)

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {

    static int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int orangesRotting(int[][] grid) {

        Queue<int[]> queue = new ArrayDeque<>();
        Set<Integer> isVisited = new HashSet<>();
        int rows = grid[0].length;
        int time = 0;
        int totalOranges = 0;

        // 0. find all of the rotten orange
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] { i, j });
                    isVisited.add(i * rows + j);
                }
                if (grid[i][j] == 1) {
                    totalOranges++;
                }
            }
        }

        // 1.
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] node = queue.poll();
                for (int[] direction : directions) {
                    int row = node[0] + direction[0];
                    int col = node[1] + direction[1];

                    if (!withinBorder(row, col, grid)) {
                        continue;
                    }
                    if (isVisited.contains(row * rows + col) || grid[row][col] == 0) {
                        continue;
                    }
                    queue.offer(new int[] { row, col });
                    totalOranges--;
                    isVisited.add(row * rows + col);

                    if (totalOranges == 0) {
                        return ++time;
                    }
                }
            }
            time++;
        }

        if (totalOranges != 0) {
            return -1;
        }

        return 0;
    }

    private boolean withinBorder(int row, int col, int[][] grid) {
        if (row > grid.length - 1 || col > grid[0].length - 1 || row < 0 || col < 0) {
            return false;
        }
        return true;
    }
}