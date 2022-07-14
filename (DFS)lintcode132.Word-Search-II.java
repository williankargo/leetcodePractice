import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    // Recursion - BT
    // DFS: 找滿足某個條件的所有方案
    // Time: O(N * 4^N)
    // Space: O(N * 4^N)
    public List<String> wordSearchII(char[][] board, List<String> words) {

        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return new ArrayList<String>();
        }

        // 建立篩選的set
        Set<String> prefixSet = new HashSet<>();
        Set<String> wordSet = new HashSet<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                prefixSet.add(word.substring(0, i + 1));
            }
            wordSet.add(word);
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        Set<String> resultSet = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                visited[i][j] = true;
                dfs(j, i, visited, String.valueOf(board[i][j]), resultSet, wordSet, prefixSet, board);
                visited[i][j] = false;
            }
        }

        return new ArrayList<String>(resultSet);
    }

    int[][] direction = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    private void dfs(int x, int y, boolean[][] visited, String temp, Set<String> resultSet, Set<String> wordSet,
            Set<String> prefixSet, char[][] board) {

        // exit
        if (!prefixSet.contains(temp)) {
            return;
        }

        // add to result
        if (wordSet.contains(temp)) {
            resultSet.add(temp);
        }

        // decide
        for (int[] dt : direction) {
            int newX = x + dt[0];
            int newY = y + dt[1];

            if (notValid(newX, newY, board) || visited[newY][newX]) {
                continue;
            }

            visited[newY][newX] = true;
            dfs(newX, newY, visited, temp + String.valueOf(board[newY][newX]), resultSet, wordSet, prefixSet, board);
            visited[newY][newX] = false;
        }
    }

    private boolean notValid(int newX, int newY, char[][] board) {

        if (newX < 0 || newY < 0 || newX >= board[0].length || newY >= board.length) {
            return true;
        }
        return false;
    }
}