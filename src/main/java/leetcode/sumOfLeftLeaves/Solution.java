package leetcode.sumOfLeftLeaves;

public class Solution {
    public static int sumOfLeftLeaves(TreeNode root){
        if (root == null)
            return 0;
        if (root.left != null && root.left.left == null && root.left.right == null)
            return root.left.val + sumOfLeftLeaves(root.right);
        else
            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
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
        int res = sumOfLeftLeaves(root);
    }

}
