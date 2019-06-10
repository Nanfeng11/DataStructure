package com.nanfeng;

import java.util.LinkedList;

/**
 * Author：nanfeng
 * Created:2019/5/9
 */
public class Interview {
    //使用队列实现栈
    class MyStack {

        private LinkedList<Integer> queue;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            this.queue = new LinkedList<Integer>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            this.queue.addLast(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            //出栈的时候，把前面的数据(size-1)出栈再尾插到队尾，最后出掉最后一个元素
            int size = this.queue.size();
            for (int i = 0; i < size - 1; i++) {
                int v = this.queue.get(0);
                this.queue.remove(0);
                this.queue.addLast(v);
            }
            int v = this.queue.get(0);
            this.queue.remove(0);
            return v;
        }

        /**
         * Get the top element.
         */
        public int top() {
            int size = this.queue.size();
            for (int i = 0; i < size - 1; i++) {
                int v = this.queue.get(0);
                this.queue.remove(0);
                this.queue.addLast(v);
            }
            int v = this.queue.get(0);
            this.queue.remove(0);
            this.queue.addLast(v);
            return v;
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return this.queue.size() == 0;
        }
    }

    //设计循环队列（用数组实现队列）
    class MyCircularQueue {

        private int[] array;
        private int size;
        private int front;      //队首元素的下标
        private int rear;       //下一个可用位置的下标

        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            this.array = new int[k];
            this.size = 0;
            this.front = 0;
            this.rear = 0;
        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if (this.size == this.array.length){
                return false;
            }
            this.array[this.rear] = value;
            this.rear = (this.rear+1)%this.array.length;
            this.size++;
            return true;
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if (this.size==0){
                return false;
            }
            this.front = (this.front+1) % this.array.length;
            this.size--;
            return true;
        }

        /** Get the front item from the queue. */
        public int Front() {
            if (this.size==0){
                return -1;
            }
            return this.array[this.front];
        }

        /** Get the last item from the queue. */
        public int Rear() {
            if (this.size==0){
                return -1;
            }
            int index = (this.rear-1+this.array.length)%this.array.length;
            return this.array[index];
        }

        public int Rear2(){
            if (this.size==0){
                return -1;
            }
            int index = this.rear==0?this.array.length-1:this.rear-1;
            return this.array[index];
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return this.size==0;
        }

        public boolean isEmpty2() {
            return this.rear == this.front;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return this.size==this.array.length;
        }

        public boolean isFull2(){
            if ((this.rear+1)%this.array.length == this.front){
                return true;
            }
            return false;
        }
    }

}
