package com.nanfeng;

/**
 * Author：nanfeng
 * Created:2019/5/4
 */

//时间复杂度：O(n^2)
public class BubbleSortYH {
   private static void bubbleSort(int[] array){
       //外部的循环表示冒泡的次数
       //一次冒泡可以解决一个数的问题
       //一共需要array.length
       //更优化的方式是：array.length-1
       for (int i=0;i<array.length;i++){
           //每次冒泡之前，假设数组已经有序
           boolean sorted = true;

           //一次冒泡的过程，保证最大的数被推到最后边
           for (int j=0;j<array.length-1-i;j++){
               //保证相邻的两个数最大的在后面
               if (array[j]>array[j+1]){
                   int t = array[j];
                   array[j] = array[j+1];
                   array[j+1] = t;
                   sorted = false;
               }
           }
           //如果过程中一次交换都没发生，假设有序成立
           if (sorted==true){
               break;
           }
       }
   }

    public static void main(String[] args) {
        int[] array = new int[]{1,5,3,6,8,2};
        bubbleSort(array);
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
}
