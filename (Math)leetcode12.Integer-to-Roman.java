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