import java.util.Stack;
import java.util.TreeMap;

class MinStack {

    Stack<Integer> stack;
    TreeMap<Integer, Integer> valueToCount;

    public MinStack() {
        stack = new Stack<>();
        valueToCount = new TreeMap<>();
    }

    public void push(int val) {
        stack.push(val);
        valueToCount.put(val, valueToCount.getOrDefault(val, 0) + 1);
    }

    public void pop() {
        Integer item = stack.pop();
        valueToCount.put(item, valueToCount.get(item) - 1);
        if (valueToCount.get(item) == 0) {
            valueToCount.remove(item);
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return valueToCount.firstKey();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */