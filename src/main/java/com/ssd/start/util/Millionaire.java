package com.ssd.start.util;

import java.util.Random;

/**
 * 百万富翁神秘代码
 * @author WHD
 * @date 2020/9/7 9:22
 */
public class Millionaire {

    public static void main (String[] args) {
        int[] redPool = new int[33];
        for (int i = 1; i <= 33; ++i) {
            redPool[i-1] = i;
        }
        int[] bluePool = new int[16];
        for (int i = 1; i <= 16; ++i) {
            bluePool[i-1] = i;
        }
        int[] result = new int[7];
        Random random = new Random();
        // 把红色池子打乱
        for (int i = 0; i < redPool.length; ++i) {
            int tempRedIndex = random.nextInt(redPool.length);
            int tempValue = redPool[i];
            redPool[i] = redPool[tempRedIndex];
            redPool[tempRedIndex] = tempValue;
        }
        int redStartIndex = random.nextInt(redPool.length - 5);
        for (int i = 0; i < 6; i++) {
            result[i] = redPool[redStartIndex + i];
        }
        sort(result);
        // 蓝球
        int blueIndex = random.nextInt(bluePool.length);
        result[6] = bluePool[blueIndex];
        System.err.println("马上成为百万富翁，神秘代码 （⊙o⊙） ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < result.length; ++i) {
            if (result[i] < 10) {
                stringBuilder.append("0");
            }
            stringBuilder.append(result[i]);
            stringBuilder.append(" ");
        }
        System.err.println(stringBuilder.toString());
    }

    /**
     * 冒泡排序 红球
     * @param result
     */
    public static void sort(int[] result) {
        final int max = 6;
        for (int i = 0; i < max-1; ++i) {
            for (int j = 0; j < max-1-i; ++j) {
                if (result[j] > result[j+1]) {
                    int temp = result[j];
                    result[j] = result[j+1];
                    result[j+1] = temp;
                }
            }
        }
    }

}
