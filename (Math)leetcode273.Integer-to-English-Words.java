// Time: O(N)
// Space: O(1)
class Solution {

    // 1 - 9
    private String one(int num) {
        switch (num) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
        }
        return "";
    }

    // 10 - 19
    private String lessThanTwenty(int num) {
        switch (num) {
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
        }
        return "";
    }

    // 20 30 ... 90
    private String ten(int num) {
        switch (num) {
            case 2:
                return "Twenty";
            case 3:
                return "Thirty";
            case 4:
                return "Forty";
            case 5:
                return "Fifty";
            case 6:
                return "Sixty";
            case 7:
                return "Seventy";
            case 8:
                return "Eighty";
            case 9:
                return "Ninety";
        }
        return "";
    }

    private String threeDigit(int num) {

        int hundred = num / 100;
        int rest = num - hundred * 100;
        String result = "";
        if (hundred != 0 && rest != 0) {
            return one(hundred) + " Hundred " + twoDigit(rest);
        } else if (hundred != 0 && rest == 0) {
            return one(hundred) + " Hundred";
        } else if (hundred == 0 && rest != 0) {
            return twoDigit(rest);
        }
        return "";
    }

    private String twoDigit(int num) {

        // special case
        if (10 <= num && num < 20) {
            return lessThanTwenty(num);
        }

        int ten = num / 10;
        int rest = num - ten * 10;
        if (ten != 0 && rest != 0) {
            return ten(ten) + " " + one(rest);
        } else if (ten != 0 && rest == 0) {
            return ten(ten);
        } else if (ten == 0 && rest != 0) {
            return one(num);
        }
        return "";
    }

    // range:0 - 2 000 000 000+
    public String numberToWords(int num) {

        if (num == 0) {
            return "Zero";
        }

        int billion = (num / 1000000000);
        int million = (num - billion * 1000000000) / 1000000;
        int thousand = (num - billion * 1000000000 - million * 1000000) / 1000;
        int hundred = (num - billion * 1000000000 - million * 1000000 - thousand * 1000) / 100;
        int rest = num - billion * 1000000000 - million * 1000000 - thousand * 1000 - hundred * 100;

        String ans = "";
        if (billion != 0) {
            ans += threeDigit(billion) + " Billion";
        }
        if (million != 0) {
            if (ans != "") {
                ans += " ";
            }
            ans += threeDigit(million) + " Million";
        }
        if (thousand != 0) {
            if (ans != "") {
                ans += " ";
            }
            ans += threeDigit(thousand) + " Thousand";
        }
        if (hundred != 0) {
            if (ans != "") {
                ans += " ";
            }
            ans += one(hundred) + " Hundred";
        }
        if (rest != 0) {
            if (ans != "") {
                ans += " ";
            }
            ans += twoDigit(rest);
        }

        return ans;
    }
}