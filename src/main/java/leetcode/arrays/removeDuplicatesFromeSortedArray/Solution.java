package leetcode.arrays.removeDuplicatesFromeSortedArray;

class Solution {
    public static int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int posCurrent = 0;
        int posInput = 1;
        while (posCurrent <= nums.length - 1){
            if(nums[posCurrent] != nums[posInput-1]) {
                nums[posInput] = nums[posCurrent];
                posInput++;
            }
            posCurrent++;
        }
        return posInput;
    }

    public static void main(String[] args) {
        int i = 1;
        {
            int[] arr = new int[]{1,2,3,4,5,6,7};
            int result = removeDuplicates(arr);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{1,2,2,4,4,5,6,7};
            int result = removeDuplicates(arr);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{1};
            int result = removeDuplicates(arr);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{};
            int result = removeDuplicates(arr);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{1,1,1,1,1,1};
            int result = removeDuplicates(arr);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{1,1,1,1,1,1};
            int result = removeDuplicates(arr);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{-100,-10,-1,1,1,1};
            int result = removeDuplicates(arr);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            for(int j = 0; j < result; j++)
                System.out.print(arr[j] + ", ");
            System.out.println();
            ++i;
        }
    }
}