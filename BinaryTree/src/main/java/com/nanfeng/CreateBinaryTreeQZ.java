package com.nanfeng;

import java.util.Arrays;

/**
 * Author：nanfeng
 * Created:2019/5/20
 */
public class CreateBinaryTreeQZ {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static int find(char[] array, int v){
        for (int i=0;i<array.length;i++){
            if (array[i] == v){
                return i;
            }
        }
        return -1;
    }

    private static TreeNode buildTree(char[] preorder, char[] inorder) {

        if (preorder.length==0){
            return null;
        }

        //1、根据前序，找到根的值，并且创建根节点
        char rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);

        //2、在中序中找到根的值所在的下标
        int leftSize = find(inorder,rootValue);

        //3、切出左子树的前序和中序
        char[] leftPreorder = Arrays.copyOfRange(preorder,1,1+leftSize);
        char[] leftInorder = Arrays.copyOfRange(inorder,0,leftSize);
        root.left = buildTree(leftPreorder,leftInorder);

        //4、切出右子树的前序和中序
        char[] rightPreorder = Arrays.copyOfRange(preorder,1+leftSize,preorder.length);
        char[] rightInorder = Arrays.copyOfRange(inorder,leftSize+1,preorder.length);
        root.right = buildTree(rightPreorder,rightInorder);

        return root;
    }

    public static void main(String[] args) {
        char[] preOrder = new char[]{'A','B','C','D','E'};
        char[] inorder = new char[]{'C','D','B','A','E'};
        TreeNode root = buildTree(preOrder,inorder);
        System.out.println("创建成功");
    }
}
