package com.jabin;

import java.util.Arrays;

public class test {
    private static int res;

    public  static int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        mergeSort(nums, 0, nums.length - 1);
        return res;
    }

    private static void mergeSort(int[] nums, int l, int r) {
        if(l >= r){
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    private static void merge(int[] nums, int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(nums, l, r + 1);
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                nums[k] = aux[j - l];
                j++;
            } else if (j > r) {
                nums[k] = aux[i - l];
                i++;
            } else if (aux[i-l] <= aux[j-l]) {
                nums[k] = aux[i - l];
                i++;
            } else {
                nums[k] = aux[j - l];
                j++;
                res += mid - i + 1;
            }

        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,2,3,1};
        reversePairs(nums);
        System.out.println(Arrays.toString(nums));
    }
}
