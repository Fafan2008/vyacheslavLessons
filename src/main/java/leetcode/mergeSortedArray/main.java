package leetcode.mergeSortedArray;

public class main {
    public static void main(String[] args)
    {
        Solution sol = new Solution();
        int num1[] = new int[]{2, 0};
        int num2[] = new int[]{1};
        sol.merge(num1, 1, num2,1);
        for (int var : num1)
            System.out.println(var);
    }
}
