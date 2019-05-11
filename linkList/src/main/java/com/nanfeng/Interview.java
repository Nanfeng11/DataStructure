package com.nanfeng;


/**
 * Author：nanfeng
 * Created:2019/5/6
 */
public class Interview {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private static class Solution {
        //删除链表里所有值为val的结点
        public ListNode removeElements(ListNode head, int val) {
            ListNode result = null;     //结果链表，没有结点
            ListNode last = null;       //记录结果链表的最后一个结点
            ListNode cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                if (cur.val != val) {
                    //把cur结点尾插到result链表上
                    cur.next = null;
                    if (result == null) {
                        result = cur;
                    } else {
                        last.next = cur;
                    }
                    //更新结果链表的最后一个结点
                    last = cur;
                }
                cur = next;
            }
            return result;
        }

        public ListNode removeElements2(ListNode head,int val){
            if (head==null){
                return null;
            }

            ListNode cur = head;
            while (cur.next!=null){
                if(cur.next.val!=val){
                    cur = cur.next;
                }else {
                    cur.next = cur.next.next;
                }
            }
            if (head.val==val){
                return head.next;
            }else {
                return head;
            }
        }

        //反转链表
        public ListNode reverseList(ListNode head) {
            ListNode result = null;
            ListNode cur = head;
            while(cur!=null){
                ListNode next = cur.next;
                cur.next = result;
                result = cur;
                cur = next;
            }
            return result;
        }

        public ListNode reverseList2(ListNode head){
            if (head==null){
                return null;
            }
            ListNode prev = null;
            ListNode cur = head;
            while (cur!=null){
                ListNode next = cur.next;
                cur.next=prev;
                prev=cur;
                cur=next;
            }
            return prev;
        }

        //合并两条链表
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if(l1==null){
                return l2;
            }
            if(l2==null){
                return l1;
            }
            ListNode cur1 = l1;
            ListNode cur2 = l2;
            ListNode result = null;     //结果链表的第一个结点
            ListNode last = null;       //记录最后一个结点

            while(cur1!=null && cur2!=null){
                if(cur1.val <= cur2.val){
                    ListNode next = cur1.next;

                    //把cur1尾插到result上
                    cur1.next = null;
                    if(result==null){
                        result = cur1;
                    }else{
                        last.next = cur1;
                    }
                    last = cur1;

                    cur1 = next;
                }else{
                    ListNode next = cur2.next;

                    //把cur2尾插到result上
                    cur2.next = null;
                    if(result==null){
                        result = cur2;
                    }else{
                        last.next = cur2;
                    }
                    last = cur2;

                    cur2 = next;
                }
            }
            if(cur1!=null){
                last.next = cur1;
            }
            if(cur2!=null){
                last.next = cur2;
            }
            return result;
        }

        //编写代码，以给定值x为基准将链表分割成两部分，所有小于x的结点排在大于或等于x的结点之前
        public ListNode partition(ListNode pHead, int x) {
            ListNode small = null;          //小于x
            ListNode smallLast = null;      //小于x的最后一个结点
            ListNode big = null;            //大于等于x
            ListNode bigLast = null;        //大于等于x的最后一个结点

            ListNode cur = pHead;
            while (cur != null) {
                ListNode next = cur.next;
                //小于x的尾插到small，大于等于x的尾插到big
                if (cur.val < x) {
                    cur.next = null;
                    if (small == null) {
                        small = cur;
                    } else {
                        smallLast.next = cur;
                    }
                    smallLast = cur;
                } else {
                    cur.next = null;
                    if (big == null) {
                        big = cur;
                    } else {
                        bigLast.next = cur;
                    }
                    bigLast = cur;
                }
                cur = next;
            }
            //考虑链表为空的情况
            if (small == null) {
                return big;
            } else {
                smallLast.next = big;
                return small;
            }
        }

        //给定一个带有头结点 head 的非空单链表，返回链表的中间结点。如果有两个中间结点，则返回第二个中间结点
        //(快(一次走2步)慢(一次走1步)引用遍历，如果快引用为空，慢引用就是中间值)
        public ListNode middleNode(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null) {
                fast = fast.next;
                if (fast == null) {
                    break;
                }
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }

        //输入一个链表，输出该链表中倒数第k个结点
        //前后引用，后引用先提前走k步
        public ListNode FindKthToTail(ListNode head, int k) {
            ListNode front = head;
            ListNode back = head;

            int i;
            for (i = 0; front != null && i < k; i++) {
                front = front.next;
            }

            if (front == null && i < k) {
                //k大于结点个数
                return null;
            } else if (front == null) {
                return head;
            }

            while (front != null) {
                front = front.next;
                back = back.next;
            }
            return back;
        }

        //判断是否为回文
        public int length(ListNode head) {
            ListNode cur = head;
            int len = 0;
            while (cur != null) {
                len++;
                cur = cur.next;
            }
            return len;
        }

        public ListNode reverse(ListNode head) {
            ListNode result = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode next = cur.next;

                //头插
                cur.next = result;
                result = cur;

                cur = next;
            }
            return result;
        }


        public boolean chkPalindrome(ListNode A) {
            int len = length(A);
            int halfLen = len / 2;

            //找中间结点
            ListNode middle = A;
            for (int i = 0; i < halfLen; i++) {
                middle = middle.next;
            }

            ListNode r = reverse(middle);
            ListNode c1 = A;
            ListNode c2 = r;

            while (c1 != null && c2 != null) {
                if (c1.val != c2.val) {
                    return false;
                }
                c1 = c1.next;
                c2 = c2.next;
            }
            return true;
        }

        //在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针
        public ListNode deleteDuplication(ListNode pHead) {
            if (pHead == null) {
                return null;
            }

            //消除第一个结点没有前驱的特殊性
            ListNode dummy = new ListNode(0);
            dummy.next = pHead;
            ListNode prev = dummy;      //prev永远是p1的前驱结点，用来删除结点
            //p1和p2是进行比较的两个节点
            ListNode p1 = pHead;
            ListNode p2 = pHead.next;

            while (p2 != null) {
                if (p1.val != p2.val) {
                    //因为有序，p1和p2不等，和p2的next更不会相等
                    prev = prev.next;
                    p1 = p1.next;
                    p2 = p2.next;
                } else {
                    while (p2 != null && p2.val == p1.val) {
                        p2 = p2.next;
                    }

                    //删除结点
                    prev.next = p2;

                    p1 = p2;
                    if (p2 != null) {
                        p2 = p2.next;
                    }
                }
            }
            return dummy.next;
        }

        //找到两个单链表相交的起始节点
        private int getLength(ListNode head) {
            int length = 0;
            for (ListNode cur = head; cur != null; cur = cur.next) {
                length++;
            }
            return length;
        }

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            int lenA = getLength(headA);
            int lenB = getLength(headB);
            ListNode longer = headA;
            ListNode shorter = headB;
            int diff = lenA - lenB;
            if (lenA < lenB) {
                diff = lenB - lenA;
                longer = headB;
                shorter = headA;
            }

            for (int i = 0; i < diff; i++) {
                longer = longer.next;
            }

            while (longer != shorter) {
                longer = longer.next;
                shorter = shorter.next;
            }

            return longer;
        }

        //给定一个链表，判断链表中是否有环。
        public boolean hasCycle(ListNode head){
            if (head==null){
                return false;
            }

            ListNode fast = head;
            ListNode slow = head;

            do {
                fast = fast.next;
                if (fast!=null){
                    fast=fast.next;
                    slow=slow.next;
                }
            }while (fast!=null && fast!=slow);

            if (fast==null){
                return false;
            }

            return true;
        }

        //给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
        public ListNode detectCycle(ListNode head) {
            if (head == null) {
                return null;
            }

            ListNode fast = head;
            ListNode slow = head;

            do {
                fast = fast.next;
                if (fast != null) {
                    fast = fast.next;
                    slow = slow.next;
                }
            } while (fast != null && fast != slow);

            if (fast == null) {
                return null;
            }

            ListNode p1 = head;
            ListNode p2 = slow;
            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }

            return p1;
        }


        //普通链表的拷贝
        public ListNode CopyList(ListNode head) {
            ListNode cur = head;
            ListNode result = null;
            ListNode last = null;

            while (cur != null) {
                ListNode newNode = new ListNode(cur.val);

                if (result == null) {
                    result = newNode;
                } else {
                    last.next = newNode;
                }
                last = newNode;
                cur = cur.next;
            }
            last.next = null;

            return result;
        }

        //链表的深度拷贝
        class RNode{
            int val;
            RNode next;
            RNode random;   //链表中任意节点的引用或者为null

            RNode(int v){
                this.val = v;
            }
        }

        void Test(){
            RNode n1 = new RNode(1);
            RNode n2 = new RNode(2);
            RNode n3 = new RNode(3);
            RNode n4 = new RNode(4);

            n1.next=n2;n2.next=n3;n3.next=n4;n4.next=null;
            n1.random=n3;n2.random=n1;n3.random=n3;n4.random=null;
        }

        public RNode copyRandomList(RNode head) {
            //遍历原链表的每一个节点，创建新节点
            //把新节点插入到原节点的后面
            RNode cur = head;
            while (cur != null) {
                RNode newNode = new RNode(cur.val);

                //把newNode插入到cur后面
                newNode.next=cur.next;
                cur.next=newNode;

                //让cur走向原链表的下一个节点
                cur=cur.next.next;
            }

            //设置新节点的random
            cur=head;
            while (cur!=null){
                RNode newNode = cur.next;
                if (cur.random==null){
                    newNode.random=null;
                }else {
                    newNode.random = cur.random.next;
                }

                //让cur走向原链表的下一个节点
                cur = newNode.next;
            }

            //拆
            cur=head;
            if (head==null){
                return null;
            }
            RNode result = head.next;
            while (cur!=null){
                RNode newNode = cur.next;
                cur.next = newNode.next;
                if (newNode.next!=null){
                    newNode.next=newNode.next.next;
                }
                cur=cur.next;
            }
            return result;
        }

    }

    private static void test1(){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(6);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(5);
        ListNode n7 = new ListNode(6);

        n1.next=n2;n2.next=n3;n3.next=n4;n4.next=n5;
        n5.next=n6;n6.next=n7;n7.next=null;

        Solution solution = new Solution();
        // ListNode result = solution.removeElements(n1,6);
        ListNode result = solution.removeElements2(n1,6);

        ListNode cur = result;
        while (cur!=null){
            System.out.print(cur.val+" ");
            cur=cur.next;
        }
    }

    private static void test2(){

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next=n2;n2.next=n3;n3.next=n4;n4.next=n5;n5.next=null;
        Solution solution = new Solution();
//        ListNode result = solution.reverseList(n1);
        ListNode result = solution.reverseList2(n1);

        ListNode cur = result;
        while (cur!=null){
            System.out.print(cur.val+" ");
            cur=cur.next;
        }

    }

    private static void test3(){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(1);
        ListNode n5 = new ListNode(3);
        ListNode n6 = new ListNode(4);

        n1.next=n2;n2.next=n3;n3.next=null;
        n4.next=n5;n5.next=n6;n6.next=null;

        Solution solution = new Solution();
        ListNode result = solution.mergeTwoLists(n1,n4);

        ListNode cur = result;
        while (cur!=null){
            System.out.print(cur.val+" ");
            cur=cur.next;
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}
