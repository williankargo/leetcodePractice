// TC: O(haystackLength * needleLength)
// SC: O(1)
class Solution {
    public int strStr(String haystack, String needle) {

        int needleIndex = 0;
        for (int i = 0; i < haystack.length() - needle.length() + 1; i += 1) { // optimize iteration length
            needleIndex = 0;
            while (needleIndex < needle.length() &&
                    haystack.charAt(i + needleIndex) == needle.charAt(needleIndex)) {
                if (needleIndex == needle.length() - 1) {
                    return i;
                }
                needleIndex++;
            }
        }

        return -1;
    }
}