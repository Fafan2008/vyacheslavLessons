package leetcode.integer.reverseInteger;

public class Solution {
    public static int reverse(int x){
        char[] arr = Integer.toString(x).toCharArray();
        int start = 0;
        int end = arr.length-1;
        if(x<0) start = 1;
        do{
            char tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
        }while (++start < --end);
        try {
            return Integer.parseInt(String.valueOf(arr));
        }catch (NumberFormatException e){
            return 0;
        }
    }

    public static int reverse2(int x){
        long reverse = 0;
        while (x != 0){
            reverse = reverse*10 + x%10;
            x /= 10;
        }
        if(reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE)
            return 0;
        else
            return (int) reverse;
    }

    public static void main(String[] args) {
        {
            int res1 = reverse(1434234);
            int res2 = reverse(123);
            int res3 = reverse(-10);
            int res4 = reverse(-1234932249);
            int res5 = reverse(0);
        }
        {
            int res1 = reverse2(1434234);
            int res2 = reverse2(123);
            int res3 = reverse2(-10);
            int res4 = reverse2(-1234932249);
            int res5 = reverse2(0);
        }
     }
}
