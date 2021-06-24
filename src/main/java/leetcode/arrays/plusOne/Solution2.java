package leetcode.arrays.plusOne;

public class Solution2 {
    public int[] plusOne(int[] digits){
        int end = digits.length-1;
        for(;end >= 0; end--){
            if (++digits[end] < 10)
                return digits;
            digits[end] = 0;
        }
        int[] newArray = new int[digits.length+1];
        newArray[0] = 1;
        return newArray;
    }
}