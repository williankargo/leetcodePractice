import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

    // TC: O(N^2)
    // SC: O(N^2)
    public static final int[][] directions = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        // no need to floodFill
        if (image[sr][sc] == color) {
            return image;
        }

        int originalColor = image[sr][sc];

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[image.length][image[0].length];

        queue.offer(new int[] { sc, sr });
        isVisited[sr][sc] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int curX = curr[0];
            int curY = curr[1];
            image[curY][curX] = color;
            for (int[] direction : directions) {
                int newX = curX + direction[0];
                int newY = curY + direction[1];
                if (newX >= 0 && newX <= image[0].length - 1
                        && newY >= 0 && newY <= image.length - 1
                        && !isVisited[newY][newX] && image[newY][newX] == originalColor) {
                    queue.offer(new int[] { newX, newY });
                    isVisited[newY][newX] = true;
                }
            }
        }

        return image;
    }
}