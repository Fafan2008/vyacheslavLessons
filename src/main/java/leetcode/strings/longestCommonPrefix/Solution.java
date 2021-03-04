package leetcode.strings.longestCommonPrefix;

public class Solution {
    public static String longestCommonPrefix(String[] strs){
        if(strs.length == 0 || strs[0].isEmpty())
            return "";
        String firstWord = strs[0];
        int commonCount  = firstWord.length();
        for(int i = 1; i < strs.length; i++){
            String checkWord = strs[i];
            if(checkWord.length() <= commonCount)
                commonCount = checkWord.length();
            while (commonCount != 0 && !checkWord.substring(0, commonCount).equals(firstWord.substring(0, commonCount))){
                commonCount--;
            }
        }
        return firstWord.substring(0,commonCount);
    }

    public static void main(String[] args) {
        {
            String[] strs = new String[]{"flower","flow","flight"};
            String result = longestCommonPrefix(strs);
            System.out.print(result);
        }
        {
            String[] strs = new String[]{"dog","racecar","car"};
            String result = longestCommonPrefix(strs);
            System.out.print(result);
        }
        {
            String[] strs = new String[]{};
            String result = longestCommonPrefix(strs);
            System.out.print(result);
        }
        {
            String[] strs = new String[]{"dog","racecar","car", "dog"};
            String result = longestCommonPrefix(strs);
            System.out.print(result);
        }
        {
            String[] strs = new String[]{"",""};
            String result = longestCommonPrefix(strs);
            System.out.print(result);
        }
        {
            String[] strs = new String[]{"abc"};
            String result = longestCommonPrefix(strs);
            System.out.print(result);
        }
        {
            String[] strs = new String[]{"a"};
            String result = longestCommonPrefix(strs);
            System.out.print(result);
        }
        {
            String[] strs = new String[]{"ab", "a"};
            String result = longestCommonPrefix(strs);
            System.out.print(result);
        }
    }
}
