package com.spareyaya.dynamicsort.sort.impl;

import com.spareyaya.dynamicsort.util.DataUtils;
import com.spareyaya.dynamicsort.util.Utils;

/**
 * Created on 2017/4/17.
 *
 * @author spareyaya.
 */
public class InsertionSort extends SortAdapter {

    @Override
    public void sort(int[] unsortedArray) {
        sortDesc(unsortedArray);
    }

    @Override
    public void sortDesc(int[] unsortedArray) {

        if (Utils.isEmpty(unsortedArray)) {
            return;
        }

        int mark, i, j;

        for (i = 1; i < unsortedArray.length; i++) {

            if (unsortedArray[i] > unsortedArray[i - 1]) {
                mark = unsortedArray[i];

                for (j = i; j > 0 && mark > unsortedArray[j - 1]; j--) {
                    unsortedArray[j] = unsortedArray[j - 1];
                    DataUtils.add(unsortedArray);

                }

                unsortedArray[j] = mark;
                DataUtils.add(unsortedArray);

            }

        }

    }

    @Override
    public void sortInc(int[] unsortedArray) {

        if (Utils.isEmpty(unsortedArray)) {
            return;
        }

        int mark, i, j;

        for (i = 1; i < unsortedArray.length; i++) {

            if (unsortedArray[i] < unsortedArray[i - 1]) {
                mark = unsortedArray[i];

                for (j = i; j > 0 && mark < unsortedArray[j - 1]; j--) {
                    unsortedArray[j] = unsortedArray[j - 1];
                    DataUtils.add(unsortedArray);

                }

                unsortedArray[j] = mark;
                DataUtils.add(unsortedArray);

            }

        }
    }
}
