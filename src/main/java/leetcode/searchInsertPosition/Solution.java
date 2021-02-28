package leetcode.searchInsertPosition;

class Solution {
    public static int searchInsertBrutForce(int[] nums, int target) {
        int i = 0;
        for(; i<nums.length; i++){
            if (nums[i]>=target)
                return i;
        }
        return i;
    }
    public static int searchInsertBinary(int[] nums, int target) {
        if(nums.length == 0) return 0;
        if(nums[0] >= target) return 0;
        if(nums[nums.length-1] < target) return nums.length;
        int left = 0;
        int right = nums.length - 1;
        int mid = (right + left)/2;
        while (left<=right){
            if(nums[mid]==target) return mid;
            if(nums[mid]>target){
                right = mid - 1;
            }else
                left = mid + 1;
            mid = (left + right)/2;
        }
        return left;
    }

    public static void main(String[] args) {
        int i = 1;
        {
            int[] arr = new int[]{1,2,3,4,5,6,7};
            int result = searchInsertBinary(arr, 2);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{1,2,3,4,5,6,7};
            int result = searchInsertBinary(arr, 1);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{1,2,3,4,5,6,7};
            int result = searchInsertBinary(arr, 7);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{1,2,3,4,5,6,7};
            int result = searchInsertBinary(arr, 4);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{1,2,3,4,5,6,7};
            int result = searchInsertBinary(arr, 8);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{1,2,3,4,5,6,7};
            int result = searchInsertBinary(arr, 0);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{1,2,3,4,5};
            int result = searchInsertBinary(arr, 2);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            System.out.println();
            ++i;
        }
    }
}
