class Solution {

    // TP: O(M+N), SP: O(M+N)
    public String mergeAlternately(String word1, String word2) {
        int length1 = word1.length(), length2 = word2.length(), index1 = 0, index2 = 0;
        StringBuilder sb = new StringBuilder();

        while (index1 < length1 || index2 < length2) {
            if (index1 < length1) {
                sb.append(word1.charAt(index1++));
            }
            if (index2 < length2) {
                sb.append(word2.charAt(index2++));
            }
        }

        return sb.toString();
    }
}