import java.util.HashMap;
import java.util.Map;

class Solution {

    // Recursion: BackTracking
    public boolean wordPatternMatch(String pattern, String str) {

        if (pattern == null || str == null) {
            return false;
        }

        Map<Character, String> Dmap = new HashMap<>();
        return dfs(pattern, 0, str, 0, Dmap);
    }

    // 0. definition
    // 此pattern和此str可不可以match上
    private boolean dfs(String pattern, int pIndex, String str, int sIndex, Map<Character, String> Dmap) {

        // 3. exit
        if (pIndex == pattern.length() && sIndex == str.length()) {
            return true;
        }
        if (pIndex == pattern.length() || sIndex == str.length()) {
            return false;
        }

        Character currentChar = pattern.charAt(pIndex);

        if (Dmap.containsKey(currentChar)) {
            String s = Dmap.get(currentChar);
            if (!str.substring(sIndex).startsWith(s)) {
                return false;
            } else {
                // 往下一個pattern, str 對找
                return dfs(pattern, pIndex + 1, str, sIndex + s.length(), Dmap);
            }
        }

        // 1. decide
        for (int i = sIndex; i < str.length(); i++) {

            String part = str.substring(sIndex, i + 1);
            if (Dmap.containsValue(part)) {
                continue;
            }

            Dmap.put(currentChar, part);

            if (dfs(pattern, pIndex + 1, str, i + 1, Dmap)) {
                // 2. return
                return true;
            }

            Dmap.remove(currentChar);
        }

        // 2. return
        return false;
    }
}