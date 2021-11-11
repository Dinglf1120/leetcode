package twoSum;

import java.util.HashMap;
import java.util.Map;

/**
 *@ClassName Code
 *@Description
 *@Author dlf
 *@Date 11/11/2021 13:06
 *@Version 1.0
 **/
public class Code {

    public static void main(String[] args) {
        int[] nums = {3,3};
        int target = 6;
        int[] twoSum = twoSum2(nums, target);
        System.out.println(twoSum);
    }

    /**
     * myself
     * 	2 ms	38.4 MB
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        /*
         * 遍历数组用map存下每个元素需要配对的值和元素的位置，
         * 每次遍历时判断当前元素是否是map中需要的配对值，
         * 如果有说明当前元素和map中的index配上了
         *
         * action：
         * 先要看map中是否有这个元素，没有再存。
         * 原因：
         * 1、先存在判断会导致自己和自己匹配上
         * 2、否则map使用普通替换后，存的是后面的index，我们要优先前面的
         */
        int[] twoSum = new int[2];
        Map<Integer, Integer> otherNum = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (otherNum.containsKey(nums[i])) {
                int index = otherNum.get(nums[i]);
                twoSum[0] = index;
                twoSum[1] = i;
                break;
            }
            otherNum.put(target-nums[i], i);
        }
        return twoSum;
    }

    /**
     * myself
     * 56 ms	38.5 MB
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        /*
         * 最笨的双层循环
         * 因为不能是同一个元素，所以j直接从i+1开始即可
         */
        int[] twoSum = new int[2];
        m : for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++)  {
                if (nums[i] + nums[j] == target) {
                    twoSum[0] = i;
                    twoSum[1] = j;
                    break m;
                }
            }
        }
        return twoSum;
    }
}
