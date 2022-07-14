import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** method 1 */
/**
 * DFS: backtracking + memo
 * 因為memo不適合找出所有具體方案，所以用backtracking，然後用memo來優化
 */
class Solution {

    // Time: O(N * N) ==> O(狀態總數 * 求出每個狀態的時間複雜度)
    // Space: O(N)
    public List<String> wordBreak(String s, Set<String> wordDict) {

        List<String> result = new ArrayList<>();

        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return result;
        }

        List<String> temp = new ArrayList<>();
        Map<Integer, Boolean> memo = new HashMap<>();
        dfs(s, 0, memo, temp, result, wordDict, getMaxLength(wordDict));

        return result;
    }

    // Back Tracking
    // 0. 向下探索，不對再回溯
    private void dfs(String s, int Index, Map<Integer, Boolean> memo, List<String> temp, List<String> result,
            Set<String> wordDict, int maxLength) {

        // 1. exit
        if (Index == s.length()) {
            joinToResult(temp, result);
            return;
        }

        // 記憶化判斷當前Index下去有沒有可行的答案
        if (!isValid(s, Index, memo, wordDict, maxLength)) {
            return;
        }

        // 2. decide
        for (int i = Index; i < s.length(); i++) {

            // prunning
            if (((i + 1) - Index) > maxLength) {
                continue;
            }

            String word = s.substring(Index, i + 1); // 切割字串成單字

            if (!wordDict.contains(word)) {
                continue;
            }

            temp.add(word);
            dfs(s, i + 1, memo, temp, result, wordDict, maxLength);
            temp.remove(temp.size() - 1);
        }
    }

    private int getMaxLength(Set<String> wordDict) {
        int max = 0;
        for (String word : wordDict) {
            max = Math.max(max, word.length());
        }

        return max;
    }

    private void joinToResult(List<String> temp, List<String> result) {

        boolean isFirstTime = true;
        StringBuilder string = new StringBuilder(); // todo
        for (String word : temp) {
            if (isFirstTime) {
                isFirstTime = false;
            } else {
                string.append(" ");
            }
            string.append(word);
        }

        result.add(string.toString()); // todo
    }

    // memo
    // 0. 當前Index下去有沒有可行的答案
    private boolean isValid(String s, int Index, Map<Integer, Boolean> memo, Set<String> wordDict, int maxLength) {

        // 1. exit
        if (Index == s.length()) {
            return true;
        }

        // memo
        if (memo.containsKey(Index)) {
            return memo.get(Index);
        }

        // 2.
        memo.put(Index, false);
        for (int i = Index; i < s.length(); i++) {

            // prunning
            if (((i + 1) - Index) > maxLength) {
                continue;
            }

            String word = s.substring(Index, i + 1);
            if (!wordDict.contains(word)) {
                continue;
            }

            if (isValid(s, i + 1, memo, wordDict, maxLength)) {
                memo.put(Index, true);
            }
        }

        // 3.
        return memo.get(Index);
    }
}

/** method 2 */
/** DFS: DC + memo */
class Solution2 {

    public List<String> wordBreak(String s, Set<String> wordDict) {

        if (s.length() == 0 || s == null || wordDict.size() == 0 || wordDict == null) {
            return new ArrayList<>();
        }

        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(0, s, memo, getMaxLength(wordDict), wordDict);
    }

    // get the max length
    private int getMaxLength(Set<String> wordDict) {
        int max = 0;
        for (String word : wordDict) {
            max = Math.max(max, word.length());
        }
        return max;
    }

    // DC + memo
    // 0.
    // 返回以s[Index]開頭的所有排列可能性
    private List<String> dfs(int Index, String s, Map<Integer, List<String>> memo, int maxLength,
            Set<String> wordDict) {

        List<String> result = new ArrayList<>();

        // 1. 出口條件
        // if (Index == s.length()){
        // return result;
        // }

        // memo
        if (memo.containsKey(Index)) {
            return memo.get(Index);
        }

        // 2.
        for (int i = Index; i < s.length(); i++) {

            // left
            String word = s.substring(Index, i + 1);

            // pruning
            if (word.length() > maxLength) {
                continue;
            }

            if (!wordDict.contains(word)) {
                continue;
            }

            // 取代出口條件
            // 但不是說如果保留出口條件，把這個註解掉就可以work，這裡的邏輯已經跟下面連在一起了！
            if ((i + 1) == s.length()) {
                result.add(word); // combine = left
                break;
            }

            // right
            List<String> sections = dfs(i + 1, s, memo, maxLength, wordDict);
            // combine = left + right
            for (String section : sections) {
                result.add(word + " " + section);
            }
        }

        // memo
        memo.put(Index, result);

        return result;
    }
}