package leetcode.invertBinaryTree;
///Blaba
public class Solution {
    public static TreeNode invertTree(TreeNode root){
        if (root == null)
            return null;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    public static void main(String[] args){
        TreeNode root;
        TreeNode l1;
        TreeNode r1;

        TreeNode l2 = new TreeNode(4);
        TreeNode r2 = new TreeNode(5);

        r1 = new TreeNode(3);
        l1 = new TreeNode(2, l2, r2);
        root = new TreeNode(1, l1, r1);
        TreeNode res = invertTree(root);
    }
}