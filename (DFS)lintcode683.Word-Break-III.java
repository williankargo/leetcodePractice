import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// method1: DFS(DC + memo)
// Time: O(N * N)
// Space: O(N)
class Solution1 {

    // 求方案總數 -> memo
    public int wordBreak3(String s, Set<String> dict) {

        if (s == null || s.length() == 0 || dict == null || dict.size() == 0) {
            return 0;
        }

        // be careful to Lowercase and UpperCase problem
        Set<String> lowerDict = new HashSet<>();
        int maxLength = initialize(dict, lowerDict);
        Map<Integer, Integer> memo = new HashMap<>();

        return dfs(s.toLowerCase(), 0, memo, lowerDict, maxLength);
    }

    private int initialize(Set<String> dict, Set<String> lowerDict) {

        int max = 0;
        for (String word : dict) {
            lowerDict.add(word.toLowerCase());
            max = Math.max(max, word.length());
        }
        return max;
    }

    // 0.
    // 以s[Index]為開頭的字串有多少種組合性
    private int dfs(String s, int Index, Map<Integer, Integer> memo, Set<String> lowerDict, int maxLength) {

        // 1.
        // 以呼叫dfs()的那個角色去思考
        if (Index == s.length()) {
            return 1; // rightWord完全沒有東西，都在leftWord，也是種選擇
        }

        if (memo.containsKey(Index)) {
            return memo.get(Index);
        }

        // 2.
        int count = 0;
        for (int i = Index; i < s.length(); i++) {

            // left
            String leftWord = s.substring(Index, i + 1);
            // leftWord如果存在，不用算數目leftCount，因為rightWord計算出來的個數前面搭配一個leftWord，數目不變

            // prunning
            if (leftWord.length() > maxLength) {
                break;
            }

            if (!lowerDict.contains(leftWord)) {
                continue;
            }

            // right
            int rightCount = dfs(s, i + 1, memo, lowerDict, maxLength);

            // combine = sum + right
            // 要把答案累積起來
            count = count + rightCount;
        }
        memo.put(Index, count);

        // 3.
        return memo.get(Index);
    }

}

// DFS (BackTracking + Memo)
// 不適當的用方法，可能會超空間，例如這一題
class Solution2 {

    // BT method: 但可能會超空間
    public int wordBreak3(String s, Set<String> dict) {

        if (s == null || s.length() == 0 || dict == null || dict.size() == 0) {
            return 0;
        }

        // be careful to Lowercase and UpperCase problem
        Set<String> lowerDict = new HashSet<>();
        int maxLength = initialize(dict, lowerDict);
        Map<Integer, Boolean> memo = new HashMap<>();
        List<String> temp = new ArrayList<>();
        List<String> result = new ArrayList<>();

        dfs(s.toLowerCase(), 0, temp, result, memo, lowerDict, maxLength);

        return result.size();
    }

    private int initialize(Set<String> dict, Set<String> lowerDict) {

        int max = 0;
        for (String word : dict) {
            lowerDict.add(word.toLowerCase());
            max = Math.max(max, word.length());
        }
        return max;
    }

    // 0.
    private void dfs(String s, int Index, List<String> temp, List<String> result, Map<Integer, Boolean> memo,
            Set<String> lowerDict, int maxLength) {

        // 1.
        if (Index == s.length()) {
            result.add("ans");
            return;
        }

        // memo
        // 此Index走下去有沒有解
        if (!isValid(s, Index, memo, lowerDict, maxLength)) {
            return;
        }

        // 2.
        for (int i = Index; i < s.length(); i++) {

            String prefix = s.substring(Index, i + 1);

            // prunning
            if (prefix.length() > maxLength) {
                break;
            }

            if (!lowerDict.contains(prefix)) {
                continue;
            }

            temp.add(prefix);
            dfs(s, i + 1, temp, result, memo, lowerDict, maxLength);
            temp.remove(temp.size() - 1);
        }
    }

    // memo
    // 0.
    // 此Index走下去有沒有解
    private boolean isValid(String s, int Index, Map<Integer, Boolean> memo, Set<String> lowerDict, int maxLength) {

        // 1.
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

            String word = s.substring(Index, i + 1);
            if (word.length() > maxLength) {
                continue;
            }
            if (!lowerDict.contains(word)) {
                continue;
            }

            if (isValid(s, i + 1, memo, lowerDict, maxLength)) {
                memo.put(Index, true);
                break;
            }
        }

        // 3.
        return memo.get(Index);
    }

}