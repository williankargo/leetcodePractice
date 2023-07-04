// TC: O(N)
// SC: O(N)
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)){
            return "";
        }
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    // 歐幾里德算法求最大公因數
    private int gcd(int a, int b){ // 被除數, 除數
        return b == 0 ? a : gcd(b, a % b); // 每一輪要除數、被除數交換
    }
}