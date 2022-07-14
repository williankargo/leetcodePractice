import java.util.ArrayDeque;
import java.util.Queue;

/*
 * @lc app=leetcode id=200 lang=java
 *
 * [200] Number of Islands
 */

// @lc code=start
class Solution {
    // Time O(RC)
    // Space O(RC)
    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int islands = 0;
        int row = grid.length;
        int column = grid[0].length;
        boolean[][] isVisited = new boolean[row][column];

        for (int x = 0; x < row; x++) { // R
            for (int y = 0; y < column; y++) { // C
                if (grid[x][y] == '1' && !isVisited[x][y]) {
                    bfs(grid, x, y, isVisited);
                    islands++;
                }
            }
        }
        return islands;
    }

    // 四個方向的偏移量
    int[] deltaX = { 0, 1, -1, 0 };
    int[] deltaY = { 1, 0, 0, -1 };
    private void bfs(char[][] grid, int x, int y, boolean[][] visited) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Pair coor = queue.poll();
            // 遍歷上右左下四個方向
            for (int direction = 0; direction < 4; direction++) {
                int newX = coor.x + deltaX[direction];
                int newY = coor.y + deltaY[direction];
                if (!isValid(grid, newX, newY, visited)) {
                    continue;
                }
                queue.offer(new Pair(newX, newY));
                visited[newX][newY] = true;
            }
        }
    }

    // 有沒有資格塞進queue裡
    private boolean isValid(char[][] grid, int x, int y, boolean[][] isVisted) {

        // boundry
        int MaxColumn = grid[0].length - 1;
        int MaxRow = grid.length - 1;
        if (x > MaxRow || y > MaxColumn || x < 0 || y < 0) {
            return false;
        }

        // 0 or 1
        if (grid[x][y] == '0') {
            return false;
        }

        // isVisited
        return !isVisted[x][y];
    }
}

class Pair {
    int x;
    int y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// @lc code=end
