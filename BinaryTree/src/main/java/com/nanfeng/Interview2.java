package com.nanfeng;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Author：nanfeng
 * Created:2019/5/25
 */
public class Interview2 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //1、二叉树创建字符串。
    private static void preOrderTree2Str(TreeNode root, StringBuilder sb) {
        if (root != null) {
            sb.append('(');
            sb.append(root.val);
            if (root.left == null && root.right != null) {
                sb.append("()");
            } else {
                preOrderTree2Str(root.left, sb);
            }
            preOrderTree2Str(root.right, sb);
            sb.append(')');
        }
    }

    public static String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        preOrderTree2Str(root, sb);
        sb.delete(0, 1);
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    //2、寻找最近的公共祖先
    private boolean find(TreeNode root, TreeNode t) {
        if (root == null) {
            return false;
        }

        if (root == t) {
            return true;
        }

        if (find(root.left, t)) {
            return true;
        }

        return find(root.right, t);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        boolean pInleft = find(root.left, p);
        boolean qInleft = find(root.left, q);
        if (pInleft && qInleft) {
            return lowestCommonAncestor(root.left, p, q);
        }

        if (!pInleft && !qInleft) {
            return lowestCommonAncestor(root.right, p, q);
        }

        return root;
    }

    //3、层序遍历
    public void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        //启动
        queue.addLast(root);

        //拉下线整个过程
        while (!queue.isEmpty()) {
            TreeNode front = queue.pollFirst();
            System.out.print(front);

            //拉下线，有要求，空的不要
            if (front.left != null) {
                queue.addLast(front.left);
            }
            if (front.right != null) {
                queue.addLast(front.right);
            }
        }
    }

    //3.1给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        class NodeLevel {
            TreeNode node;
            int level;

            public NodeLevel(TreeNode node, int level) {
                this.node = node;
                this.level = level;
            }
        }

        LinkedList<NodeLevel> queue = new LinkedList<NodeLevel>();
        queue.addLast(new NodeLevel(root, 0));

        while (!queue.isEmpty()) {
            NodeLevel front = queue.pollFirst();
            TreeNode node = front.node;
            int level = front.level;
            //中间的遍历
            if (list.size() == level) {
                list.add(new ArrayList<Integer>());
            }
            list.get(level).add(node.val);

            if (node.left != null) {
                queue.addLast(new NodeLevel(node.left, level + 1));
            }
            if (node.right != null) {
                queue.addLast(new NodeLevel(node.right, level + 1));
            }
        }
        return list;
    }

    //3.2判断一棵树是否是完全二叉树
    private static boolean isComplete(TreeNode root){
        if (root==null){
            return true;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);

        while (true){
            TreeNode front = queue.pollFirst();
            if (front==null){
                //遇到空，下一步去判断队列中剩余的节点，是否全是空
                break;
            }
        }

        //判断所有节点是否是非空
        while (!queue.isEmpty()){
            TreeNode front = queue.pollFirst();
            //意味着空遇到非空
            if (front!=null){
                return false;
            }
        }
        return true;
    }

    //4、二叉搜索树转成双向链表
    private static TreeNode prev = null;
    private static TreeNode head = null;
    private static void buildDList(TreeNode node){
        node.left = prev;
        if (prev!=null){
            prev.right = node;
        }else {
            head = node;
        }
        prev = node;
    }
    private static void inOrderTraversalSearchTree(TreeNode root){
        if (root!=null){
            inOrderTraversalSearchTree(root.left);
            buildDList(root);
            inOrderTraversalSearchTree(root.right);
        }
    }
    public TreeNode Convert(TreeNode pRootOfTree) {
        prev = null;
        head = null;
        inOrderTraversalSearchTree(pRootOfTree);
        return head;
    }

    //5、非递归前序遍历
    private static void preOrderNoR(TreeNode root){
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.empty() || cur!=null){
            //优先走左边
            while (cur!=null){
                //第一次遇到cur这个节点的位置
                System.out.print(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            //向左走到头了需要回溯
            //从栈里取出节点进行回溯
            //目前栈里的所有节点的右子树都没有进行遍历
            TreeNode top = stack.pop();
            cur = top.right;
        }
    }

    //5、非递归中序遍历
    private static void inOrderNoR(TreeNode root){
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.empty() || cur!=null){
            //优先走左边
            while (cur!=null){
                //第一次遇到cur这个节点的位置
                stack.push(cur);
                cur = cur.left;
            }
            //向左走到头了需要回溯
            //从栈里取出节点进行回溯
            //目前栈里的所有节点的右子树都没有进行遍历
            TreeNode top = stack.pop();
            //top取出的节点，是第二次遇到该节点
            System.out.print(top.val);
            cur = top.right;
        }
    }

    //5、非递归后序遍历
    private static void postOrderNoR(TreeNode root){
        TreeNode cur = root;
        TreeNode last = null;       //上一次被完整后序遍历过的树的根节点
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.empty() || cur!=null){
            //优先走左边
            while (cur!=null){
                //第一次遇到cur这个节点的位置
                stack.push(cur);
                cur = cur.left;
            }
            //向左走到头了需要回溯
            //从栈里取出节点进行回溯
            //目前栈里的所有节点的右子树都没有进行遍历
            TreeNode top = stack.peek();
            if (top.right==null){
                System.out.print(top.val);
                stack.pop();
                last = top;
            }else if (top.right==last){
                System.out.print(top.val);
                stack.pop();
                last = top;
            }else {
                cur = top.right;
            }
        }
    }
}
