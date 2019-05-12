package com.nanfeng;


import java.util.Stack;

/**
 * Author：nanfeng
 * Created:2019/5/9
 */
public class Interview {
    //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '(':
                case '[':
                case '{':
                    stack.push(ch);
                    break;
                case ')':
                case ']':
                case '}': {
                    if (stack.isEmpty()) {
                        return false;
                    }

                    char left = stack.pop();
                    if (!((left == '(' && ch == ')') || (left == '[' && ch == ']') || (left == '{' && ch == '}'))) {
                        return false;
                    }
                    break;
                }
                default:
                    break;
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    //用两个栈实现一个队列
    class MyQueue {

        Stack<Integer> in;
        Stack<Integer> out;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            in = new Stack<Integer>();
            out = new Stack<Integer>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            in.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    int v = in.pop();
                    out.push(v);
                }
            }
            int v = out.pop();
            return v;
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    int v = in.pop();
                    out.push(v);
                }
            }
            int v = out.peek();
            return v;
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }
    }

    //实现一个最小栈（空间换时间）
    class MinStack {

        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {

        }

        public void pop() {

        }

        public int top() {

        }

        public int getMin() {

        }
    }

}
