package com.jabin.da.base.heap;

import java.util.*;

public class LeetcodeTest {

    private static class Freq implements Comparable<Freq> {
        private int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq o) {
            if (this.freq < o.freq) {
                return 1;
            } else if (this.freq > o.freq) {
                return -1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "Freq{" +
                    "e=" + e +
                    ", freq=" + freq +
                    '}';
        }
    }

    public
    static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i :
                nums) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }

        PriorityQueue<Freq> queue = new PriorityQueue<>(k);
        for (int key :
                map.keySet()) {
            if (queue.getSize() < k) {
                queue.offer(new Freq(key, map.get(key)));
            } else if (map.get(key) > queue.peek().freq) {
                queue.poll();
                queue.offer(new Freq(key, map.get(key)));
            }
        }
        int[] res = new int[k];
        int index = k-1;
        while (!queue.isEmpty()) {
            Freq freq = queue.poll();
            System.out.println(freq);
            res[index--] = freq.e;

        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {5,3,1,1,1,3,5,73,1};
        int k = 3;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }
}
