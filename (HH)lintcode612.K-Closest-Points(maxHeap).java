import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

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

    // Time: O(Nlog(k))
    // Space: O(k)
    public Point[] kClosest(Point[] points, Point origin, int k) {

        // generate a max heap
        Queue<Point> queue = new PriorityQueue<Point>(k, new PointComparator(origin));

        for (Point point : points) {
            queue.offer(point);
            if (queue.size() > k) { // 讓queue的大小維持在k
                queue.poll();
            }
        }

        Point[] result = new Point[k];
        int i = k - 1;
        while (!queue.isEmpty()) {
            result[i] = queue.poll();
            i--;
        }

        return result;
    }

    class PointComparator implements Comparator<Point> {
        Point origin;

        PointComparator(Point origin) {
            this.origin = origin;
        }

        public int compare(Point point1, Point point2) {
            int diff = getDistance(point2, origin) - getDistance(point1, origin);
            if (diff == 0) {
                return point2.x - point1.x;
            }
            if (diff == 0) {
                return point2.y - point1.y;
            }
            return diff;
        }
    }

    private int getDistance(Point point, Point origin) {
        return (point.x - origin.x) * (point.x - origin.x) + (point.y - origin.y) * (point.y - origin.y);
    }
}
