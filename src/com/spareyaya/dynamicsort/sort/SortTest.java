package com.spareyaya.dynamicsort.sort;

import com.spareyaya.dynamicsort.sort.impl.QuickSort;
import com.spareyaya.dynamicsort.util.Utils;

/**
 * Created on 2017/4/17.
 *
 * @author spareyaya.
 *
 * 测试排序算法
 */
public class SortTest {

    public static void main(String[] args) {
        int[] arr = Utils.createRadomData();

        Utils.printArray(arr);

        Sort sort = new QuickSort();
        sort.sortInc(arr);

        Utils.printArray(arr);

        System.out.println("=====================");

        int[] arr2 = Utils.createRadomData();
        Utils.printArray(arr2);

        sort.sortDesc(arr2);

        Utils.printArray(arr2);
    }
}
