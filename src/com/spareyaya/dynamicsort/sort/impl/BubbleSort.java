package com.spareyaya.dynamicsort.sort.impl;

import com.spareyaya.dynamicsort.util.DataUtils;
import com.spareyaya.dynamicsort.util.Utils;

/**
 * Created on 2017/4/16.
 *
 * @author spareyaya.
 */
public class BubbleSort extends SortAdapter {

    @Override
    public void sort(int[] unsortedArray) {
        sortDesc(unsortedArray);
    }

    @Override
    public void sortDesc(int[] unsortedArray) {

        if (Utils.isEmpty(unsortedArray)) {
            return;
        }

        DataUtils.add(unsortedArray);

        int tmp;

        for (int i = 0; i < unsortedArray.length; i++) {
            for (int j = unsortedArray.length - 1; j > i; j--) {
                if (unsortedArray[j] > unsortedArray[j-1]) {
                    tmp = unsortedArray[j];
                    unsortedArray[j] = unsortedArray[j-1];
                    unsortedArray[j-1] = tmp;

                    DataUtils.add(unsortedArray);
                }

            }

        }
    }

    @Override
    public void sortInc(int[] unsortedArray) {

        boolean isSwap = false;

        if (Utils.isEmpty(unsortedArray)) {
            return;
        }

        int tmp;

        for (int i = 0; i < unsortedArray.length; i++) {
            for (int j = unsortedArray.length - 1; j > i; j--) {
                if (unsortedArray[j] < unsortedArray[j-1]) {
                    tmp = unsortedArray[j];
                    unsortedArray[j] = unsortedArray[j-1];
                    unsortedArray[j-1] = tmp;
                    isSwap = true;
                }

                if (isSwap) {
                    DataUtils.add(unsortedArray);
                    isSwap = false;
                }

            }

        }
    }
}
