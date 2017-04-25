package com.spareyaya.dynamicsort.sort.impl;

import com.spareyaya.dynamicsort.util.DataUtils;
import com.spareyaya.dynamicsort.util.Utils;

/**
 * Created on 2017/4/24.
 *
 * @author spareyaya.
 */
public class SelectionSort extends SortAdapter {

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

        int mark, tmp, i, j;
        for (i = 0; i < unsortedArray.length; i++) {

            mark = i;

            for (j = i + 1; j < unsortedArray.length; j++) {
                if (unsortedArray[mark] < unsortedArray[j]) {
                    mark = j;
                }
            }

            if (mark != i) {
                tmp = unsortedArray[i];
                unsortedArray[i] = unsortedArray[mark];
                unsortedArray[mark] = tmp;
                DataUtils.add(unsortedArray);
            }

        }
    }

    @Override
    public void sortInc(int[] unsortedArray) {

        if (Utils.isEmpty(unsortedArray)) {
            return;
        }

        DataUtils.add(unsortedArray);

        int mark, tmp, i, j;
        for (i = 0; i < unsortedArray.length; i++) {

            mark = i;

            for (j = i + 1; j < unsortedArray.length; j++) {
                if (unsortedArray[mark] > unsortedArray[j]) {
                    mark = j;
                }
            }

            if (mark != i) {
                tmp = unsortedArray[i];
                unsortedArray[i] = unsortedArray[mark];
                unsortedArray[mark] = tmp;
                DataUtils.add(unsortedArray);
            }

        }
    }
}
