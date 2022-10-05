import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

// Time: 预处理出所有树点的复杂度为O(N*M)，对树点进行排序的复杂度为O(NMlogNM)，最多有N*M个树点，对每两个相邻树点运用 BFS求最短路的复杂度为O(N*M)，统计完整路径的复杂度为O(N^2*M^2)
// Space: O(N^2)
// https://www.youtube.com/watch?v=OFkLC30OxXM
class Solution {

    static int forestXlength;
    static int forestYlength;
    List<List<Integer>> ground;

    public int cutOffTree(List<List<Integer>> forest) {

        // start from (0, 0)
        if (forest.get(0).get(0) == 0) {
            return -1;
        }

        // global variable
        forestXlength = forest.get(0).size();
        forestYlength = forest.size();
        this.ground = forest;

        // 先把路徑找出來
        List<int[]> route = new ArrayList<>();
        for (int j = 0; j < forest.size(); j++) {
            for (int i = 0; i < forest.get(0).size(); i++) {
                // 因為題目規定從 (0,0)開始遍歷，下面會處理(0,0)這個case，如果這裡把1放進去，下面讀next時會讀到1，會發生錯誤
                if (forest.get(j).get(i) > 1) {
                    route.add(new int[] { i, j, forest.get(j).get(i) });
                }
            }
        }

        // 依照排序value升序排序
        Collections.sort(route, (o1, o2) -> (o1[2] - o2[2]));

        // 題目規定一定要從(0,0)開始遍歷
        int x = 0, y = 0, path = 0;
        for (int[] ne : route) {
            int nextX = ne[0];
            int nextY = ne[1];
            int temp = bfs(x, y, nextX, nextY);
            if (temp == -1) {
                return -1;
            }
            path += temp;
            x = nextX;
            y = nextY;
        }

        return path;
    }

    // 題目保證每個非01的都不一樣，但可能會重複走，看花花影片例子
    int[][] directions = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    private int bfs(int curX, int curY, int nextX, int nextY) {

        // 如果從2開始，
        if (curX == nextX && curY == nextY) {
            return 0;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[forestYlength][forestXlength];
        queue.offer(new int[] { curX, curY });
        isVisited[curY][curX] = true;

        int layer = 0;
        while (!queue.isEmpty()) {
            int queue_size = queue.size();
            for (int i = 0; i < queue_size; i++) {
                int[] current = queue.poll();
                int current_x = current[0];
                int current_y = current[1];
                for (int[] direction : directions) {
                    int neighbor_x = current_x + direction[0];
                    int neighbor_y = current_y + direction[1];
                    if (isValid(neighbor_x, neighbor_y)) {
                        if (isVisited[neighbor_y][neighbor_x] ||
                                ground.get(neighbor_y).get(neighbor_x) == 0) {
                            continue;
                        }
                        if (neighbor_x == nextX && neighbor_y == nextY) {
                            return ++layer;
                        }
                        queue.offer(new int[] { neighbor_x, neighbor_y });
                        isVisited[neighbor_y][neighbor_x] = true;
                    }
                }
            }
            layer++;
        }

        return -1; // can't find match case
    }

    private boolean isValid(int x, int y) {
        if (x < 0 || x > forestXlength - 1 || y < 0 || y > forestYlength - 1) {
            return false;
        }
        return true;
    }
}