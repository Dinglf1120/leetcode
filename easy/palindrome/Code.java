package palindrome;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *@ClassName Code
 *@Description
 *@Author dlf
 *@Date 12/11/2021 16:40
 *@Version 1.0
 **/
public class Code {

    public static void main(String[] args) {
        boolean result = isPalindrome1(1001);
        System.out.println(result);
    }

    /**
     * myself
     * 13 38.3
     * 时间复杂度不好
     * @param x
     * @return
     */
    public static boolean isPalindrome1(int x) {
        //前面有-号，怎么也不会是回文
        if (x < 0) {
            return false;
        }
        //一位数指定回文
        if (x < 10) {
            return true;
        }
        String s = String.valueOf(x);
        int length = s.length();
        int index = length/2;
        int mode = length % 2;
        //不能用右边的反转，比如001会变成1
        int left = reverse(Integer.parseInt(s.substring(0, index)));
        int right = Integer.parseInt(s.substring(mode == 0 ? index : index + 1, length));
        return left == right;
    }
    public static int reverse(int x) {
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;
            x = x/10;
        }
        return (int)n==n? (int)n:0;
    }
}
