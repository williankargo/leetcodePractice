import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    // minHeap version
    // https://www.jiuzhang.com/problem/kth-smallest-number-in-sorted-matrix/
    // Time: O(klog(row number of matrix))
    // Space: O(row number of matrix)
    public int kthSmallest(int[][] matrix, int k) {

        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        Queue<Node> pq = new PriorityQueue<>(matrix.length,
                new Comparator<Node>() {
                    public int compare(Node node1, Node node2) {
                        return node1.value - node2.value;
                    }
                });

        // 先把每個row的第一個元素塞到pq中
        int row = 0;
        for (int[] rowItem : matrix) {
            pq.offer(new Node(row++, 0, rowItem[0]));
        }

        // 每次把pq中最小的元素pop()出來，然後offer pq [被pop出來的那個元素的後column + 1的元素]
        Node minOfCurrentPQ = new Node(0, 0, 0);
        while (k > 0) {
            minOfCurrentPQ = pq.poll();
            if (minOfCurrentPQ.column + 1 < matrix[0].length) {
                pq.offer(
                        new Node(
                                minOfCurrentPQ.row,
                                minOfCurrentPQ.column + 1,
                                matrix[minOfCurrentPQ.row][minOfCurrentPQ.column + 1]));
            }

            k--;
        }

        return minOfCurrentPQ.value;
    }

    class Node {
        int row, column, value;

        Node(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }
}
