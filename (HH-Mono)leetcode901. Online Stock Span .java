// TC: O(N)
// SC: O(N)

import java.util.Stack;

class StockSpanner {

    Stack<Point> stack;

    public StockSpanner() {
        this.stack = new Stack<>();
    }

    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && price >= stack.peek().value) {
            Point prev = stack.pop();
            span += prev.prevSpan;
        }
        stack.push(new Point(span, price));

        return span;
    }
}

class Point {
    int prevSpan;
    int value;

    Point(int prevSpan, int value) {
        this.prevSpan = prevSpan;
        this.value = value;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */