import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

    // TC: O(N + N)
    // SC: O(N)
    // 這題使用特殊解法！！
    public String predictPartyVictory(String senate) {

        Queue<Integer> queue_r = new ArrayDeque<>();
        Queue<Integer> queue_d = new ArrayDeque<>();

        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') {
                queue_r.offer(i);
            } else {
                queue_d.offer(i);
            }
        }

        while (!queue_r.isEmpty() && !queue_d.isEmpty()) {
            int r_index = queue_r.poll();
            int d_index = queue_d.poll();
            if (r_index < d_index) {
                queue_r.offer(r_index + senate.length());
            } else {
                queue_d.offer(d_index + senate.length());
            }
        }

        return queue_r.size() > queue_d.size() ? "Radiant" : "Dire";
    }
}