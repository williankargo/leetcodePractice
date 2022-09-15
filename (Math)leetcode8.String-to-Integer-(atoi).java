class Solution {

    // Time: O(N)
    // Space: O(N)
    public int myAtoi(String s) {

        if (s.length() == 0) {
            return 0;
        }

        int length = s.length();
        char[] charArray = s.toCharArray();
        int PM = 1;
        long sum = 0;
        boolean NotReadDigit = true;
        for (int i = 0; i < length; i++) {
            char word = charArray[i];
            if (' ' == word && NotReadDigit) {
                continue;
            } else if (('-' == word || '+' == word) && NotReadDigit) {
                if ('-' == word) {
                    PM = -1;
                }
                NotReadDigit = false;
            } else if (Character.isDigit(word)) {
                sum = sum * 10 + (word - '0');
                if (PM * sum > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (PM * sum < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
                NotReadDigit = false;
            } else {
                break;
            }
        }
        sum = PM * sum;

        return (int) sum;
    }
}