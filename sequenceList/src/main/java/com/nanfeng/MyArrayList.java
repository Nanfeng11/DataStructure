package com.nanfeng;

/**
 * Author：nanfeng
 * Created:2019/5/6
 */
public class MyArrayList implements IArrayList {
    private int[] array;    //保存数据的空间
    private int size;       //保存有效数据个数

    MyArrayList(int capacity){
        this.array = new int[capacity];
        this.size = 0;
    }

    //时间复杂度O(n)
    public void pushFront(int item) {
        ensureCapacity();
        //将顺序表中已有的数据后移一格
        for (int i=this.size;i>=1;i--){
            this.array[i] = this.array[i-1];
        }
        this.array[0] = item;
        this.size++;
    }

    //时间复杂度O(1)---扩容后变成O(n)
    public void pushBack(int item) {
        ensureCapacity();
        this.array[this.size] = item;
        this.size++;
    }

    //时间复杂度O(n)---最坏情况下，index=0，相当于头插
    public void add(int item, int index) {
        ensureCapacity();
        if (index<0 || index>this.size){
            throw new Error();
        }
        for (int i=this.size;i>=index+1;i--){
            this.array[i] = this.array[i-1];
        }
        this.array[index] = item;
        this.size++;
    }

    //时间复杂度O(n)
    public void popFront() {
        if (this.size == 0){
            throw new Error();
        }
        for (int i=1;i<=this.size-1;i++){
            this.array[i-1] = this.array[i];
        }
        this.array[--this.size] = 0;
    }

    //时间复杂度O(1)
    public void popBack() {
        if (this.size==0){
            throw new Error();
        }
        this.array[--this.size] = 0;
    }

    //时间复杂度O(n)---头删
    public void remove(int index) {
        if (index<0 || index>=this.size){
            throw new Error();
        }
        if (this.size == 0){
            throw new Error();
        }
        for (int i=index+1;i<=this.size-1;i++){
            this.array[i-1] = this.array[i];
        }
        this.size--;
    }

    public void print(){
        for (int i=0;i<size;i++){
            System.out.print(array[i]+" ");
        }
    }

    /**
     * 保证数组容量够用
     * 时间复杂度O(n)
     */
    private void ensureCapacity(){
        if (this.size<this.array.length){
            return;
        }
        //1、找新房子，找原来的2倍大小
        int capacity = this.array.length*2;
        int[] newArray = new int[capacity];
        //2、搬家
        for (int i=0;i<this.size;i++){
            newArray[i] = this.array[i];
        }
        //3、去学校登记新房子位置，退掉老房子
        this.array = newArray;
    }
}
