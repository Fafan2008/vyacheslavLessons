package org.slava.company.leetCode.arrays.maximumSubarray;

import java.util.Arrays;

public class Solution {

    public static int maxSubArray(int[] nums){
        int maxResult = nums[0];
        int maxSub = nums[0];
        int start = 0;
        int end = 0;

        for(int i = 1; i < nums.length; i++){
            if(nums[i] > maxSub + nums[i]){
                maxSub = nums[i];
                start = i;
            }else {
                maxSub += nums[i];
            }
            if(maxResult < maxSub){
                maxResult = maxSub;
                end = i;
            }
        }
        return maxResult;
    }

    public static int maxSubArray2(int[] nums){
        int maxResult = nums[0];
        int maxSub = nums[0];
        for(int i = 1; i < nums.length; i++){
            maxSub = Math.max(nums[i], maxSub+nums[i]);
            maxResult = Math.max(maxResult, maxSub);
        }
        return maxResult;
    }

    public static void main(String[] args) {
        int i = 1;
        {
            int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
            int result = maxSubArray(arr);
            System.out.println("Test " + i );
            System.out.println("Input data:");
            System.out.println(Arrays.toString(arr));
            System.out.println("Right answer is 6");
            System.out.println("Algorithm answer is " + result);
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{1};
            int result = maxSubArray(arr);
            System.out.println("Test " + i );
            System.out.println("Input data:");
            System.out.println(Arrays.toString(arr));
            System.out.println("Right answer is 1");
            System.out.println("Algorithm answer is " + result);
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{5,4,-1,7,8};
            int result = maxSubArray(arr);
            System.out.println("Test " + i );
            System.out.println("Input data:");
            System.out.println(Arrays.toString(arr));
            System.out.println("Right answer is 23");
            System.out.println("Algorithm answer is " + result);
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
            int result = maxSubArray2(arr);
            System.out.println("Test " + i );
            System.out.println("Input data:");
            System.out.println(Arrays.toString(arr));
            System.out.println("Right answer is 6");
            System.out.println("Algorithm answer is " + result);
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{1};
            int result = maxSubArray2(arr);
            System.out.println("Test " + i );
            System.out.println("Input data:");
            System.out.println(Arrays.toString(arr));
            System.out.println("Right answer is 1");
            System.out.println("Algorithm answer is " + result);
            System.out.println();
            ++i;
        }
        {
            int[] arr = new int[]{5,4,-1,7,8};
            int result = maxSubArray2(arr);
            System.out.println("Test " + i );
            System.out.println("Input data:");
            System.out.println(Arrays.toString(arr));
            System.out.println("Right answer is 23");
            System.out.println("Algorithm answer is " + result);
            System.out.println();
            ++i;
        }
    }
}
