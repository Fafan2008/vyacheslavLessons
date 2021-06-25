package org.slava.company.leetCode.strings.longestSubstringWithoutRepeatingCharacters;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public static int lengthOfLongestSubstring1(String s){
        int counter = 0;
        int result = 0;
        int start = 0;
        int end = 0;
        int i =0;
        int size = s.length();
        Map<Character, Integer> memory = new HashMap<>();
        while(i<size){
            char currentSymbol = s.charAt(i);
            if(memory.containsKey(currentSymbol))
            {
                result = Math.max(counter, result);
                end = memory.get(currentSymbol);
                do memory.remove(s.charAt(start));
                while(++start<=end);
                counter = i - start + 1;
            }else{
                counter++;
            }
            memory.put(currentSymbol, i);
            i++;
        }
        return Math.max(result, counter);
    }
    public static int lengthOfLongestSubstring2(String s){
        int start = 0;
        int end = 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        while (end < s.length()) {
            if (!set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                end++;
                max = Math.max(set.size(), max);
            } else {
                set.remove(s.charAt(start));
                start++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int i = 1;
        int idMethod = 1;
        Class classObj = org.slava.company.leetCode.strings.lengthOfLastWord.Solution.class;
        Method[] methods = classObj.getMethods();
        {
            String str = "abcabcbb";
            int result = lengthOfLongestSubstring1(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 3");
            System.out.println("Output: " + result);
            ++i;
        }
        {
            String str = "bbbbb";
            int result = lengthOfLongestSubstring1(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 1");
            System.out.println("Output: " + result);
            ++i;
        }
        {
            String str = "pwwkew";
            int result = lengthOfLongestSubstring1(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 3");
            System.out.println("Output: " + result);
            ++i;
        }
        {
            String str = " ";
            int result = lengthOfLongestSubstring1(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 1");
            System.out.println("Output: " + result);
            ++i;
        }
        idMethod++;
        {
            String str = "abcabcbb";
            int result = lengthOfLongestSubstring2(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 3");
            System.out.println("Output: " + result);
            ++i;
        }
        {
            String str = "bbbbb";
            int result = lengthOfLongestSubstring2(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 1");
            System.out.println("Output: " + result);
            ++i;
        }
        {
            String str = "pwwkew";
            int result = lengthOfLongestSubstring2(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 3");
            System.out.println("Output: " + result);
            ++i;
        }
        {
            String str = " ";
            int result = lengthOfLongestSubstring2(str);
            System.out.println("Test number " + i);
            System.out.println("Using method: " + methods[idMethod]);
            System.out.println("Input data: " + str);
            System.out.println("Expected: 1");
            System.out.println("Output: " + result);
            ++i;
        }
    }
}
