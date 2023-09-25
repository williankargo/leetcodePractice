import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

class Solution {

    // TC: O(N)
    // SC: O(N)
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        boolean[] isVisited = new boolean[rooms.size()];
        Queue<Integer> queue = new ArrayDeque<>();

        isVisited[0] = true;
        queue.offer(0);

        while (!queue.isEmpty()) {
            int room = queue.poll();
            List<Integer> list = rooms.get(room);
            for (int i : list) {
                if (!isVisited[i]) {
                    queue.offer(i);
                    isVisited[i] = true;
                }
            }
        }

        for (boolean visit : isVisited) {
            if (!visit) {
                return false;
            }
        }

        return true;
    }
}