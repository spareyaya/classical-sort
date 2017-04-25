package com.spareyaya.dynamicsort.sort.impl;

import com.spareyaya.dynamicsort.util.DataUtils;
import com.spareyaya.dynamicsort.util.Utils;

/**
 * Created on 2017/4/17.
 *
 * @author spareyaya.
 */
public class QuickSort extends SortAdapter {

    @Override
    public void sort(int[] unsortedArray) {
        sortDesc(unsortedArray);
    }

    @Override
    public void sortDesc(int[] unsortedArray) {

        if (Utils.isEmpty(unsortedArray)) {
            return;
        }

        qSortDesc(unsortedArray, 0, unsortedArray.length - 1);
    }

    @Override
    public void sortInc(int[] unsortedArray) {

        if (Utils.isEmpty(unsortedArray)) {
            return;
        }

        qSortInc(unsortedArray, 0, unsortedArray.length - 1);
    }


    private void qSortInc(int[] arr, int l, int h) {

        if (l < h) {
            int pivot = descPartition(arr, l, h);
            qSortInc(arr, l, pivot - 1);
            qSortInc(arr, pivot + 1, h);
        }

    }

    private int descPartition(int[] arr, int l, int h) {

        int mark = arr[l];
        int pivot = arr[l];

        while (l < h) {

            while (l < h && arr[h] >= pivot) {
                --h;
            }

            arr[l] = arr[h];
            DataUtils.add(arr);

            while (l < h && arr[l] <= pivot) {
                ++l;
            }
            arr[h] = arr[l];
            DataUtils.add(arr);
        }

        arr[l] = mark;
        return l;

    }


    private void qSortDesc(int[] arr, int l, int h) {

        if (l < h) {
            int pivot = incPartition(arr, l, h);
            qSortDesc(arr, l, pivot - 1);
            qSortDesc(arr, pivot + 1, h);
        }

    }

    private int incPartition(int[] arr, int l, int h) {

        int mark = arr[l];
        int pivot = arr[l];

        while (l < h) {

            while (l < h && arr[h] <= pivot) {
                --h;
            }

            arr[l] = arr[h];
            DataUtils.add(arr);

            while (l < h && arr[l] >= pivot) {
                ++l;
            }
            arr[h] = arr[l];
            DataUtils.add(arr);
        }

        arr[l] = mark;
        return l;

    }

}
