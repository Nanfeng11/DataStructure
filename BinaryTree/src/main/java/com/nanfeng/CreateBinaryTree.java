package com.nanfeng;

import javafx.util.Pair;

import java.util.Arrays;

/**
 * Author：nanfeng
 * Created:2019/5/20
 */
public class CreateBinaryTree {
    static class Node{
        char value;
        Node left;
        Node right;

        public Node(char value){
            this.value = value;
            this.left = this.right=null;
        }
    }

    private static Pair<Node,Integer> createTree(char[] preOrder){
        if (preOrder.length==0){
            return new Pair<Node, Integer>(null,0);
        }
        if (preOrder[0]=='#'){
            return new Pair<Node, Integer>(null,1);
        }

        Node root = new Node(preOrder[0]);
        Pair<Node,Integer> left = createTree(Arrays.copyOfRange(preOrder,1,preOrder.length));
        Pair<Node,Integer> right = createTree(Arrays.copyOfRange(preOrder,1+left.getValue(),preOrder.length));
        root.left = left.getKey();
        root.right = right.getKey();
        return new Pair<Node, Integer>(root,1+left.getValue()+right.getValue());
    }

    private static void preOrderTraversal(Node root){
        if (root!=null){
            System.out.print(root.value);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    private static void inOrderTraversal(Node root){
        if (root!=null){
            inOrderTraversal(root.left);
            System.out.print(root.value);
            inOrderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        char[] preOrder = new char[]{
                'a','b','d','#','#','e','#','#','c','#','f'
        };

        Pair<Node,Integer> pair = createTree(preOrder);
        Node root = pair.getKey();
        preOrderTraversal(root);
        System.out.println();
        inOrderTraversal(root);
        System.out.println();
    }
}
