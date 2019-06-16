package com.nanfeng;

import java.util.Arrays;

/**
 * Author：nanfeng
 * Created:2019/6/16
 */


public class HeapMain {
    /**
     * 向下调整（堆化）---递归（大堆）
     * 必须满足可以向下调整的前提：只有一个位置不满足堆
     *
     * 时间复杂度：O(log(n))
     *
     * @param tree  看成完全二叉树的数组
     * @param index 要调整位置的下标
     */
    private static void heapify(int[] tree, int index) {
        /**
         * 1.判断 index 位置是不是叶子节点
         *  完全二叉树，只要判断有没有左孩子
         *  转化成数组下标越界的问题去判断
         */
        int left = 2 * index + 1;
        if (left >= tree.length) {
            return;
        }

        /**
         * 不是叶子节点，意味着一定有左孩子，但不一定有右孩子
         * 2.找到最大的一个孩子
         *  1、没有右孩子     左孩子
         *  2、有右孩子
         *      1、左边大  左孩子
         *      2、右边大  右孩子
         */
        int right = 2 * index + 2;
        int max = left;
        if (right < tree.length && tree[right] > tree[left]) {
            max = right;
        }

        /**
         * 3.和根的值进行比较
         *  如果根的值比较大，满足堆的性质，不需要调整
         *  否则，交换数组中两个下标的值
         *  并且，继续以max作为下标，进行向下调整
         */
        if (tree[index] >= tree[max]) {
            return;
        }
        //根的值比较小，先交换
        int t = tree[index];
        tree[index] = tree[max];
        tree[max] = t;

        //继续向下调整
        heapify(tree, max);
    }

    //向下调整非递归（小堆）
    private static void adjustDown(int[] array, int index) {
        int min = 2 * index + 1;
        while (min < array.length) {
            if (min + 1 < array.length && array[min + 1] < array[min]) {
                min += 1;
            }

            if (array[index] <= array[min]) {
                break;
            }

            int t = array[min];
            array[min] = array[index];
            array[index] = t;

            index = min;
            min = 2 * index + 1;
        }
    }

    /**
     * 时间复杂度：O(n)
     * @param array
     */
    private static void createHeap(int[] array){
        //从最后一个非叶子节点的下标开始，一路向下调整至根的位置
        //[(array.lenth-2)/2,0]
        for (int i = (array.length-2)/2;i>=0;i--){
            heapify(array,i);
        }
    }

    public static void main(String[] args) {
        int[] array = {27, 15, 19, 18, 28, 34, 65, 49, 25, 37};
        adjustDown(array, 0);
        for (int item : array) {
            System.out.print(item + " ");
        }
    }
}
