// Solution 1: Trie + DFS
// TC: too complicated to say
// SC: O(M), M is the node numbers of Trie

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution1 {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        // build a trie
        Trie trie = new Trie();
        for (String product : products) {
            trie.insert(product);
        }

        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            List<String> list = trie.startWith(searchWord.substring(0, i + 1));
            ans.add(list);
        }

        return ans;
    }
}

class TrieNode {
    Map<Character, TrieNode> children;
    Boolean endOfWord;

    TrieNode() {
        this.children = new HashMap<>();
        this.endOfWord = false;
    }
}

class Trie {

    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                curr.children.put(ch, new TrieNode());
            }
            curr = curr.children.get(ch);
        }
        curr.endOfWord = true;
    }

    // startWith
    public List<String> startWith(String word) {

        List<String> list = new ArrayList<>();
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                return list;
            }
            curr = curr.children.get(ch);
        }

        dfs(curr, word, list);
        return list;
    }

    private void dfs(TrieNode curr, String temp, List<String> list) {
        if (list.size() == 3) {
            return;
        }
        if (curr.endOfWord) {
            list.add(temp);
        }
        for (char i = 'a'; i <= 'z'; i++) {
            if (curr.children.get(i) != null) {
                dfs(curr.children.get(i), temp + i, list);
            }
        }
    }
}

// solution 2: 2 pointer
// solution: https://www.youtube.com/watch?v=D4T2N0yAr20&ab_channel=NeetCode
// TC: O(NlogN + M + NW), N is the length of products, M is the length of
// searchWord, W is the length of every single word
// SC: O(M*3)
class Solution2 {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        List<List<String>> result = new ArrayList<>();
        Arrays.sort(products);

        int l = 0, r = products.length - 1;
        for (int i = 0; i < searchWord.length(); i++) {
            char ch = searchWord.charAt(i);
            // 先把判斷長度的放前面，就可以避免charAt(i)讀到超範圍的東西報錯
            while (l <= r && (products[l].length() <= i || products[l].charAt(i) != ch)) {
                l++;
            }

            while (l <= r && (products[r].length() <= i || products[r].charAt(i) != ch)) {
                r--;
            }

            List<String> temp = new ArrayList<>();
            int length = Math.min(3, r - l + 1);
            for (int j = 0; j < length; j++) {
                temp.add(products[l + j]);
            }
            result.add(temp);
        }

        return result;
    }
}