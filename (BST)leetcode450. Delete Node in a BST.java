/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    // TC: O(logN)
    // SC: O(logN)
    // 0.
    public TreeNode deleteNode(TreeNode root, int key) {
        
        // 1.
        if (root == null){
            return null;
        }

        // 2.
        if (key < root.val){
            root.left = deleteNode(root.left, key);
        } else if (key > root.val){
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null && root.right == null){
                root = null;
            } else if (root.left != null){
                root.val = predecessor(root); // predecessor抓交替
                root.left = deleteNode(root.left, root.val); // 目標換成root.val
            } else if (root.right != null){
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
        }
        
        return root;
    }

    private int successor(TreeNode root){
        root = root.right;
        while (root.left != null){
            root = root.left;
        }
        return root.val;
    }

    private int predecessor(TreeNode root){
        root = root.left;
        while (root.right != null){
            root = root.right;
        }
        return root.val;
    }

}