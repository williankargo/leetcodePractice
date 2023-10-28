// solution: https://www.youtube.com/watch?v=w6LcypDgC4w&ab_channel=NeetCode
// TC: O(N)
// SC: O(N)

import java.util.Stack;

class Solution {

    class Node {
        Character c;
        int count;

        Node(Character c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    public String removeDuplicates(String s, int k) {

        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() || stack.peek().c != s.charAt(i)) {
                stack.push(new Node(s.charAt(i), 1));
            } else {
                Node prevNode = stack.pop();
                prevNode.count++;
                if (prevNode.count != k) {
                    stack.push(prevNode);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Node node : stack) { // 在java stack中使用for loop，會從底部非頂部開始iterate!
            System.out.println(node.c);
            for (int i = 0; i < node.count; i++) {
                sb.append(node.c);
            }
        }

        return sb.toString();
    }
}