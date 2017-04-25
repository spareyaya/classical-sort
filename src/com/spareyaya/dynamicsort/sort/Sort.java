package com.spareyaya.dynamicsort.sort;

/**
 * Created on 2017/4/16.
 *
 * @author spareyaya.
 *
 * 算法接口，可以继承SortAdapter来选择实现其中一种顺序的排序。实现该接口方法时为了记录
 * 排序过程，在排序过程中当有数据的位置发生变化，调用
 * @see com.spareyaya.dynamicsort.util.DataUtils#add(int[])
 * 来记录数据的变化
 *
 * @see com.spareyaya.dynamicsort.sort.impl.SortAdapter
 */
public interface Sort {

    /**
     * 排序全部数据，默认为降序
     * */
    void sort(int[] unsortedArray);

    /**
     * 按降序排序
     * */
    void sortDesc(int[] unsortedArray);

    /**
     * 按升序排序
     * */
    void sortInc(int[] unsortedArray);
}
