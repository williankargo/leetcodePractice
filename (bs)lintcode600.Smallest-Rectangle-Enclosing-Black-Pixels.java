import java.util.ArrayDeque;
import java.util.Queue;

// 未排序的bs解法
// Time: O(NlogM + MlogN)
// Space: O(1)
class Solution1 {
    // 本題可以找到二段性，因此也可以用二分法，對上下左右邊進行bs
}

// BFS解法
// Time: O(N + E) N為點的數量 E為邊的數量
// Space: O(N)
class Solution2 {

    public int minArea(char[][] image, int x, int y) {

        if (image == null || image[0] == null) {
            return 0;
        }

        int[][] directions = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int rowLength = image.length;
        int columnLength = image[0].length;

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        Queue<Pair> queue = new ArrayDeque<>();
        Pair start = new Pair(y, x);

        queue.offer(start);
        image[x][y] = '0'; // 紀錄已經走過了

        while (!queue.isEmpty()) {

            Pair current = queue.poll();

            minX = Math.min(minX, current.x);
            maxX = Math.max(maxX, current.x);
            minY = Math.min(minY, current.y);
            maxY = Math.max(maxY, current.y);

            for (int[] direction : directions) {

                int newY = current.y + direction[0];
                int newX = current.x + direction[1];

                if ((newX < rowLength && newX >= 0) && (newY < columnLength && newY >= 0)
                        && image[newX][newY] == '1') {
                    Pair neighbor = new Pair(newY, newX);
                    queue.offer(neighbor);
                    image[newX][newY] = '0';
                }
            }
        }

        int height = maxX - minX + 1;
        int width = maxY - minY + 1;

        return width * height;
    }

    class Pair {
        int x, y;

        Pair(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }
}