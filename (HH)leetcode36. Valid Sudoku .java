// TC: O(N)
// SC: O(N)

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {

        List<Set<Character>> rowSets = new ArrayList<>();
        List<Set<Character>> colSets = new ArrayList<>();
        List<Set<Character>> boxSets = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            rowSets.add(i, new HashSet<>());
            colSets.add(i, new HashSet<>());
            boxSets.add(i, new HashSet<>());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                char val = board[i][j];
                if (val == '.') {
                    continue;
                }

                // row
                if (rowSets.get(i).contains(val)) {
                    return false;
                }
                rowSets.get(i).add(val);

                // column
                if (colSets.get(j).contains(val)) {
                    return false;
                }
                colSets.get(j).add(val);

                // box
                int boxNum = (i / 3) * 3 + j / 3;
                if (boxSets.get(boxNum).contains(val)) {
                    return false;
                }
                boxSets.get(boxNum).add(val);
            }
        }

        return true;
    }
}