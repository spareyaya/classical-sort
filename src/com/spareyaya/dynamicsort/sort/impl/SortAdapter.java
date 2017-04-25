package com.spareyaya.dynamicsort.sort.impl;

import com.spareyaya.dynamicsort.sort.Sort;

/**
 * Created on 2017/4/16.
 *
 * @author spareyaya.
 *
 * 适配Sort接口
 *
 * @see Sort
 */
public class SortAdapter implements Sort {

    @Override
    public void sort(int[] unsortedArray) {
        sortDesc(unsortedArray);
    }

    @Override
    public void sortDesc(int[] unsortedArray) {

    }

    @Override
    public void sortInc(int[] unsortedArray) {

    }
}
