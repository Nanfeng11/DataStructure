package com.nanfeng;

import java.util.Arrays;

/**
 * Author：nanfeng
 * Created:2019/6/15
 */
public class Sort {
    /**
     * 时间复杂度
     * 最坏：O(n^2)    已经逆序
     * 平均：O(n^2)
     * 最好：O(n)    已经有序（倒着找的原因）
     * 空间复杂度
     * O(1)
     * 稳定性：稳定
     *
     * @param array
     */
    public static void insertSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            //有序区间[0,i)
            //无序区间[i,array.length)

            //1、在有序区间遍历查找，从后往前
            int j;
            for (j = i - 1; j >= 0 && array[i] < array[j]; j--) {

            }

            //j+1 就是要插入数据的下标
            //2、插入数据，从后往前搬移数据
            int pos = j + 1;
            int key = array[i];
            for (int k = i; k > pos; k--) {
                array[k] = array[k - 1];
            }

            array[pos] = key;
        }
    }

    public static void insertSort2(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            for (; j >= 0 && key < array[j + 1]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = key;
        }
    }

    public static void insertSort3(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int key = array[i];
            //有序[0,i)
            int left = 0;
            int right = i;

            //二分查找
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (key == array[mid]) {
                    left = mid + 1;
                } else if (key < array[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            //搬数据
            int pos = left;
            for (int k = i; k > pos; k--) {
                array[k] = array[k - 1];
            }
            array[pos] = key;
        }
    }

    private static void insertSortWithGap(int[] array, int gap) {
        for (int i = 0; i < array.length; i++) {
            int key = array[i];
            int j = i - gap;
            for (; j >= 0 && key < array[j + 1]; j = j - gap) {
                array[j + gap] = array[j];
            }
            array[j + gap] = key;
        }
    }

    /**
     * 时间复杂度
     * 最好：O(n)
     * 最坏：O(n^2)    概率变小
     * 平均：O(n^1.2-1.3)
     * 空间复杂度：O(1)
     * 稳定性：不稳定（很难保证相同的数据分到一个组里）
     *
     * @param array
     */
    public static void shellSort(int[] array) {
        int gap = array.length;
        while (true) {
            //gap = gap/2
            gap = (gap / 3) + 1;
            insertSortWithGap(array, gap);
            if (gap == 1) {
                break;
            }
        }
    }

    //选择排序
    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void selectSort(int[] array) {
        //每次选择一个最小的放到无序部分的最开始位置
        for (int i = 0; i < array.length; i++) {
            //有序[0,i)
            //无序[i,array.length)
            int min = i;    //记录最终最小数所在的下标
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }

            //最终交换一次
            swap(array, min, i);
        }
    }

    //堆排序
    private static void heapify(int[] array, int size, int index) {
        //1、判断index是不是叶子
        while (2 * index + 1 < size) {
            //2、找到最大的孩子的下标
            int max = 2 * index + 1;
            if (max + 1 < size && array[max + 1] > array[max]) {
                max = 2 * index + 2;
            }

            //3、判断最大的孩子和根的值
            if (array[index] < array[max]) {
                swap(array, index, max);
                index = max;
            } else {
                //4、根的值比较大，可以直接结束了
                //不交换，也不继续往下走了
                break;
            }
        }
    }

    private static void createHeap(int[] array) {
        //[最后一个非叶子节点的下标，根] 向下调整
        //[(array.length-2)/2,0]
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            heapify(array, array.length, i);
        }
    }

    public static void heapSort(int[] array) {
        //建堆 建大堆
        createHeap(array);
        //减治处理
        for (int i = 0; i < array.length; i++) {
            //无序[0,length-i-1)
            //有序[length-i,length)
            //最大的数在[0]，最大的数应该放到的下标是[length-1-i]
            swap(array, 0, array.length - 1 - i);
            //处理[0],无序剩余部分满足堆的性质
            //无序[0,lenght-i-2)
            //有序[lenght-i-1,length)
            //size  剩余无序部分的长度
            heapify(array, array.length - 1 - i, 0);
        }
    }

    //冒泡排序
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean isSorted = true;
            //最小的数冒泡到最前面
            //有序[0,i)
            //无序[i,length)
            //要把最小的数冒泡到最开始，需要从后往前冒泡
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    //快速排序

    /**
     * 时间复杂度
     * 最好：O(n*log(n))
     * 最坏：O(n^2) 选最边上作为基准值时，当数组已经有序或者数组逆序，都是最坏的情况
     * 平均：O(n*log(n))
     * 空间复杂度：
     * 最好：O(log n)
     * 最坏：O(n)
     * 平均：O(log n)
     * 稳定性：不稳定
     * @param array
     * @param left
     * @param right
     * @return
     */
    private static int parition1(int[] array,int left,int right){
        int begin = left;
        int end = right;
        int pivot = array[right];
        while (begin<end){
            while (begin<end && array[begin]<=pivot){
                begin++;
            }
            //array[begin]>pivot
            while (begin<end && array[end]>=pivot){
                end--;
            }
            //array[end]<pivot
            swap(array,begin,end);
        }
        //注意不要交换pivot的位置，pivot只是一个局部变量
        swap(array,begin,right);
        //返回基准值的位置
        return begin;
    }

    private static int parition2(int[] array,int left,int right){
        int begin = left;
        int end = right;
        int pivot = array[right];
        while (begin<end){
            while (begin<end && array[begin]<=pivot){
                begin++;
            }
            array[end] = array[begin];

            while (begin<end && array[end]>=pivot){
                end--;
            }
            array[begin] = array[end];

        }
        array[begin] = pivot;
        //返回基准值的位置
        return begin;
    }

    private static int parition3(int[] array,int left,int right){
        int j = left;
        for (int i=left;i<right;i++){
            if (array[i] < array[right]){
                swap(array,j,i);
                j++;
            }
        }
        swap(array,j,right);
        return j;
    }

    private static int sanShuQuZhong(int[] array,int left,int right){
        int mid = left + (right-left)/2;
        if (array[left]>array[right]){
            if (array[left]<array[mid]){
                return left;
            }else if (array[mid] > array[right]){
                return mid;
            }else {
                return right;
            }
        }else {
            if (array[right]<array[mid]){
                return right;
            }else if (array[mid]>array[left]){
                return mid;
            }else {
                return left;
            }
        }
    }

    private static void quickSortInner(int[] array,int left,int right){
        //直到size==1 || size==0
        if (left==right){
            //size==1
            return;
        }
        if (left>right){
            //size==0
            return;
        }
        //要排序的区间是array[left,right]
        //1、找基准值，array[right]
        int originIndex = sanShuQuZhong(array,left,right);
        swap(array,originIndex,right);
        //2、遍历整个区间，把区间分成三部分
        int pivotIndex = parition1(array,left,right);
        //比基准值小的[left,pivotIndex-1]
        //比基准值大的[pivotIndex+1,right]
        //3、分治算法
        //处理比基准值小的区间
        quickSortInner(array,left,pivotIndex-1);
        //处理比基准值大的区间
        quickSortInner(array,pivotIndex+1,right);
    }

    public static void quickSort(int[] array){
        quickSortInner(array,0,array.length-1);
    }

    public static void main(String[] args) {
        int[] array = {9, 3, 1, 5, 2, 4, 3, 8, 7, 6};
        insertSort(array);
        System.out.print(Arrays.toString(array));
    }
}
