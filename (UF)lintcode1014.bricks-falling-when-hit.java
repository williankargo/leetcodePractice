class Solution {

    // unionfind => 連通狀況的問題
    // Space O(column * row)
    // Time O(N + column * row) * log(column * row) ，N是hit的長度， log(column * row)
    // 是樹的最大深度

    int row;
    int column;

    public int[] hitBricks(int[][] grid, int[][] hits) {

        // 因為一般不能修改題目原值，所以deep copy grid出來
        // 2D deep copy 一個一個遍歷
        column = grid[0].length;
        row = grid.length;
        int[][] copyGrid = new int[row][column];
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < column; i++) {
                copyGrid[j][i] = grid[j][i];
            }
        }

        // 先把所有要打的打掉
        for (int[] hit : hits) {
            int y = hit[0];
            int x = hit[1];
            copyGrid[y][x] = 0;
        }

        UnionFind uf = new UnionFind(column * row + 1); // 多一個單位當作虛擬母root
        int ROOT = column * row;

        // 先把第一層連接虛擬母root
        for (int i = 0; i < column; i++) {
            if (copyGrid[0][i] == 0) {
                continue;
            }
            int node = convertTo1D(i, 0);
            uf.merge(node, ROOT);
        }

        int[][] directions = new int[][] { { 0, -1 }, { -1, 0 } };
        // 建立連結
        for (int j = 1; j < row; j++) {
            for (int i = 0; i < column; i++) {
                if (copyGrid[j][i] == 0) {
                    continue;
                }
                for (int[] direction : directions) {
                    int neighborX = i + direction[0];
                    int neighborY = j + direction[1];
                    if (isNotValid(neighborX, neighborY, copyGrid)) {
                        continue;
                    }
                    int node = convertTo1D(i, j);
                    int neighbor = convertTo1D(neighborX, neighborY);
                    uf.merge(neighbor, node);
                }
            }
        }

        // hit
        // 倒序把hit補上，看連通root
        // https://leetcode.cn/problems/bricks-falling-when-hit/solution/803-da-zhuan-kuai-by-leetcode-r5kf/
        int[][] fourDirections = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int[] result = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {

            int x = hits[i][1];
            int y = hits[i][0];

            // 如果本來就沒有磚塊可以打
            if (grid[y][x] == 0) {
                continue;
            }

            int lostBricks = 0;
            int currentBricks = uf.getSize(ROOT);

            // 如果補回的這個節點在第一行，要特別處理
            if (y == 0) {
                uf.merge(x, ROOT);
            }

            for (int[] fourDirection : fourDirections) {

                int neighborX = x + fourDirection[0];
                int neighborY = y + fourDirection[1];

                if (isNotValid(neighborX, neighborY, copyGrid)) {
                    continue;
                }

                int neighbor = convertTo1D(neighborX, neighborY);
                int node = convertTo1D(x, y);
                uf.merge(neighbor, node);
            }

            int postMergeBricks = uf.getSize(ROOT);
            lostBricks = Math.max(0, postMergeBricks - currentBricks - 1); // 可能這一輪hit都沒有變少，但是減去自己了會變負1，所以取0
            result[i] = lostBricks;

            // 補上磚塊
            copyGrid[y][x] = 1;
        }

        return result;
    }

    private int convertTo1D(int x, int y) {
        return y * column + x;
    }

    private boolean isNotValid(int x, int y, int[][] copyGrid) {

        if (x < 0 || y < 0) {
            return true;
        }

        if (x > column - 1 || y > row - 1) {
            return true;
        }

        // == 0
        if (copyGrid[y][x] == 0) {
            return true;
        }

        return false;
    }

}

class UnionFind {

    int[] father;
    int[] sonNumber; // 紀錄包含自己向下連了幾個子nodes

    UnionFind(int n) {
        father = new int[n];
        sonNumber = new int[n];

        for (int i = 0; i < n; i++) {
            father[i] = i;
            sonNumber[i] = 1;
        }
    }

    void merge(int neighbor, int node) {

        int neighborRoot = find(neighbor);
        int nodeRoot = find(node);

        if (neighborRoot != nodeRoot) {
            father[neighborRoot] = nodeRoot;
            sonNumber[nodeRoot] = sonNumber[neighborRoot] + sonNumber[nodeRoot];
        }
    }

    int find(int node) {

        int root = node;

        // find the stem root
        while (root != father[root]) {
            root = father[root];
        }

        // compress the tree
        while (node != root) {
            int originalFather = father[node];
            father[node] = root;
            node = originalFather;
        }

        return root;
    }

    // 取得某個點的集合大小
    int getSize(int node) {
        return sonNumber[find(node)];
    }

}