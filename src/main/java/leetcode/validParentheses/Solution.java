package leetcode.validParentheses;

import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class Solution {
    public static boolean isValid(String s){
        if(s.length() == 0 || s.length()%2 != 0)
            return false;
        HashMap<Character, Character> pairs = new HashMap<Character, Character>()
        {{
            put('{', '}');
            put('[', ']');
            put('(', ')');
        }};
        if(!pairs.containsKey(s.charAt(0)))
            return false;
        ArrayDeque<Character> openBrackets = new ArrayDeque<>();
        for (int pos = 0; pos<s.length(); pos++){
            char bracket = s.charAt(pos);
            if(pairs.containsKey(bracket))
                openBrackets.add(bracket);
            else
                if(openBrackets.isEmpty() || pairs.get(openBrackets.pollLast()) != bracket)
                    return false;
        }
        return openBrackets.isEmpty();
    }

    public static void main(String[] args) {
        int testNum = 0;
        {
            String s = "()";
            boolean result = isValid(s);
            System.out.println("Result of test " + Integer.toString(++testNum) + ":");
            System.out.println(result);
        }
        {
            String s = "()[]{}";
            boolean result = isValid(s);
            System.out.println("Result of test " + Integer.toString(++testNum) + ":");
            System.out.println(result);
        }
        {
            String s = "(]";
            boolean result = isValid(s);
            System.out.println("Result of test " + Integer.toString(++testNum) + ":");
            System.out.println(result);
        }
        {
            String s = "([)]";
            boolean result = isValid(s);
            System.out.println("Result of test " + Integer.toString(++testNum) + ":");
            System.out.println(result);
        }
        {
            String s = "(";
            boolean result = isValid(s);
            System.out.println("Result of test " + Integer.toString(++testNum) + ":");
            System.out.println(result);
        }
        {
            String s = "";
            boolean result = isValid(s);
            System.out.println("Result of test " + Integer.toString(++testNum) + ":");
            System.out.println(result);
        }
        {
            String s = "({}{}[][]){[([])]})";
            boolean result = isValid(s);
            System.out.println("Result of test " + Integer.toString(++testNum) + ":");
            System.out.println(result);
        }
        {
            String s = "({}{}[][])({[([])]})";
            boolean result = isValid(s);
            System.out.println("Result of test " + Integer.toString(++testNum) + ":");
            System.out.println(result);
        }
        {
            String s = "[({}{}[][])({[([])]})]";
            boolean result = isValid(s);
            System.out.println("Result of test " + Integer.toString(++testNum) + ":");
            System.out.println(result);
        }
        {
            String s = "(){}}{";
            boolean result = isValid(s);
            System.out.println("Result of test " + Integer.toString(++testNum) + ":");
            System.out.println(result);
        }
    }
}
