// TC: O(MN)
// SC: O(1)
class Solution {
    public void gameOfLife(int[][] board) {

        // status:
        // dead -> dead: 0
        // live -> live: 1
        // dead -> live: 2
        // live -> dead: 3

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int neighbors = countNeighbors(j, i, board);
                if (board[i][j] == 0) {
                    if (neighbors == 3) {
                        board[i][j] = 2;
                    }
                } else if (board[i][j] == 1) {
                    if (neighbors > 3 || neighbors < 2) {
                        board[i][j] = 3;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                } else if (board[i][j] == 3) {
                    board[i][j] = 0;
                }
            }
        }

    }

    private int countNeighbors(int j, int i, int[][] board) {

        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
        int xLength = board[0].length;
        int yLength = board.length;
        int count = 0;

        for (int[] direction : directions) {
            int x = j + direction[0];
            int y = i + direction[1];
            if (x >= 0 && y >= 0 && x < xLength && y < yLength && (board[y][x] == 1 || board[y][x] == 3)) {
                count++;
            }
        }

        return count;
    }
}