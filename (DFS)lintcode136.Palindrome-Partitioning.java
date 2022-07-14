import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// method 1
// DFS (BackTracking + memo)
// Time: O(N^2)
// Space: O(N)
class Solution1 {

    public List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();
        if (s.length() == 0 || s == null) {
            return result;
        }

        List<String> temp = new ArrayList<>();
        Map<Integer, Boolean> memo = new HashMap<>();
        Map<String, Boolean> memoForP = new HashMap<>();

        dfs(0, s, temp, result, memo, memoForP);
        return result;
    }

    // backtracking
    // 0.
    private void dfs(int Index, String s, List<String> temp, List<List<String>> result, Map<Integer, Boolean> memo,
            Map<String, Boolean> memoForP) {

        // 1.
        if (Index == s.length()) {
            result.add(new ArrayList<String>(temp)); // deep copy
            return;
        }

        // memo
        if (!isValid(Index, s, memo, memoForP)) {
            return;
        }

        // 2.
        for (int i = Index; i < s.length(); i++) {

            String word = s.substring(Index, i + 1);
            if (!isPalindrome(word, memoForP)) {
                continue;
            }

            temp.add(word);
            dfs(i + 1, s, temp, result, memo, memoForP);
            temp.remove(temp.size() - 1);
        }
    }

    private boolean isPalindrome(String word, Map<String, Boolean> memoForP) {

        if (memoForP.containsKey(word)) {
            return memoForP.get(word);
        }

        int left = 0;
        int right = word.length() - 1;

        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                memoForP.put(word, false);
                return false;
            }
            left++;
            right--;
        }

        memoForP.put(word, true);
        return true;
    }

    // memo
    // 0.
    // 此Index開頭的string有沒有構成回文的可能性
    private boolean isValid(int Index, String s, Map<Integer, Boolean> memo, Map<String, Boolean> memoForP) {

        // 1.
        if (Index == s.length()) {
            return true;
        }

        if (memo.containsKey(Index)) {
            return memo.get(Index);
        }

        // 2.
        memo.put(Index, false);
        for (int i = Index; i < s.length(); i++) {

            String word = s.substring(Index, i + 1);
            if (!isPalindrome(word, memoForP)) {
                continue;
            }

            if (isValid(i + 1, s, memo, memoForP)) {
                memo.put(Index, true);
            }
        }

        // 3.
        return memo.get(Index);
    }

}

// method 2
// DFS (DC + memo)
class Solution {

    public List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();
        if (s.length() == 0 || s == null) {
            return result;
        }

        Map<Integer, List<List<String>>> memo = new HashMap<>();
        Map<String, Boolean> memoForP = new HashMap<>();

        result = dfs(0, s, memo, memoForP);
        return result;
    }

    // DC + memo
    // 0.
    // 以Index開頭的 回文結果集
    private List<List<String>> dfs(int Index, String s, Map<Integer, List<List<String>>> memo,
            Map<String, Boolean> memoForP) {

        List<List<String>> result = new ArrayList<>();

        // 1.
        // if (Index == s.length()){
        // result.add(new ArrayList<>());
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
            if (!isPalindrome(word, memoForP)) {
                continue;
            }

            // 取代出口條件
            if ((i + 1) == s.length()) {
                result.add(Arrays.asList(word));
                break;
            }

            // right
            List<List<String>> collections = dfs(i + 1, s, memo, memoForP);

            // combine = left + right
            for (List<String> collection : collections) {

                // 將word以Arrays.asList()變成List後才可以被new ArrayList包覆
                result.add(new ArrayList<>(Arrays.asList(word)));

                result.get(result.size() - 1).addAll(collection);
            }
        }
        memo.put(Index, result);
        System.out.println("memo: " + memo);

        return result;
    }

    private boolean isPalindrome(String word, Map<String, Boolean> memoForP) {

        if (memoForP.containsKey(word)) {
            return memoForP.get(word);
        }

        int left = 0;
        int right = word.length() - 1;

        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                memoForP.put(word, false);
                return false;
            }
            left++;
            right--;
        }

        memoForP.put(word, true);
        return true;
    }

}