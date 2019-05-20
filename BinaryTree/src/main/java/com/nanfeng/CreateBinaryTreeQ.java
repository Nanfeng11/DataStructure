package com.nanfeng;

import java.util.Arrays;

/**
 * Author：nanfeng
 * Created:2019/5/14
 */

/**
 * 为了实现一个函数返回两个值的效果
 * 把两个不同的返回值打包到一个类中
 */
class CreateTreeReturnValue{
    /**
     * 创建好的二叉树的根节点
     */
    public Node returnRoot;

    /**
     * 使用的个数
     */
    public int used;

    CreateTreeReturnValue(Node returnRoot,int used){
        this.returnRoot = returnRoot;
        this.used = used;
    }
}

public class CreateBinaryTreeQ {

    //1、根据前序遍历创建二叉树
    private static CreateTreeReturnValue createTree(char[] preOrder){

        //ABC#D####E##(E之后就是空数组，相当于创建一棵空树)
        if (preOrder.length==0){
            return new CreateTreeReturnValue(null,0);
        }

        //1、根节点
        char rootValue = preOrder[0];
        if (rootValue == '#'){
            return new CreateTreeReturnValue(null,1);
        }
        Node root = new Node(rootValue);

        //2、左子树，利用递归
        char[] leftPreOrder = new char[preOrder.length-1];
        leftPreOrder = Arrays.copyOfRange(preOrder,1,preOrder.length);
        CreateTreeReturnValue leftReturn = createTree(leftPreOrder);

        //3、右子树，利用递归
        //length-根的长度-左子树的长度
        char[] rightPreOrder = new char[preOrder.length-1-leftReturn.used];
        rightPreOrder = Arrays.copyOfRange(preOrder,1+leftReturn.used,preOrder.length);
        CreateTreeReturnValue rightReturn = createTree(rightPreOrder);

        //绑定左右子树和根
        root.left = leftReturn.returnRoot;
        root.right = rightReturn.returnRoot;

        //创建好的树的根节点是root
        //使用的个数是：根使用的(1)+左子树使用的(leftReturn.used)+右子树使用的(leftReturn.used)
        return new CreateTreeReturnValue(root,1+leftReturn.used+rightReturn.used);

    }

    public static void main(String[] args) {
        char[] preOrder = new char[]{
          'A','B','C','#','D','#', '#','#','E'
        };
        CreateTreeReturnValue returnValue = createTree(preOrder);
        System.out.println(returnValue.used);
    }
}
