class Solution {

    // Recursion: memo
    // DFS + Memoization
    // Time: O(sLength * pLength * 1) => 想boolean[][]，因為是記憶化搜索，所以時間複雜度為 O(狀態總數 *
    // 計算每個狀態的時間花費)
    // Space: O(sLength * pLength) ， 另外遞歸了 N 層 (看出口條件)
    public boolean isMatch(String s, String p) {

        // corner case
        if (s == null || p == null) {
            return false;
        }

        // memoization
        boolean[][] visited = new boolean[s.length()][p.length()];
        boolean[][] history = new boolean[s.length()][p.length()];

        // dfs
        return dfs(s, 0, p, 0, visited, history);
    }

    // 0. definition: s & p match or not
    private boolean dfs(String s, int sIndex, String p, int pIndex, boolean[][] visited, boolean[][] history) {

        // 3. exit
        // 如果p走完了
        if (pIndex == p.length()) {
            // s也必須跟著走完
            return sIndex == s.length();
        }

        // 如果s走完了
        if (sIndex == s.length()) {
            // p必須是全部星星
            // p must be empty too
            return AllStars(p, pIndex);
        }

        // memoization
        if (visited[sIndex][pIndex]) {
            return history[sIndex][pIndex];
        }

        // 1. decide
        boolean match;
        char sChar = s.charAt(sIndex);
        char pChar = p.charAt(pIndex);

        if (pChar != '*') {

            match = isMatch(sChar, pChar) && dfs(s, sIndex + 1, p, pIndex + 1, visited, history);

        } else { // (p.charAt(pIndex) == '*')

            // 二分法
            // 消滅空 或 消滅一個s
            match = dfs(s, sIndex, p, pIndex + 1, visited, history) || dfs(s, sIndex + 1, p, pIndex, visited, history);

        }

        // 2. return
        visited[sIndex][pIndex] = true;
        history[sIndex][pIndex] = match;
        return match;
    }

    private boolean isMatch(char charS, char charP) {
        return (charS == charP || charP == '?');
    }

    private boolean AllStars(String p, int pIndex) {

        for (int i = pIndex; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

}