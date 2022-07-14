import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    // https://www.jiuzhang.com/problem/merge-k-sorted-arrays/
    // Time: O(NlogK) --> 每次在pq中拿出東西logK，拿了Ｎ次
    // Space: O(N + k)
    public int[] mergeSortedArrays(int[][] arrays) {

        if (arrays.length == 0 || arrays == null) {
            return new int[0];
        }

        // 先把每個array的第一個東西放到queue中
        Queue<Node> queue = new PriorityQueue<>(arrays.length, new NodeComparator());
        int row = 0;
        int totalSize = 0;
        for (int[] array : arrays) {
            queue.offer(new Node(row++, 0, array[0]));
            totalSize += array.length;
        }

        // 基於merge sort -- merge的思想來merge，同時用一個minHeap來幫助快速找到array的最小值
        int[] result = new int[totalSize];
        int index = 0;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            result[index++] = current.value;
            if (current.column < arrays[current.row].length - 1) {
                queue.offer(new Node(current.row, current.column + 1, arrays[current.row][current.column]));
            }
        }

        return result;
    }

    class Node {
        int row, column, value;

        Node(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }

    class NodeComparator implements Comparator<Node> {
        public int compare(Node node1, Node node2) {
            return node1.value - node2.value;
        }
    }

}