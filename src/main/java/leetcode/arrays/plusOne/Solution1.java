package leetcode.arrays.plusOne;

public class Solution1 {
    public int[] plusOne(int[] digits){
        int end = digits.length-1;
        boolean needAdd = true;
        for(;end >= 0 && needAdd; end--){
            needAdd = ++digits[end] == 10;
            digits[end] = needAdd ? 0 : digits[end];
        }
        if(digits[0]==0){
            int[] newArray = new int[digits.length+1];
            newArray[0] = 1;
            return newArray;
        }
        return digits;
    }
}
