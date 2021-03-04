package leetcode.invertBinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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
    public static TreeNode invertTree2(TreeNode root){
        if (root == null)
            return null;
        TreeNode newTree = new TreeNode();
        Deque<TreeNode> list = new ArrayDeque<>();
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode current = list.poll();
            if(current!=null) {
                TreeNode tmp = current.left;
                current.left = current.right;
                current.right = tmp;
                if(current.left != null)
                    list.add(current.left);
                if(current.right != null)
                    list.add(current.right);
            }
        }
        return root;
    }
    public static void main(String[] args){
        TreeNode root1;
        TreeNode l1;
        TreeNode r1;

        TreeNode l2 = new TreeNode(4);
        TreeNode r2 = new TreeNode(5);

        r1 = new TreeNode(3);
        l1 = new TreeNode(2, l2, r2);
        root1 = new TreeNode(1, l1, r1);
        TreeNode res1 = invertTree(root1);

        TreeNode root2;
        TreeNode l11;
        TreeNode r11;

        TreeNode l22 = new TreeNode(4);
        TreeNode r22 = new TreeNode(5);

        r11 = new TreeNode(3);
        l11 = new TreeNode(2, l22, r22);
        root2 = new TreeNode(1, l11, r11);
        TreeNode res2 = invertTree2(root2);

        TreeNode root3;
        TreeNode l111 = null;
        TreeNode r111 = new TreeNode(2);
        root3 = new TreeNode(1, l111, r111);
        TreeNode res3 = invertTree2(root3);
    }
}