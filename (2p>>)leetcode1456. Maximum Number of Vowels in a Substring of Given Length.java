class Solution {

    // TC: O(N)
    // SC: O(1)
    public int maxVowels(String s, int k) {

        if (s.length() == 0 || k == 0) {
            return 0;
        }

        // 0.
        int j = 0;

        // 1.
        int maxCount = 0;
        int count = 0;

        // 2.
        for (int i = 0; i < s.length(); i++) {

            // 3.
            while (j < s.length() && (j - i < k)) {
                if (s.charAt(j) == 'a' ||
                        s.charAt(j) == 'e' ||
                        s.charAt(j) == 'i' ||
                        s.charAt(j) == 'o' ||
                        s.charAt(j) == 'u') {
                    count++;
                }
                j++;
            }

            // 4.
            maxCount = Math.max(count, maxCount);
            if (j >= s.length()) {
                return maxCount;
            }

            // 5.
            if (s.charAt(i) == 'a' ||
                    s.charAt(i) == 'e' ||
                    s.charAt(i) == 'i' ||
                    s.charAt(i) == 'o' ||
                    s.charAt(i) == 'u') {
                count--;
            }
        }

        return maxCount;
    }
}