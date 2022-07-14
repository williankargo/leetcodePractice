import java.util.Comparator;
import java.util.PriorityQueue;

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

    // Time: O(logN)
    // Space: O(N)
    public Point[] kClosest(Point[] points, Point origin, int k) {

        // genetate a minHeap
        PriorityQueue<Point> queue = new PriorityQueue<Point>(new PointsComparator(origin));

        for (Point point : points) {
            queue.offer(point);
        }

        Point[] result = new Point[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }

        return result;
    }

    class PointsComparator implements Comparator<Point> {

        Point origin;

        PointsComparator(Point origin) {
            this.origin = origin;
        }

        // 目前 point1(前) -> point2(後)，結果必須為升序
        // point1 - point2
        // return < 0，代表前後『符合升序』
        // return > 0，代表前後『不符合升序』，要前後對調才能符合升序
        // 所以如果要降序，就要在『符合升序 return<0』時告訴判斷器『不符合升序 return>0』，以point2 - point1實現
        public int compare(Point point1, Point point2) { // compare是override，是public層級記得加上
            int diff = getDistance(point1, origin) - getDistance(point2, origin);
            if (diff == 0) {
                diff = point1.x - point2.x;
            }
            if (diff == 0) {
                diff = point1.y - point2.y;
            }
            return diff;
        }
    }

    private int getDistance(Point point, Point origin) {
        return (point.x - origin.x) * (point.x - origin.x) + (point.y - origin.y) * (point.y - origin.y);
    }
}