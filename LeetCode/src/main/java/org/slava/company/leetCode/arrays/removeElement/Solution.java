package org.slava.company.leetCode.arrays.removeElement;

public class Solution {
    public static int removeElement(int[] nums, int val){
        if(nums.length == 0) return 0;
        int posCurrent = 0;
        int posEnd = nums.length;
        while(posCurrent < posEnd){
            if(nums[posCurrent] == val){
                nums[posCurrent] = nums[posEnd-1];
                posEnd--;
            }else
            {
                posCurrent++;
            }
        }
        return posEnd;
    }

    public static void main(String[] args) {
        int i = 1;
        {
            int[] arr = new int[]{3,2,2,3};
            int result = removeElement(arr, 2);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{3,2,2,3};
            int result = removeElement(arr, 3);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{0,1,2,2,3,0,4,2};
            int result = removeElement(arr, 2);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{2};
            int result = removeElement(arr, 2);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{2,2,2,2,2};
            int result = removeElement(arr, 2);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{2,2,0,0,0};
            int result = removeElement(arr, 2);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{20,0,0,0,0};
            int result = removeElement(arr, 2);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
    }
}
