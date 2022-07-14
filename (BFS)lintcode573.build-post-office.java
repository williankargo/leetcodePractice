import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {

    int row;
    int column;
    int[][] grid;
    static int[][] directions = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    // Space: O(row*cloumn*postOffice#)
    // Time: O(row*column*postOffice#)
    // https://zhengyang2015.gitbooks.io/lintcode/content/build_post_office_ii_573.html
    public int shortestDistance(int[][] grid) {

        if (grid == null || grid[0] == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        row = grid.length;
        column = grid[0].length;
        this.grid = grid;

        // 紀錄郵局位置
        List<Position> PostOfficeList = new ArrayList<>();
        int PostOfficeListLength;

        // 把郵局加到List裡
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < column; i++) {
                if (grid[j][i] == 1) {
                    PostOfficeList.add(new Position(i, j));
                }
            }
        }
        PostOfficeListLength = PostOfficeList.size();

        // 都是郵局
        if (row * column == PostOfficeListLength) {
            return -1;
        }

        // 紀錄路徑長度
        // grid[k][j][i] => 第K個郵局到grid[j][i]的距離
        // 先把所有路徑初始化成最大值
        int[][][] pathArray = new int[PostOfficeListLength][row][column];
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < column; i++) {
                for (int t = 0; t < PostOfficeListLength; t++) {
                    pathArray[t][j][i] = Integer.MAX_VALUE;
                }
            }
        }

        // 統計各郵局到各點的距離
        for (int i = 0; i < PostOfficeList.size(); i++) {
            bfs(PostOfficeList.get(i), pathArray, i);
        }

        int shortestPath = Integer.MAX_VALUE;
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < column; i++) {
                int kSum = 0;
                for (int t = 0; t < PostOfficeListLength; t++) {

                    // 非house
                    if (grid[j][i] != 0) {
                        kSum = Integer.MAX_VALUE;
                        break;
                    }
                    // 代表這個點無法到全部的K個office
                    if (pathArray[t][j][i] == Integer.MAX_VALUE) {
                        kSum = Integer.MAX_VALUE;
                        break;
                    }
                    kSum += pathArray[t][j][i];
                }
                shortestPath = (kSum < shortestPath) ? kSum : shortestPath;
            }
        }
        return shortestPath != Integer.MAX_VALUE ? shortestPath : -1;
    }

    private void bfs(Position position, int[][][] pathArray, int k) {

        Queue<Position> queue = new ArrayDeque<>();
        boolean[][] gone = new boolean[row][column];

        queue.offer(position);
        gone[position.y][position.x] = true;

        int path = 0;
        while (!queue.isEmpty()) {

            path++;
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                Position current = queue.poll();
                for (int[] direction : directions) {
                    int neighborX = current.x + direction[0];
                    int neighborY = current.y + direction[1];

                    // boundary
                    if (isNotValid(neighborX, neighborY)) {
                        continue;
                    }

                    // 只記錄house
                    if (grid[neighborY][neighborX] != 0) {
                        continue;
                    }

                    // gone before
                    if (gone[neighborY][neighborX]) {
                        continue;
                    }

                    Position neighbor = new Position(neighborX, neighborY);
                    queue.offer(neighbor);
                    pathArray[k][neighborY][neighborX] = path; // 統計路徑長度
                    gone[neighborY][neighborX] = true;
                }
            }
        }
    }

    private boolean isNotValid(int x, int y) {

        if (x < 0 || y < 0 || x > column - 1 || y > row - 1) {
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