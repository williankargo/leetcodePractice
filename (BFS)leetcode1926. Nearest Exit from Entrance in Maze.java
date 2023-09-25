
// TC: O(N)
// SC: O(N)

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {

    // direction
    static int[][] directions = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public int nearestExit(char[][] maze, int[] entrance) {

        Queue<int[]> queue = new ArrayDeque<>();
        Set<Integer> isVisited = new HashSet<>();
        int rowLength = maze[0].length;
        int route = 0;

        queue.offer(entrance);
        isVisited.add(convert21D(entrance[0], entrance[1], rowLength));

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] node = queue.poll();
                for (int[] direction : directions) {
                    int row = node[0] + direction[0];
                    int column = node[1] + direction[1];
                    if (!withinBorder(row, column, maze)) {
                        continue;
                    }
                    if (isVisited.contains(convert21D(row, column, rowLength))
                            || (maze[row][column] == '+')) {
                        continue;
                    }
                    if (isBorder(row, column, maze, entrance)) {
                        return ++route;
                    }
                    queue.add(new int[] { row, column });
                    isVisited.add(convert21D(row, column, rowLength));
                }
            }
            route++;
        }
        return -1;
    }

    private int convert21D(int row, int column, int rowLength) {
        return row * rowLength + column;
    }

    private boolean withinBorder(int row, int column, char[][] maze) {
        if (row > maze.length - 1 || column > maze[0].length - 1 || row < 0 || column < 0) {
            return false;
        }
        return true;
    }

    private boolean isBorder(int row, int column, char[][] maze, int[] entrance) {
        if ((row == maze.length - 1 || column == maze[0].length - 1 || row == 0 || column == 0)
                &&
                !(row == entrance[0] && column == entrance[1])) {
            return true;
        }
        return false;
    }
}