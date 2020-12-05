package com.ds.L03_linkedlist;

import java.util.Stack;

/**
 * 面试题
 * @author yangwei
 * @date 2020-05-03 22:37
 */
public class LinkedListTest {
    static class LinkedNode {
        private int value;
        private LinkedNode next;

        public LinkedNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "LinkedNode[value=" + value + "]";
        }
    }

    public static void main(String[] args) {
        // 1、创建一个不带头节点的单向链表
        LinkedNode head = createLinkedList(5, 4, 1, 3, 8, 9, 7);
        System.out.print("① 单向链表：");
        printLinkedList(head);
        // 2、求单链表中有效节点的个数
        System.out.println("② 单向链表中有效结点个数：" + getLength(head));
        // 3、查找单链表中的倒数第k个结点
        System.out.printf("③ 单链表中倒数第%d个结点：%s\n", 4, findLastIndexNode(head, 4));
        // 4、单链表的反转
        reverseLinkedList(head);
        System.out.print("④ 反转后的链表：");
        printLinkedList(head);
        // 5、逆序打印链表
        System.out.print("⑤ 逆序打印链表：");
        reversePrint(head);
        // 6、合并两个有序链表
        System.out.print("⑥ 合并两个有序链表：");
        LinkedNode head1 = createLinkedList(1, 3, 5, 7);
        LinkedNode head2 = createLinkedList(2, 4, 6, 8, 10);
        LinkedNode newHead = margeTwoLinkedList(head1, head2);
        printLinkedList(newHead);
    }
    /**
     * 根据传入的值，创建一个单向链表
     * @param values 一组整数
     * @return 单向链表头结点
     */
    private static LinkedNode createLinkedList(int... values) {
        LinkedNode head = null, cursor = null;
        for (int value : values) {
            LinkedNode node = new LinkedNode(value);
            if (cursor == null) {
                head = node;
            } else {
                cursor.next = node;
            }
            cursor = node;
        }
        return head;
    }
    /**
     * 打印链表
     *
     * @param head
     */
    private static void printLinkedList(LinkedNode head) {
        LinkedNode cursor = head;
        while (cursor != null) {
            System.out.printf("%d -> ", cursor.value);
            cursor = cursor.next;
        }
        System.out.println("null");
    }
    /**
     * 求单链表中有效节点的个数
     */
    private static int getLength(LinkedNode head) {
        int length = 0;
        LinkedNode cursor = head;
        while (cursor != null) {
            length++;
            cursor = cursor.next;
        }
        return length;
    }
    /**
     * 查找单链表中的倒数第k个结点
     */
    private static LinkedNode findLastIndexNode(LinkedNode head, int index) {
        // 第一次遍历，得到链表的长度
        int length = getLength(head);
        if (length <= 0 || index > length) {
            return null;
        }
        // 第二次遍历，for循环定位到 倒数的index
        LinkedNode cursor = head;
        for (int i = 0;i <length - index;i++) {
            cursor = cursor.next;
        }
        return cursor;
    }
    /**
     * 单链表的反转
     * 注：不带头结点，head 引用传递，所以先反转head.next之后的结点
     */
    private static void reverseLinkedList(LinkedNode head) {
        if (head == null || head.next == null) {
            return;
        }
        // 定义一个游标，用于遍历链表，从head的下一个结点开始
        LinkedNode cursor = head.next;
        // 定义一个辅助变量，初始值为原始的头结点的值
        LinkedNode tempHead = new LinkedNode(head.value);
        // 指向当前结点[cursor]的下一个结点
        LinkedNode next;
        while (cursor != null) {
            // 先暂存一下cursor的下一个结点
            next = cursor.next;

            // 摘下（取下）cursor结点，并指向新的链表的最前端
            cursor.next = tempHead;
            tempHead = cursor;

            // cursor 后移
            cursor = next;
        }
        // 将head指向tempHead，实现单链表反转
        head.value = tempHead.value;
        head.next = tempHead.next;
    }
    /**
     * 从尾到头打印单链表
     * 可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点
     * 就实现了逆序打印的效果
     */
    private static void reversePrint(LinkedNode head) {
        if (head == null) {
            // 空链表
            System.out.println("null");
            return;
        }
        // 创建一个栈，将各个结点压入栈
        Stack<LinkedNode> stack = new Stack<>();
        // 创建一个游标，用于遍历
        LinkedNode cursor = head;
        while (cursor != null) {
            stack.push(cursor);
            cursor = cursor.next;
        }
        // pop出栈，打印栈中的节点
        while (stack.size() > 0) {
            System.out.printf("%d -> ", stack.pop().value);
        }
        System.out.println("null");
    }
    /**
     * 合并两个有序的单链表，合并之后的链表依然有序
     */
    private static LinkedNode margeTwoLinkedList(LinkedNode head1, LinkedNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        // 经过上面的边界值判断，head1 != null，head2 != null

        // 合并后的链表表头，设置为head1、head2中最小的那一个
        LinkedNode head = head1.value < head2.value ? head1 : head2;
        // 定义两个个游标指针
        LinkedNode cursor1 = head == head1 ? head1 : head2;
        LinkedNode cursor2 = head == head1 ? head2 : head1;
        LinkedNode pre = cursor1;
        LinkedNode next;
        // 遍历
        while (cursor1 != null && cursor2 != null) {
            int value1 = cursor1.value;
            int value2 = cursor2.value;
            if (value1 <= value2) {
                pre = cursor1;
                cursor1 = cursor1.next;
            } else {
                next = cursor2.next;
                pre.next = cursor2;
                cursor2.next = cursor1;
                pre = cursor2;
                cursor2 = next;
            }
        }
        pre.next = cursor1 == null ? cursor2 : cursor1;

        return head;
    }
}
