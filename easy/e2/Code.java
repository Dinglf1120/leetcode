package e2;

import java.util.Objects;

/**
 *@ClassName Code
 *@Description
 *@Author dlf
 *@Date 12/11/2021 13:23
 *@Version 1.0
 **/
public class Code {

    public static void main(String[] args) {
        int result = reverse3(1534236469);
        System.out.println(result);
    }

    /**
     * myself
     * 5ms 35.4
     * 时间复杂度相比很高
     *
     * action：反转后超过int上限或下限
     * @param x
     * @return
     */
    public static int reverse1(int x) {
        String s = String.valueOf(x);
        Character symbol = null;
        StringBuilder builder = new StringBuilder();
        for (int i = s.length() - 1 ; i >= 0; i--) {
            char c = s.charAt(i);
            if (i != 0) {
                builder.append(c);
            } else if (Objects.equals('-', c) || Objects.equals('+', c)){
                symbol = c;
            } else {
                builder.append(c);
            }
        }
        String result = Objects.isNull(symbol) ? builder.toString() : symbol + builder.toString();
        //这里用long钻空子了 题目中说假设环境不允许存储 64 位整数
        long y = Long.parseLong(result);
        if (y > Integer.MAX_VALUE || y < Integer.MIN_VALUE) {
            return 0;
        }
        return Integer.parseInt(result);
    }

    /**
     * people 他人思路
     * 1ms 35.3
     * 时间复杂度完美
     * think: 重复弹出x的末尾数字
     * 不足：题目中说假设环境不允许存储 64 位整数，所以用long属于钻空子
     * @param x
     * @return
     */
    public static int reverse2(int x) {
        /*
            每次取出最后一个尾数(个位数 %10),x去掉尾数（个位数 /10）
            执行一次，少掉10位，所以n每次乘10
            example：123
            1、x = 123, n = 0*10 + 3, x=12。每次都会去掉最后一个个位数
            2、x = 12, n = 3*10 + 2, x=1。
            3、x = 1, n = 32*10 + 1, x=0。剩最后一个个位数除以10肯定等于0，结束
         */
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;
            x = x/10;
        }
        //如果将long转为int还等于long的话，说明没超出上下限，返回(int)long即可，否则超过返回0
        return (int)n==n? (int)n:0;
    }

    /**
     * answer
     *
     * 和上方他人的思路是一样的，但是没有借助long，关于上下限判断的推理过程如下
     * @param x
     * @return
     */
    public static int reverse3(int x) {
        int y = 0;
        while (x != 0) {
            /*
                -2^31 <= y*10 + x%10 <= 2^31 - 1
                MIN <= 10y + k <= MAX
                MIN/10 <= y + k/10 <= MAX/10
                因为x%10一定是一个个位数（取余），个位数0-9，无论是那个值除以10都是0(int是整数)
                不等式为
                MIN/10 <= y + 0 <= MAX/10
                MIN/10 <= y <= MAX/10为 y > MAX/10 || y < MIN/10
                所以不满足条件的y
             */
            if (y > Integer.MAX_VALUE / 10 || y < Integer.MIN_VALUE / 10) {
                return 0;
            }
            y = y * 10 + x % 10;
            x = x / 10;
        }
        return y;
    }
}
