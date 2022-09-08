import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {

    // Time: O (node# + edge#)
    // https://leetcode.com/problems/minimum-knight-moves/discuss/386992/get-rid-of-TLE-for-python-BFS
    public static final int[][] directions = new int[][] { { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }, { 2, 1 },
            { 2, -1 }, { -2, 1 }, { -2, -1 } };

    public int minKnightMoves(int x, int y) {

        // we can change x,y to absolute value since the moving pattern is symmetric.
        x = Math.abs(x);
        y = Math.abs(y);

        Queue<Pair> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        int steps = 0;

        queue.offer(new Pair(0, 0));
        visited.add("0,0");
        while (!queue.isEmpty()) {

            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Pair curr = queue.poll();
                if (curr.x == x && curr.y == y) {
                    return steps;
                }

                for (int[] d : directions) {
                    int neighborX = curr.x + d[0];
                    int neighborY = curr.y + d[1];
                    // 畫圖看可以知道，如果先往後退到有負數的象限，再往前進可以進兩格到第一象限，能做到此事情的是-1範圍內的點
                    if (!visited.contains(neighborX + "," + neighborY) && neighborX >= -1 && neighborY >= -1) {
                        queue.add(new Pair(neighborX, neighborY));
                        visited.add(neighborX + "," + neighborY);
                    }
                }
            }
            steps++;
        }

        return 0;
    }

    class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}