import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

/*
 * @lc app=leetcode id=127 lang=java
 *
 * [127] Word Ladder
 */

// @lc code=start
class Solution {

    // Time O(26L^2)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Queue<String> queue = new ArrayDeque<>(); // ArrayDeque連續空間 LinkedList散落空間
        HashSet<String> set = new HashSet<>();

        int initlength = 1;
        queue.offer(beginWord);
        set.add(beginWord); // set要緊跟著queue.offer(...) 加入set代表遍歷過
        while (!queue.isEmpty()) { // queue: isEmpty()

            int size = queue.size();
            initlength++;
            for (int i = 0; i < size; i++) { // 這題必須加上『分層』條件，不然initlength會重複加到無法『可以走下去的』，想從上往下的tree圖片
                String word = queue.poll(); // queue: offer(...) poll()
                for (String son : getNeighbors(word, wordList)) {

                    if (set.contains(son)) {
                        continue;
                    }

                    if (son.equals(endWord)) {
                        return initlength;
                    }

                    queue.offer(son);
                    set.add(son);
                }
            }
        }
        return 0;
    }

    // hit(1) -> hot(2) -> dot(3) -> dog(4)(無法走下去了，如果沒有分層，在poll queue時就會重複算到這個的高度)
    // -> lot(3) -> log(4) -> cog(5)

    // return neighbors...
    private List<String> getNeighbors(String word, List<String> wordList) {
        List<String> neighbors = new ArrayList<String>();

        for (char c = 'a'; c <= 'z'; c++) { // 26
            int length = word.length();
            for (int i = 0; i < length; i++) { // L
                if (word.charAt(i) == c) {
                    continue;
                }
                String newWord = replace(word, i, c); // L
                if (wordList.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
        }
        return neighbors;
    }

    // replace word
    private String replace(String word, int index, char c) {
        char[] chars = word.toCharArray(); // turn String to char[] // L
        chars[index] = c;
        return new String(chars); // turn char[] to String
    }

}
// @lc code=end
