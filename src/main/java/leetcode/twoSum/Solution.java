package leetcode.twoSum;


import java.util.HashMap;

public class Solution {
    static public int[] twoSum(int[] nums, int target) {
        int[] result = new int[]{0, 1};
        if (nums.length == 2)
            return result;
        HashMap<Integer, Integer> memory = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int difference = target - nums[i];
            if (memory.containsKey(difference)) {
                int position = memory.get(difference);
                return i < position ? new int[]{i, position} : new int[]{position, i};
            } else {
                memory.put(nums[i], i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] answer = twoSum(arr, target);

        int[] arr2 = new int[]{3,2,4};
        int target2 = 6;
        int[] answer2 = twoSum(arr2, target2);
    }
}
