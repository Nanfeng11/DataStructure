package com.nanfeng;

/**
 * Author：nanfeng
 * Created:2019/5/14
 */
public class BinaryTree {
    private static class Node {
        char value;
        Node left;
        Node right;

        Node(char v) {
            this.value = v;
        }
    }

    //当树是空树，node=null
    private static void preOrderTraversal(Node root) {
        if (root != null) {
            //根 左子树的前序遍历 右子树的前序遍历
            System.out.print(root.value + " ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    private static void inOrderTraversal(Node root) {
        if (root != null) {
            //左子树的中序遍历 根 右子树的中序遍历
            inOrderTraversal(root.left);
            System.out.print(root.value + " ");
            inOrderTraversal(root.right);
        }
    }

    private static void postOrderTraversal(Node root) {
        if (root != null) {
            //左子树的后序遍历 右子树的后序遍历 根
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.value + " ");
        }
    }

    //求二叉树节点个数
    private static int count = 0;
    //用遍历的思想解决问题
    private static int countByTraversal(Node root) {
        if (root != null) {
            count++;
            countByTraversal(root.left);
            countByTraversal(root.right);
        }
        return count;
    }
    //用子问题的思想解决
    private static int count(Node root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            //可选
            return 1;
        } else {
            return count(root.left) + count(root.right) + 1;
        }
    }

    //求叶子节点个数
    //用遍历的思想解决问题
    private static int getLeafSize(Node root){
        if (root!=null){
            if (root.left==null && root.right==null){
                count++;
            }
            getLeafSize(root.left);
            getLeafSize(root.right);
        }
        return count;
    }
    //用子问题的思想解决
    private static int getLeafSize2(Node root){
        if (root==null){
            return 0;
        }else if (root.left==null && root.right==null){
            return 1;
        }else {
            return getLeafSize2(root.left)+getLeafSize2(root.right);
        }
    }

    //求二叉树的高度
    private static int height(Node root){
        //空树
        if (root==null){
            return 0;
        }else if (root.left==null && root.right==null){
            //一个节点，可选
            return 1;
        }else {
            //其它：max(left,right)+1
            int left = height(root.left);
            int right = height(root.right);
            return (left>right?left:right)+1;
        }
    }

    //求第k层节点个数
    private static int getKLevelSize(Node root,int k){
        if (root==null){
            return 0;
        }else if (k==1){
            return 1;
        }else {
            return getKLevelSize(root.left,k-1)+getKLevelSize(root.right,k-1);
        }
    }

    //查找，依次在二叉树的 根、左子树、右子树 中查找 value，如果找到，返回结点，否则返回 null
//    private static Node find(Node root,char v){
//        Node r;
//        if (root==null){
//            return null;
//        }else if (root.value==v){
//            return root;
//        }else if ((r=find(root.left,v))!=null){
//            return r;
//        }else if ((r=find(root.right,v))!=null){
//            return r;
//        }else {
//            return null;
//        }
//    }
    private static Node find(Node root,char v){
        //树为空
        if (root==null){
            return null;
        }
        //根节点是要查找的节点
        if (root.value==v){
            return root;
        }
        //去左子树查找
        Node r = find(root.left,v);
        if (r!=null){
            return r;   //返回左子树中找到的节点引用
        }
        //去右子树查找
//        r=find(root.right,v);
//        if (r!=null){
//            return r;   //返回右子树中找到的节点引用
//        }
//        return null;
        //左子树没找到，去右子树查找
        return find(root.right,v);
    }

    private static Node createTestTree() {
        Node a = new Node('A');
        Node b = new Node('B');
        Node c = new Node('C');
        Node d = new Node('D');
        Node e = new Node('E');
        Node f = new Node('F');
        Node g = new Node('G');
        Node h = new Node('H');
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        e.right = h;
        return a;
    }

    public static void main(String[] args) {
        Node root = createTestTree();
        preOrderTraversal(root);
        System.out.println();
        inOrderTraversal(root);
        System.out.println();
        postOrderTraversal(root);
        System.out.println();

        System.out.println("二叉树叶子节点个数："+getLeafSize2(root));
        System.out.println("二叉树高度："+height(root));
    }
}
