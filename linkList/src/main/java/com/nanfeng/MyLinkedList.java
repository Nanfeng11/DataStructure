package com.nanfeng;

/**
 * Author：nanfeng
 * Created:2019/5/6
 */
public class MyLinkedList {
    /**
     * 内部类：链表中的一个节点
     */
    public class Node {
        public int value;   //保存的是有效数据
        public Node next;   //下一个节点的线索（引用）

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    //如果一个节点都没有，head==null
    private Node head;  //保存链表中第一个节点的引用

    MyLinkedList() {
        this.head = null;
    }

    //头插：时间复杂度O(1)
    public void pushFront(int item) {
        Node node = new Node(item);
        node.next = this.head;
        this.head = node;
    }

    //头删：时间复杂度O(1)
    public void popFront() {
        if (this.head == null) {
            throw new Error();
        }
        this.head = this.head.next;
    }

    //尾插
    //时间复杂度O(n)
    private Node getLast() {
        Node cur = this.head;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    //时间复杂度O(n)
    public void pushBack(int item) {
        Node node = new Node(item);
        if (this.head == null) {
            this.head = node;
        } else {
            Node last = getLast();
            last.next = node;
        }
    }

    /**
     * 尾删
     * 找倒数第二个
     * 时间复杂度O(n)
     *
     * @return
     */
    private Node getPenult() {
        Node cur = this.head;
        while (cur.next.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    //时间复杂度O(n)
    public void popBack() {
        if (this.head == null) {
            throw new Error();
        }
        if (this.head.next == null) {
            this.head = null;
        } else {
            Node penult = getPenult();
            penult.next = null;
        }
    }

    //任意位置插入
    boolean addIndex(int index, int v) {
        Node node = new Node(v);
        //如果指定插入位置为0，相当于头插
        if (index == 0) {
            node.next = this.head;
            this.head = node;
        } else {
            Node cur = this.head;
            //1 2 3 4 5 6，在数据4的位置，也就是下标为3的位置插入一个数据，需要先找到数据3，挪动2步，即index-1
            //如果要在下标为6的位置插入数据，不存在
            for (int i = 0; cur != null && i < index - 1; i++) {
                cur = cur.next;
            }

            if (cur == null) {
                return false;
            }

            node.next = cur.next;
            cur.next = node;
        }
        return true;
    }

    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int v) {
        for (Node c = this.head; c != null; c = c.next) {
            if (c.value == v) {
                return true;
            }
        }
        return false;
    }

    //删除第一次出现关键字为key的节点
    public void remove(int v) {
        if (this.head == null) {
        } else if (this.head.value == v) {
            this.head = this.head.next;
        } else {
            Node prev = this.head;
            while (prev.next != null) {
                if (prev.next.value == v) {
                    prev.next = prev.next.next;
                }
            }
        }
    }

    //删除所有值为key的节点
    public void removeAll(int v){
        Node result = null;
        Node last = null;
        Node cur = this.head;
        while (cur!=null){
            Node next = cur.next;
            if (cur.value!=v){
                //尾插到result链表
                cur.next=null;
                if (result==null){
                    result=cur;
                }else {
                    last.next=cur;
                }
                last=cur;
            }
            cur=next;
        }
    }

    public int getLength(){
        Node cur = this.head;
        int len = 0;
        while (cur!=null){
            len++;
            cur=cur.next;
        }
        return len;
    }

    //依次打印链表中每一个结点
    public void display() {
        //如何通过循环遍历链表的每一个结点
        Node cur = this.head;
        while (cur != null) {
            System.out.format("%d-->", cur.value);
            cur = cur.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.pushFront(1);
        linkedList.pushFront(2);
        linkedList.pushFront(3);
//        linkedList.display();
        linkedList.pushBack(10);
        linkedList.pushBack(20);
        linkedList.pushBack(30);
        linkedList.display();
    }
}
