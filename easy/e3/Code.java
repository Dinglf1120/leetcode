package e3;

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
        boolean result = isPalindrome4(1211);
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

    /**
     * myself
     * 6/10 37.6
     * 时间复杂度一般般 6的时候优秀 10垃圾
     * 转成string用了字符串，需要空间
     * @param x
     * @return
     */
    public static boolean isPalindrome2(int x) {
        String s = String.valueOf(x);
        int length = s.length();
        //一位数时index为0，for根本没执行直接返回true
        int index = length / 2;
        for (int i = 0; i < index; i++) {
            //和当前i对应的是length-1-i
            if (!Objects.equals(s.charAt(i), s.charAt(length - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * people他人思路
     * 9ms 62.34
     * 一个回文数，反转之后等于原来的数。如果反转之后溢出了，说明不是回文（否则原先也是溢出的）
     * action：自己写的时候明明想到回文了，但是没有想到回文后等于自身。
     * 不足：整个数做了完全的反转
     * @param x
     * @return
     */
    public static boolean isPalindrome3(int x) {
        //负数或者可以整除10，一定不是回文。-无法回文，整除10（去除0）说明个位是0，相对应的首位不可能是0
        if (x < 0 || (0 == x % 10 && x != 0)) {
            return false;
        }
        //前面处理了负数，那么回文就无需考虑负数。
        int reverse = reverse1(x);
        return x == reverse;
    }

    public static int reverse1(int x) {
        int reverse = 0;
        while (x > 0) {
            if (reverse > Integer.MAX_VALUE / 10) {
                return 0;
            }
            reverse = reverse*10 + x % 10;
            x = x /10;
        }
        return reverse;
    }

    /**
     * answer
     * 4/8ms 95-100
     * 也是利用反转，但是反转一半即可，也能避免溢出
     * action：
     *  自己写的时候明明想到回文且还是回文一半，但傻了吧唧用string，脑子有病。
     *  也不要切成两半之后回文，否则拥有便会问会出现001,先变成1回文后是1的情况
     * @param x
     * @return
     */
    public static boolean isPalindrome4(int x) {
        //负数或者可以整除10，一定不是回文。-无法回文，整除10（去除0）说明个位是0，相对应的首位不可能是0
        if (x < 0 || (0 == x % 10 && x != 0)) {
            return false;
        }
        /*
            现在的问题在于如何确定反转到一半了
            对于回文数来说：
                （1）偶数位进行到一半，位数相等。reverse == x, 123321拆为123和123（直接对比reverse==x）
                （2）奇数位进行到一半,右边比左边多一位。1235321拆成123和1235。
                    reverse > x（这是一个节点，在此之前x一直大于reverse[位数]）
                    （比较的时候需要reverse/10 == x）
            对于非回文说来说：
                （1）偶数位进行到一半，位数相等。这个时候reverse可能小于x(2111为12、11)
                    也可能大于x(1234为12、43)。但是没有反转到一半的时候一定是reverse<x，
                    那节点只能取reverse>x。这个时候不论比较reverse==x还是
                    reverse/10 == x，条件都不满足，一定不是回文，这是正确的结果
                （2）奇数位进行到一半,右边比左边多一位。reverse一定大于x

            综上所述，反转一半的节点就是reverse>=x，不满足时需要一直反转
         */
        int reverse = 0;
        while (reverse < x) {
            reverse = reverse * 10 + x % 10;
            x = x / 10;
        }
        return reverse == x || x == reverse / 10;
    }
}
