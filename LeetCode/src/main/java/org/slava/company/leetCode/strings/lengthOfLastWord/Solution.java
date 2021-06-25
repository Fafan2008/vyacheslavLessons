package org.slava.company.leetCode.strings.lengthOfLastWord;

import java.lang.reflect.Method;

public class Solution {
    public static int lengthOfLastWord1(String s) {
        int i = s.length();
        int counter = 0;
        while(--i>=0)
            if(s.charAt(i)!=' ')
                counter++;
            else
            if(counter>0)
                break;
        return counter;
    }

    public static int lengthOfLastWord2(String s) {
        s = s.trim();
        int lastIndex = s.lastIndexOf(' ') + 1;
        return s.length() - lastIndex;
    }

    public static void main(String[] args) {
        int i = 1;
        int idMethod = 1;
        Class classObj = Solution.class;
        Method[] methods = classObj.getMethods();
        {
            String str = "\"Hello World\"";
            int result = lengthOfLastWord1(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 5");
            System.out.println("Output: " + result);
            ++i;
        }
        {
            String str = "\"Hello World \"";
            int result = lengthOfLastWord1(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 5");
            System.out.println("Output: " + result);
            ++i;
        }
        {
            String str = "\"  \"";
            int result = lengthOfLastWord1(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 0");
            System.out.println("Output: " + result);
            ++i;
        }
        idMethod++;
        {
            String str = "\"Hello World\"";
            int result = lengthOfLastWord1(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 5");
            System.out.println("Output: " + result);
            ++i;
        }
        {
            String str = "\"Hello World \"";
            int result = lengthOfLastWord1(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 5");
            System.out.println("Output: " + result);
            ++i;
        }
        {
            String str = "\"  \"";
            int result = lengthOfLastWord1(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 0");
            System.out.println("Output: " + result);
            ++i;
        }
    }
}
