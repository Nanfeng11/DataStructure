package com.nanfeng;

/**
 * Author：nanfeng
 * Created:2019/5/9
 */
public class MyStack {
    private int[] array;
    int top;

    MyStack() {
        this.array = new int[100];
        this.top = 0;
    }

    /**
     * 压入一个数据，插入一个数据
     * 压栈
     *
     * @param v
     */
    public void push(int v) {
        //尾插
        this.array[this.top++] = v;
    }

    /**
     * 弹栈，出栈
     *
     * @return 栈顶元素
     */
    public int pop() {
        //尾删
        return this.array[--this.top];
    }

    /**
     * 查看栈顶元素
     *
     * @return 栈顶元素
     */
    public int peek() {
        return this.array[this.top - 1];
    }

    public int size() {
        return this.top;
    }

    public boolean isEmpty() {
        return this.top == 0;
    }
}
