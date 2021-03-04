package leetcode.integer.palindromeNumber;

public class Solution {
    public static boolean isPalindrome(int x) {
        if(x<0) return false;
        if(x<10) return true;
        char[]arr = Integer.toString(x).toCharArray();
        int start = 0;
        int end = arr.length - 1;
        do {
            if(arr[start] != arr[end])
                return false;
        }while (start++ < end--);
        return true;
    }
    public static boolean isPalindrome2(int x) {
        if(x<0 || (x!=0 && x%10 == 0)) return false;
        int reverted = 0;
        while (x > reverted){
            reverted = reverted*10 + x%10;
            x /= 10;
        }
        return reverted == x || reverted/10 == x;
    }

    public static void main(String[] args) {
        {
            boolean res1 = isPalindrome(111);
            boolean res2 = isPalindrome(121);
            boolean res3 = isPalindrome(11);
            boolean res4 = isPalindrome(12121);
            boolean res5 = isPalindrome(123);
            boolean res6 = isPalindrome(12);
            boolean res7 = isPalindrome(1);
            boolean res8 = isPalindrome(0);
            boolean res9 = isPalindrome(100);
            boolean res10 = isPalindrome(-11);
        }
        {
            boolean res1 = isPalindrome2(111);
            boolean res2 = isPalindrome2(121);
            boolean res3 = isPalindrome2(11);
            boolean res4 = isPalindrome2(12121);
            boolean res5 = isPalindrome2(123);
            boolean res6 = isPalindrome2(12);
            boolean res7 = isPalindrome2(1);
            boolean res8 = isPalindrome2(0);
            boolean res9 = isPalindrome2(100);
            boolean res10 = isPalindrome2(-11);
            boolean res11 = isPalindrome2(121);
            boolean res12 = isPalindrome2(1231);
        }
    }
}
