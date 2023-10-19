class Solution {

    // I: 1
    // V: 5
    // X: 10
    // L: 50
    // C: 100
    // D: 500
    // M: 1000
    // 注意constraints可以減少代碼量！
    public static final String[] thousands = new String[] { "", "M", "MM", "MMM" };
    public static final String[] hundreds = new String[] { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
    public static final String[] tens = new String[] { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
    public static final String[] ones = new String[] { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

    public String intToRoman(int num) {

        return thousands[num / 1000] + hundreds[(num % 1000) / 100] + tens[(num % 100) / 10] + ones[(num % 10)];
    }

}

// Solution 2
// solution: https://www.youtube.com/watch?v=ohBNdSJyLh8&ab_channel=NeetCode
// TC: O(N)
// SC: O(1)
class Solution2 {
    public String intToRoman(int num) {

        int[] number = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbol = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        String ans = "";
        for (int i = 0; i < number.length; i++){
            int val = num / number[i];
            if (val == 0){
                continue;
            }
            ans += symbol[i].repeat(val);
            num %= number[i];
        }

        return ans;
    }
}