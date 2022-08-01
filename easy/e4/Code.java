package e4;

import java.util.HashMap;
import java.util.Map;

/**
 *@ClassName Code
 *@Description
 *@Author dlf
 *@Date 15/11/2021 14:58
 *@Version 1.0
 **/
public class Code {

    public static void main(String[] args) {
        int result = roman("MCMXCIV");
        System.out.println(result);
    }

    /**
     * myself
     * 6ms 60.4
     * 38.8 44.86
     * @param s
     * @return
     */
    public static int roman(String s) {
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        for (int i = 0; i < s.length(); i++) {
            int m = map.get(s.charAt(i));
            if (i < s.length() - 1) {
               int n = map.get(s.charAt(i+1));
               if (m < n) {
                   m = -m;
               }
            }
            result = result + m;
        }
        return result;
    }
}
