package com.jabin.da.base.map;

import java.util.ArrayList;
import java.util.List;

public class LeetCodeTest {

    //350
    public int[] intersect(int[] nums1, int[] nums2) {
//        Map<Integer, Integer> map = new LinkedListMap<>();
        Map<Integer, Integer> map = new BSTMap<>();
        for (int i:
             nums1) {
            if (!map.contains(i)) {
                map.put(i, 1);
            }else {
                int value = map.get(i);
                map.put(i, ++value);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i:
             nums2) {
            if (map.contains(i)) {
                res.add(i);
                map.set(i, map.get(i) - 1);
                if (map.get(i) == 0) {
                    map.remove(i);
                }
            }
        }

        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }
}
