import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Solution {

    // DFS + Memo
    // Time: O(N * L), N為狀態數，L為最大詞長，也就是O(狀態總數*計算每個狀態的時間耗費)
    // Space: O(N), recursion depth: N (層數不代表次數)，此題會StackOverFlow

    public boolean wordBreak(String s, Set<String> wordSet) {

        if (s == null || wordSet == null) {
            return false;
        }

        return dfs(s, 0, getLongest(wordSet), wordSet, new HashMap<Integer, Boolean>());
    }

    private int getLongest(Set<String> wordSet) {
        int max = 0;
        for (String set : wordSet) {
            max = Math.max(set.length(), max);
        }
        return max;
    }

    // 0. 此sIndex後有沒有匹配的
    private boolean dfs(String s, int sIndex, int longest, Set<String> wordSet, Map<Integer, Boolean> memo) {

        // 1. exit
        if (sIndex == s.length()) {
            return true;
        }

        if (memo.containsKey(sIndex)) {
            return memo.get(sIndex);
        }

        // 2. decide
        memo.put(sIndex, false);
        for (int i = sIndex + 1; i <= s.length(); i++) { // <= : the final case

            if (i - sIndex > longest) {
                break;
            }

            String set = s.substring(sIndex, i);
            if (!wordSet.contains(set)) {
                continue;
            }

            if (dfs(s, i, longest, wordSet, memo)) {
                memo.put(sIndex, true);
                break;
            }
        }

        // 3. return
        return memo.get(sIndex);
    }
}