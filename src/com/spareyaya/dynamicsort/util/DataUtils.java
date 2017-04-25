package com.spareyaya.dynamicsort.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2017/4/22.
 *
 * @author spareyaya.
 */
public class DataUtils {

    public static List<int[]> sData = new ArrayList<>();

    public static void add(int[] data) {
        int[] elem = Arrays.copyOf(data, data.length);
        sData.add(elem);
    }

    public static void clear() {
        sData.clear();
    }
}
