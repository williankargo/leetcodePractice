import java.util.ArrayList;
import java.util.List;

class Solution {

    // 所有解 => DFS
    // graph: https://www.youtube.com/watch?v=s9fokUqJ76A
    public List<String> generateParenthesis(int n) {

        if (n == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        dfs(0, 0, n, new StringBuilder(), result);
        return result;
    }

    // back-tracking
    private void dfs(int leftP, int rightP, int n, StringBuilder word, List<String> result) {

        // exit
        if (rightP == n) {
            result.add(word.toString());
            return;
        }

        if (leftP < n) {
            word.append("(");
            dfs(leftP + 1, rightP, n, word, result);
            word.deleteCharAt(word.length() - 1);
        }

        if (rightP < leftP) {
            word.append(")");
            dfs(leftP, rightP + 1, n, word, result);
            word.deleteCharAt(word.length() - 1);
        }

    }
}