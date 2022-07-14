class Solution {

    // Recursion: memo
    // DFS + Memoization
    // Time: O(sLength * pLength * 1) => 想boolean[][]，因為是記憶化搜索，所以時間複雜度為 O(狀態總數 *
    // 計算每個狀態的時間花費)
    // Space: O(sLength * pLength) ， 另外遞歸了 N 層 (看出口條件)
    public boolean isMatch(String s, String p) {

        if (s == null || p == null) {
            return false;
        }

        boolean[][] history = new boolean[s.length()][p.length()];
        boolean[][] visited = new boolean[s.length()][p.length()];

        return dfs(s, 0, p, 0, history, visited);
    }

    // 0. sIndex的s 和 pIndex的p 有沒有match
    private boolean dfs(String s, int sIndex, String p, int pIndex, boolean[][] history, boolean[][] visited) {

        // 3. exit
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }

        if (sIndex == s.length()) {
            return REMatch(p, pIndex);
        }

        // memoization
        if (visited[sIndex][pIndex]) {
            return history[sIndex][pIndex];
        }

        // 1. decide
        char charS = s.charAt(sIndex);
        char charP = p.charAt(pIndex);
        boolean match;

        // a*
        // 當a後跟著一個*時，必須當成一個整體來匹配
        if ((pIndex + 1) < p.length() && p.charAt(pIndex + 1) == '*') {
            // a* 為空 || a* 為 aa 或 aaa... (當前要可以合格)
            match = dfs(s, sIndex, p, pIndex + 2, history, visited) ||
                    isMatch(charS, charP) && dfs(s, sIndex + 1, p, pIndex, history, visited);
        } else { // 一般狀況
            match = isMatch(charS, charP) && dfs(s, sIndex + 1, p, pIndex + 1, history, visited);
        }

        // 2. return
        visited[sIndex][pIndex] = true;
        history[sIndex][pIndex] = match;

        return match;
    }

    // 必須是 a*b*c* 才可能為空，pIndex進來時不會落在*上
    private boolean REMatch(String p, int pIndex) {
        for (int i = pIndex; i < p.length(); i += 2) {
            // (i + 1) >= p.length() ==> ex: a*a*a*a(v)
            if ((i + 1) >= p.length() || p.charAt(i + 1) != '*') {
                return false;
            }
        }
        return true;
    }

    private boolean isMatch(char charS, char charP) {
        return (charS == charP || charP == '.');
    }

}