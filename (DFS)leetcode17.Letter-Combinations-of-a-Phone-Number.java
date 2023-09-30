import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// version 1
class Solution1 {

    public static final String[] KEYBOARD = {
            "", // 0
            "", // 1
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    // Recursion - BT
    // combination
    // Time: O(4^n)
    // Space: O(4^n)
    public List<String> letterCombinations(String digits) {

        List<String> combinations = new ArrayList<>();
        if (digits == null || digits == "") {
            return combinations;
        }

        dfs(0, "", combinations, digits);

        return combinations;
    }

    private void dfs(int index, String combination, List<String> combinations, String digits) {

        // exit
        if (index == digits.length()) {
            combinations.add(combination);
            return;
        }

        int number = digits.charAt(index) - '0'; // '2' - '0' = 2

        // 拆解
        for (int i = 0; i < KEYBOARD[number].length(); i++) {
            dfs(index + 1, combination + KEYBOARD[number].charAt(i), combinations, digits); // 如果用List就要 add remove了
        }
    }

}

// version 2
class Solution2 {

    // Map直接初始化
    public Map<Character, String> graph = Map.of(
            '0', "", '1', "", '2', "abc", '3', "def", '4', "ghi", '5', "jkl", '6', "mno",
            '7', "pqrs", '8', "tuv", '9', "wxyz");

    public List<String> letterCombinations(String digits) {

        List<String> list = new ArrayList<>();
        if (digits.length() == 0) {
            return list;
        }
        dfs(0, digits, new StringBuilder(), list);

        return list;
    }

    private void dfs(int index, String digits, StringBuilder sb, List<String> list) {

        if (index == digits.length()) {
            list.add(sb.toString());
            return;
        }

        Character num = digits.charAt(index);

        for (int i = 0; i < graph.get(num).length(); i++) {
            sb.append(graph.get(num).charAt(i));
            dfs(index + 1, digits, sb, list);
            sb.deleteCharAt(sb.length() - 1);
        }

    }
}