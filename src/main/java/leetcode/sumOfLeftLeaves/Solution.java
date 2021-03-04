package leetcode.sumOfLeftLeaves;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public static int sumOfLeftLeaves(TreeNode root){
        if (root == null)
            return 0;
        if (root.left != null && root.left.left == null && root.left.right == null)
            return root.left.val + sumOfLeftLeaves(root.right);
        else
            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
    //Важно! Другой способ решения. Жрет много памяти. Что можно сделать?
    public static int sumOfLeftLeaves2(TreeNode root){
        int sum = 0;
        List<TreeNode> stack = new ArrayList<TreeNode>();
        if (root != null)
            stack.add(root);
        while (!stack.isEmpty())
        {
            TreeNode currentTree = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            if (currentTree.left!=null && currentTree.left.left == null && currentTree.left.right == null) {
                sum += currentTree.left.val;
            }
            if(currentTree.left != null)
                stack.add(currentTree.left);
            if(currentTree.right != null)
                stack.add(currentTree.right);
        }
        return sum;
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
        int res1 = sumOfLeftLeaves(root);
        int res2 = sumOfLeftLeaves2(root);
    }
}
