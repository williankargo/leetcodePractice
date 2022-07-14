import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}

class Solution {

    public int shortestPath(boolean[][] grid, Point source, Point destination) {

        // 初始判斷
        if (grid == null || grid[0] == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        // direction
        int[] moveX = { -1, 1, 2, 2, 1, -1, -2, -2 };
        int[] moveY = { 2, 2, 1, -1, -2, -2, -1, 1 };

        Queue<Point> queue = new ArrayDeque();
        Map<Integer, Integer> map = new HashMap();

        int col = grid[0].length;

        queue.offer(source);
        int rootPoint = col * source.x + source.y;
        map.put(rootPoint, 0);

        while (!queue.isEmpty()) {
            Point curNode = queue.poll();
            int curPoint = col * curNode.x + curNode.y;

            if (destination.x == curNode.x && destination.y == curNode.y) {
                return map.get(curPoint);
            }

            for (int direction = 0; direction < 8; direction++) {
                int newX = curNode.x + moveX[direction];
                int newY = curNode.y + moveY[direction];

                if (isValid(newX, newY, grid)) {
                    int newPoint = col * newX + newY;
                    if (map.containsKey(newPoint)) {
                        continue;
                    }
                    queue.offer(new Point(newX, newY));
                    map.put(newPoint, map.get(curPoint) + 1);
                }

            }

        }
        return -1;
    }

    private boolean isValid(int x, int y, boolean[][] grid) {

        int row = grid.length;
        int column = grid[0].length;
        if (x < 0 || y < 0 || x >= row || y >= column) {
            return false;
        }

        return !grid[x][y];
    }
}