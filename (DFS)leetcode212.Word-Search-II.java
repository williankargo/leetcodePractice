import java.util.ArrayList;
import java.util.List;

class Solution {

    public static int[][] directions = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public List<String> findWords(char[][] board, String[] words) {

        List<String> result = new ArrayList<>();
        boolean[][] isVisited = new boolean[board.length][board[0].length];
        for (String word : words) {
            boolean canFind = false;
            for (int i = 0; i < board[0].length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (word.charAt(0) == board[j][i]) {
                        isVisited[j][i] = true;
                        canFind = dfs(i, j, isVisited, board, 1, word, result);
                        isVisited[j][i] = false;
                    }
                    if (canFind) {
                        break;
                    }
                }
                if (canFind) {
                    break;
                }
            }
        }

        return result;
    }

    // dfs
    // 0. backtracking
    private boolean dfs(int i, int j, boolean[][] isVisited, char[][] board, int index, String word,
            List<String> result) {

        // 1. exit
        if (index == word.length()) {
            result.add(word);
            return true; // find it!
        }

        // 2. decide
        boolean canFind = false;
        for (int[] direction : directions) {
            int xD = direction[0];
            int yD = direction[1];
            int newX = i + xD;
            int newY = j + yD;
            if (isValid(newX, newY, board, isVisited, index, word)) {
                isVisited[newY][newX] = true;
                canFind = dfs(newX, newY, isVisited, board, index + 1, word, result);
                isVisited[newY][newX] = false;
            }
            if (canFind) {
                return true;
            }
        }

        // 3. return
        return false;
    }

    private boolean isValid(int newX, int newY, char[][] board, boolean[][] isVisited, int index, String word) {
        if (newX < 0 || newX >= board[0].length || newY < 0 || newY >= board.length || isVisited[newY][newX]) {
            return false;
        }
        if (board[newY][newX] != word.charAt(index)) {
            return false;
        }
        return true;
    }

}