package com.nanfeng;

/**
 * Author：nanfeng
 * Created:2019/5/9
 */
public class MyQueue {
    private class Node{
        int value;
        Node next;
    }

    Node head;
    Node last;

    MyQueue(){
        this.head=this.last=null;
    }

    /**
     * 把数据插入到队尾（尾插）
     * @param v
     */
    public void push(int v){
        Node node = new Node();
        node.value=v;
        node.next=null;
        if (this.head==null){
            this.head=this.last=node;
        }else {
            this.last.next=this.head=node;
        }
    }

    /**
     * 出队列(头删)
     * @return
     */
    public int pop(){
        int v = this.head.value;
        if (this.head==null){
            this.last=null;
        }
        this.head=this.head.next;
        return v;
    }

    /**
     * 返回队首元素
     * @return
     */
    public int front(){
        return this.head.value;
    }

    public int size(){
        Node cur = this.head;
        int len = 0;
        while (cur!=null){
            len++;
            cur=cur.next;
        }
        return len;
    }

    public boolean isEmpty(){
        return this.head==null;
    }
}
