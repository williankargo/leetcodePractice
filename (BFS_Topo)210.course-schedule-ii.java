import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
 * @lc app=leetcode id=210 lang=java
 *
 * [210] Course Schedule II
 */

// @lc code=start
class Solution {

    // 拓樸排序
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // List型態的用get()來取值
        // Array [] 型態的用[index]來取值

        // 構建圖
        List[] graph = new ArrayList[numCourses]; // List[] => 把List當成一個屬性，想 int[]
        // 0. 圖的初始化
        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }

        // 1. 統計 indegree，並構建圖
        int[] indegree = new int[numCourses];
        for (int[] order : prerequisites) {
            indegree[order[0]]++;
            graph[order[1]].add(order[0]);
        }
        
        // 2. 將每個indegree=0的點放入到queue
        Queue queue = new ArrayDeque();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        // 3. 不斷的從queue中拿出一個點，去掉該點射出到的點的入度
        int takenCourses = 0;
        int[] topo = new int[numCourses];
        while (!queue.isEmpty()) {
            int currentPoint = (int) queue.poll();
            topo[takenCourses] = currentPoint;
            takenCourses++;
            for (int i = 0; i < graph[currentPoint].size(); i++) {
                int neighbor = (int) graph[currentPoint].get(i);
                indegree[neighbor]--;
                // 發現新的indegree=0的點，丟回queue中
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        return takenCourses == numCourses ? topo : new int[0]; // new int[0] => []
    }
}
// @lc code=end

