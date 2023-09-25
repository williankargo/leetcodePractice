
// method 1: DFS

import java.util.ArrayDeque;
import java.util.Queue;

class Solution1 {

    // TC: O(N^2)
    // SC: O(N)
    public int findCircleNum(int[][] isConnected) {

        int length = isConnected.length;
        boolean[] isVisited = new boolean[length];
        int Num = 0;
        for (int i = 0; i < length; i++) {
            if (!isVisited[i]) {
                Num++;
                dfs(i, isConnected, isVisited);
            }
        }

        return Num;
    }

    private void dfs(int index, int[][] isConnected, boolean[] isVisited) {
        isVisited[index] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[index][i] == 1 && !isVisited[i]) {
                dfs(i, isConnected, isVisited);
            }
        }
    }
}

// method 2: BFS
class Solution2 {

    // TC: O(N^2)
    // SC: O(N)
    public int findCircleNum(int[][] isConnected) {

        int Num = 0;
        int length = isConnected.length;
        boolean[] isVisited = new boolean[length];

        for (int i = 0; i < length; i++) {
            if (!isVisited[i]) {
                Num++;
                bfs(i, isConnected, isVisited);
            }
        }

        return Num;
    }

    private void bfs(int index, int[][] isConnected, boolean[] isVisited) {

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(index);
        isVisited[index] = true;

        while (!queue.isEmpty()) {
            int target = queue.poll();
            for (int i = 0; i < isConnected.length; i++) {
                if (isConnected[target][i] == 1 && !isVisited[i]) {
                    queue.offer(i);
                    isVisited[i] = true;
                }
            }
        }
    }
}

// method 3: Union Find
class Solution3 {

    // TC: O(N^2)
    // SC: O(N)
    public int findCircleNum(int[][] isConnected) {
        
        int length = isConnected.length;
        int count = length;
        UnionFind uf = new UnionFind(length);

        for (int i = 0; i < length; i++){
            for (int j = i + 1; j < length; j++){
                if (isConnected[i][j] == 1 && uf.find(i) != uf.find(j)){
                    count--;
                    uf.merge(i, j);
                }
            }
        }
        return count;
    }
}

class UnionFind {

    Map<Integer, Integer> father;

    UnionFind(int length){
        father = new HashMap<>();
        for (int i = 0; i < length; i++){
            father.put(i, null); // default: dad is null
        }
    }

    public int find(int node){

        // find the deepest root
        int root = node;
        while (father.get(root) != null){
            root = father.get(root);
        }

        // tear down tree
        while (node != root){
            int dad = father.get(node);
            father.put(node, root);
            node = dad;
        }

        return root;
    }

    public void merge(int node1, int node2){
        
        if (find(node1) != find(node2)){
            father.put(find(node1), find(node2));
        }
    }
}