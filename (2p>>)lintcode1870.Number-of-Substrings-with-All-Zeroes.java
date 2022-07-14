class Solution {

    // 同向雙指針
    // Time: O(N)
    // Space: O(1)
    public int stringCount(String str) {

        if (str == null) {
            return 0;
        }

        int j = 1;
        int answer = 0;
        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) != '0') {
                continue;
            }

            j = Math.max(i + 1, j);
            while (j < str.length() && str.charAt(j) == '0') {
                j++;
            }

            answer = answer + (j - i);
        }

        return answer;
    }
}