package com.nanfeng;

/**
 * Author：nanfeng
 * Created:2019/5/6
 */

//O(n)
public class BinarySearch {
    private static int binarySearch(int[] array, int value) {
        //[left,right)
        int left = 0;
        int right = array.length;
        //区间内还有一个数，需要继续找，没有数就可以停
        while (left < right) {
            //int mid = (left+right)/2;
            int mid = left + (right - left) / 2;
            if (value == array[mid]) {
                return mid;
            } else if (value < array[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
