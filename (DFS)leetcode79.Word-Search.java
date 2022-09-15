class Solution {

    // Time: (W * L * (4 ^ Length)) #4 -> 4個方向
    // Space: O(W * L)
    // https://www.jiuzhang.com/problem/word-search/

    public static final int[][] directions = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public boolean exist(char[][] board, String word) {

        // prunning
        boolean[][] isVisited = new boolean[board.length][board[0].length];
        boolean canFind = false;

        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (word.charAt(0) == board[j][i]) {
                    isVisited[j][i] = true;
                    canFind = dfs(i, j, 1, isVisited, board, word);
                    isVisited[j][i] = false;
                    if (canFind) {
                        return true;
                    }
                }
            }
        }

        return canFind;
    }

    // 0. 此開頭走下去能不能找到
    private boolean dfs(int x, int y, int currentLength, boolean[][] isVisited, char[][] board, String word) {

        // 1. exit
        if (currentLength == word.length()) {
            return true;
        }

        // 2. decide
        boolean canFind = false;
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (isValid(newX, newY, board, isVisited, word, currentLength)) {
                isVisited[newY][newX] = true;
                canFind = dfs(newX, newY, currentLength + 1, isVisited, board, word);
                isVisited[newY][newX] = false;
            }
            if (canFind) {
                return true;
            }
        }

        // 3. return
        return canFind;
    }

    private boolean isValid(int x, int y, char[][] board, boolean[][] isVisited, String word, int currentLength) {
        int width = board[0].length;
        int length = board.length;
        if (x < 0 || x >= width || y < 0 || y >= length || isVisited[y][x]) {
            return false;
        }
        if (board[y][x] != word.charAt(currentLength)) {
            return false;
        }
        return true;
    }
}