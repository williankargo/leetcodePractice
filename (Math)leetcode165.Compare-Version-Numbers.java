class Solution {

    // Time: O(m + n + max(m, n))
    // Space: O(max(m, n))
    public int compareVersion(String version1, String version2) {

        String[] nums1 = version1.split("\\."); // split(要放reg)
        String[] nums2 = version2.split("\\.");
        int length1 = nums1.length;
        int length2 = nums2.length;

        for (int i = 0; i < Math.max(length1, length2); i++) {
            int number1 = i < length1 ? Integer.parseInt(nums1[i]) : 0;
            int number2 = i < length2 ? Integer.parseInt(nums2[i]) : 0;
            if (number1 != number2) {
                return number1 > number2 ? 1 : -1;
            }
        }

        return 0;
    }
}