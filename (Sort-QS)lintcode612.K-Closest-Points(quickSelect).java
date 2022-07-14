import java.util.Arrays;
import java.util.Comparator;

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}

class Solution {

    // quick select version
    // Time: O(N + klogk) -> quick select average time: N, and then quick sort klogk
    // Space: O(k)
    Point origin;
    public Point[] kClosest(Point[] points, Point origin, int k) {

        if (points == null || points.length == 0 || k == 0) {
            return new Point[0];
        }

        this.origin = origin;
        quickSelect(points, new PointsComparator(), 0, points.length - 1, k - 1);

        Point[] result = Arrays.copyOf(points, k); // java Arrays.copyOf(array, length)
        Arrays.sort(result, new PointsComparator());
        return result;
    }

    // 找到一個 k index，index小於k的array[index]會 <= array[k]，index大於k的array[index]會 >=
    // array[k]
    private Point quickSelect(Point[] points, PointsComparator comp, int start, int end, int k) {

        if (start >= end) {
            return points[k];
        }

        int pivotIndex = (start + (end - start) / 2); // 中間
        Point pivot = points[pivotIndex];
        int left = start, right = end;
        while (left <= right) {

            // 因為points會變化，所以不能傳入points[pivotIndex]，要在一開始就固定pivot
            // comp.compare(points[left], pivot) 可能會有 =0 的情況出現
            while (left <= right && (comp.compare(points[left], pivot) < 0)) { // 抓比pivot's Value大於等於的
                left++;
            }

            while (left <= right && (comp.compare(points[right], pivot) > 0)) { // 抓比pivot's Value小於等於的
                right--;
            }

            // swap
            if (left <= right) {
                Point temp = points[left];
                points[left] = points[right];
                points[right] = temp;
                left++;
                right--;
            }
        }

        // 往左半找k，在pivotIndex右邊的value一定大於array[k]
        if (k <= right) {
            return quickSelect(points, comp, start, right, k);
        }

        // 往右半找k，在pivotIndex左邊的value一定小於array[k]
        if (k >= left) {
            return quickSelect(points, comp, left, end, k);
        }

        return points[k];
    }

    private int getDistance(Point current) {
        return (current.x - origin.x) * (current.x - origin.x) + (current.y - origin.y) * (current.y - origin.y);
    }

    // comparator
    class PointsComparator implements Comparator<Point> {
        public int compare(Point point1, Point point2) {
            if (getDistance(point1) < getDistance(point2)) {
                return -1;
            } else if (getDistance(point1) > getDistance(point2)) {
                return 1;
            } else {
                return point1.x != point2.x ? point1.x - point2.x : point1.y - point2.y;
            }
        }
    }
}