import java.util.ArrayList;
import java.util.List;

class Solution1 {

    // Time: O(26 * sLength + pLength)
    // Space: O(26 * 2)
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();
        if (s.length() == 0 || s == null || p.length() == 0 || p == null) {
            return result;
        }

        // 統計26個字母
        int[] dict_s = new int[26];
        int[] dict_p = new int[26];

        // 統計p
        for (int i = 0; i < p.length(); i++) {
            dict_p[p.charAt(i) - 'a'] += 1;
        }

        for (int i = 0, j = 0; i < s.length() - (p.length() - 1); i++) {

            while (j < s.length() && (j - i) < p.length()) {
                dict_s[s.charAt(j) - 'a'] += 1;
                j++;
            }

            if (j < s.length() && (j - i) >= p.length()) {
                if (check(dict_s, dict_p)) {
                    result.add(i);
                }
            } else {
                if (check(dict_s, dict_p)) {
                    result.add(i);
                    return result;
                }
            }

            // 向前滑動
            dict_s[s.charAt(i) - 'a'] -= 1;
        }

        return result;
    }

    private boolean check(int[] dict_s, int[] dict_p) {

        for (int i = 0; i < 26; i++) {
            if (dict_s[i] != dict_p[i]) {
                return false;
            }
        }

        return true;
    }
}

// 優化版本：以種類判斷
class Solution2 {

    // Time: O(SLength + PLength)
    // Space: O(26)
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();
        if (s.length() == 0 || s == null || p.length() == 0 || p == null) {
            return result;
        }

        // 統計p
        int[] dict_p = new int[26];
        int genreCountP = 0;
        for (int i = 0; i < p.length(); i++) {
            dict_p[p.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < 26; i++) {
            if (dict_p[i] != 0) {
                genreCountP++;
            }
        }

        int genreCountS = 0;
        for (int i = 0, j = 0; i < s.length() - (p.length() - 1); i++) {

            while (j < s.length() && (j - i) < p.length()) {
                if (--dict_p[s.charAt(j) - 'a'] == 0) { // 統計當前窗口的種類個數 注意！--i == 0 => i = i - 1, i == 0 // i-- == 0 => i
                                                        // == 0, i--; 不一樣！
                    genreCountS++;
                }
                j++;
            }

            if (j < s.length() && (j - i) >= p.length()) {
                if (genreCountS == genreCountP) {
                    result.add(i);
                }
            } else {
                if (genreCountS == genreCountP) {
                    result.add(i);
                    return result;
                }
            }

            // 向前滑動
            if (++dict_p[s.charAt(i) - 'a'] == 1) {
                genreCountS--;
            }

        }

        return result;
    }

}