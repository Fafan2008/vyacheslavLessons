package leetcode.arrays.romanNumerals;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public static int romanToIntBest(String s) {
        int[] map = new int[256];
        map['I'] = 1; map['V'] = 5; map['X'] = 10; map['L'] = 50; map['C'] = 100; map['D'] = 500; map['M'] = 1000;

        int ret = 0, pre = 1;
        for (int i = s.length()-1; i >= 0; --i) {
            int cur = map[s.charAt(i)];
            if (cur < pre) ret -= cur;
            else {
                pre = cur;
                ret += cur;
            }
        }
        return ret;
    }

    public int romanToIntSwitchCase(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            switch(s.charAt(i)){
                case 'I':
                    if(i < s.length() - 1 && (s.charAt(i + 1) == 'V' || s.charAt(i + 1) == 'X')){
                        res -= 1;
                    }else{
                        res += 1;
                    }
                    break;
                case 'X':
                    if(i < s.length() - 1 && (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C')){
                        res -= 10;
                    }else{
                        res += 10;
                    }
                    break;
                case 'C':
                    if(i < s.length() - 1 && (s.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'M')){
                        res -= 100;
                    }else{
                        res += 100;
                    }
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'M':
                    res += 1000;
                    break;
            }
        }
        return res;
    }

    public static int romanToIntMy(String s) {
        HashMap<Character, Integer> numbers = new HashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};
        if(s.length() == 1)
            return numbers.get(s.charAt(0));
        HashMap<Character, HashSet<Character>> rules = new HashMap<>();
        rules.put('I', new HashSet<Character>() {{
            add('V');
            add('X');
        }});
        rules.put('X', new HashSet<Character>() {{
            add('L');
            add('C');
        }});
        rules.put('C', new HashSet<Character>() {{
            add('D');
            add('M');
        }});

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            char next = i + 1 < s.length() ? s.charAt(i + 1) : 0;
            int add = 0;
            if (next != 0 && rules.containsKey(current) && rules.get(current).contains(next)) {
                add = numbers.get(next) - numbers.get(current);
                i++;
            } else {
                add = numbers.get(current);
            }
            result += add;
        }
        return result;
    }

    public static void main(String[] args) {
        int result1 = romanToIntMy("I");
        int result2 = romanToIntMy("II");
        int result3 = romanToIntMy("III");
        int result4 = romanToIntMy("IV");
        int result5 = romanToIntMy("V");
        int result6 = romanToIntMy("X");
        int result7 = romanToIntMy("XL");
        int result8 = romanToIntMy("LVIII");
        int result9 = romanToIntMy("MCMXCIV");
    }
}
