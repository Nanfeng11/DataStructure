package com.nanfeng;

import java.util.Arrays;

/**
 * Author：nanfeng
 * Created:2019/5/20
 */
public class CreateBinaryTreeZH {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static int find(char[] array, int v) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == v) {
                return i;
            }
        }
        return -1;
    }

    private static TreeNode buildTree(char[] inorder, char[] postorder) {

        if (postorder.length == 0) {
            return null;
        }

        //1、根据后序，找到根的值，并且创建根节点
        char rootValue = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootValue);

        //2、在中序中找到根的值所在的下标
        int leftSize = find(inorder, rootValue);

        //3、切出左子树的中序和后序
        char[] leftInorder = Arrays.copyOfRange(inorder, 0, leftSize);
        char[] leftPostorder = Arrays.copyOfRange(postorder, 0, leftSize);
        root.left = buildTree(leftInorder, leftPostorder);

        //4、切出右子树的中序和后序
        char[] rightInorder = Arrays.copyOfRange(inorder, leftSize+1, inorder.length);
        char[] rightPostorder = Arrays.copyOfRange(postorder, leftSize, postorder.length-1);
        root.right = buildTree( rightInorder,rightPostorder);

        return root;
    }

    public static void main(String[] args) {
        char[] inorder = new char[]{'C', 'D', 'B', 'A', 'E'};
        char[] postorder = new char[]{'D', 'C', 'B', 'E', 'A'};
        TreeNode root = buildTree(inorder,postorder);
        System.out.println("创建成功");
    }
}
