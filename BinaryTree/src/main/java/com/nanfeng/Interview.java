package com.nanfeng;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Author：nanfeng
 * Created:2019/5/14
 */
public class Interview {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private ArrayList<Integer> list;

    //给定一个二叉树，返回它的 前序 遍历
    private void preorder(TreeNode root) {
        if (root != null) {
            list.add(root.val);
            preorder(root.left);
            preorder(root.right);
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        list = new ArrayList<Integer>();
        preorder(root);
        return list;
    }

    //中序
    private void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            list.add(root.val);
            inorder(root.right);
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        list = new ArrayList<Integer>();
        inorder(root);
        return list;
    }

    //后序
    private void postorder(TreeNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            list.add(root.val);
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        list = new ArrayList<Integer>();
        postorder(root);
        return list;
    }

    //给定两个二叉树，编写一个函数来检验它们是否相同。
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null && q==null){
            return true;
        }
        if (p==null || q==null){
            return false;
        }
        return p.val == q.val
                && isSameTree(p.left,q.left)
                && isSameTree(p.right,q.right);
    }

    //如何判断两棵树互为镜像
    public boolean isMirror(TreeNode p,TreeNode q){
        if (p==null && q==null){
            return true;
        }
        if (p==null || q==null){
            return false;
        }
        return p.val == q.val
                && isMirror(p.left,q.right)
                && isMirror(p.right,q.left);
    }

    //给定一个二叉树，检查它是否是镜像对称的
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        return isMirror(root.left,root.right);
    }

    //另一棵树的子树
    private boolean isSame(TreeNode p,TreeNode q){
        if (p==null && q==null){
            return true;
        }
        if (p==null || q==null){
            return false;
        }
        return p.val == q.val
                && isSame(p.left,q.left)
                && isSame(p.right,q.right);
    }
    private boolean find(TreeNode root,TreeNode t){
        if (root==null){
            return false;
        }
        if (isSame(root,t)){
            return true;
        }
        if (find(root.left,t)==true){
            return true;
        }
        return find(root.right,t);
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return find(s,t);
    }

    //给定一个二叉树，判断它是否是高度平衡的二叉树
    public int height(TreeNode root){
        if (root==null){
            return 0;
        }
        return Math.max(height(root.left),height(root.right))+1;
    }
    public boolean isBalanced(TreeNode root) {
        if (root==null){
            return true;
        }
        if (isBalanced(root.left)==false){
            return false;
        }
        if (isBalanced(root.right)==false){
            return false;
        }
        int left = height(root.left);
        int right = height(root.right);
        int diff = left-right;
        if (diff<-1 || diff>1){
            return false;
        }
        return true;
    }
}
