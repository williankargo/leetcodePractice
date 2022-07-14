import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {

    // Time: O(row*column)
    // Space: O(row*column)

    static int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    int row;
    int column;

    public int zombie(int[][] grid) {

        if (grid == null || grid[0] == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        row = grid.length;
        column = grid[0].length;

        Queue<Position> queue = new ArrayDeque<>();

        // 統計有幾個人可以變殭屍
        // *** 然後把殭屍加入Queue ***
        int people = 0;
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < column; i++) {

                if (grid[j][i] == 0) {
                    people++;
                }

                if (grid[j][i] == 1) {
                    queue.offer(new Position(i, j));
                }

            }
        }

        int days = -1; // 要從-1算起，因為第一個queueSize是本來殭屍的
        Set<Position> gone = new HashSet<>();
        while (!queue.isEmpty()) {

            int queueSize = queue.size();

            // System.out.println("queueSize: " + queueSize);
            // System.out.println("前: 第 " + days + " days");

            for (int i = 0; i < queueSize; i++) {
                Position current = queue.poll();

                for (int[] direction : directions) {

                    int neighborX = current.x + direction[0];
                    int neighborY = current.y + direction[1];

                    if (isNotValid(neighborX, neighborY)) {
                        continue;
                    }

                    if (grid[neighborY][neighborX] != 0) {
                        continue;
                    }

                    Position neighbor = new Position(neighborX, neighborY);

                    if (gone.contains(neighbor)) {
                        continue;
                    }

                    queue.offer(neighbor);
                    gone.add(neighbor);
                    grid[neighborY][neighborX] = 1;
                    people--;
                }
            }

            days++;
            // System.out.println("後: 第 " + days + " days");
        }

        if (people != 0) {
            return -1;
        }

        return days;
    }

    private boolean isNotValid(int x, int y) {
        if (x < 0 || y < 0 || x >= column || y >= row) {
            return true;
        }
        return false;
    }

    class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}