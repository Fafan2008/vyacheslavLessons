package org.slava.company.leetCode.strings.implementStrStr;

public class Solution {
    public static int strStr(String haystack, String needle){
        if (needle.isEmpty()) return 0;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            for (int j = 0; j < needle.length() && haystack.charAt(i + j) == needle.charAt(j); j++)
                if (j == needle.length() - 1) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int i = 1;
        {
            String str1 = "mississippi";
            String str2 = "issip";
            int result = strStr(str1, str2);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            ++i;
        }
    }
}
