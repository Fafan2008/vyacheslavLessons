package leetcode.trees.findBottomLeftTreeValue;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    // Мое решение с использованием алгоритма в ширину.
    public static int findBottomLeftValueBFS(TreeNode root) {
        int result = root.val;
        int elementsCounter = 0;
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.offer(root);
        while(!stack.isEmpty()){
            elementsCounter = stack.size();
            result = stack.peek().val;
            while(elementsCounter!=0){
                root = stack.pollFirst();
                if(root.left!=null)
                    stack.offer(root.left);
                if(root.right!=null)
                    stack.offer(root.right);
                elementsCounter--;
            }
        }
        return result;
    }

    // Второе решение в глубину с использованием рекурсии (это не ое решение)
    int currentDepth;
    int value;
    public int findBottomLeftValueDFS(TreeNode root) {
        return findBottomLeftValueDFS(root, 0);
    }
    public int findBottomLeftValueDFS(TreeNode root, Integer newDepth) {
        if (currentDepth<newDepth) {
            this.value = root.val;
            currentDepth = newDepth;}
        if (root.left!=null) findBottomLeftValueDFS(root.left, newDepth+1);
        if (root.right!=null) findBottomLeftValueDFS(root.right, newDepth+1);
        return value;
    }

    public static void main(String[] args) {
        int i = 1;
        {
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            int res = findBottomLeftValueBFS(root);
            System.out.println("Result of test " + i + " is: " + res);
            i++;
        }
        {
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            Solution obj = new Solution();
            int res = obj.findBottomLeftValueDFS(root);
            System.out.println("Result of test " + i + " is: " + res);
            i++;
        }
    }
}
