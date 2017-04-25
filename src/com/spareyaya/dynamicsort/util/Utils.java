package com.spareyaya.dynamicsort.util;

import java.util.Random;

/**
 * Created on 2017/4/16.
 *
 * @author spareyaya.
 */
public class Utils {

    private static final int ARRAY_SIZE = 100;

    public static boolean isEmpty(int[] unsortedArray) {
        if (unsortedArray == null || unsortedArray.length == 0) {
            return true;
        }

        return false;
    }

    /**
     * 随机生成100个[0, 300)的整数
     * */
    public static int[] createRadomData() {
        int[] arr = new int[ARRAY_SIZE];

        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < ARRAY_SIZE; i++) {
            arr[i] = random.nextInt(300) + 1;
        }

        return arr;
    }

    /**
     * 打印数据
     * */
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * 简单的日志方法
     * */
    public static void log(String tag, String msg) {
        System.out.println(tag + " --- " + msg);
    }
}
