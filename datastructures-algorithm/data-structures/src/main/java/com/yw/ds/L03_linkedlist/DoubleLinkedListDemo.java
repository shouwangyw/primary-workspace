package com.yw.ds.L03_linkedlist;

/**
 * 双向链表
 *
 * @author yangwei
 * @date 2020-05-07 07:15
 */
public class DoubleLinkedListDemo {
    /**
     * 定义一个双向链表的结点对象
     */
    static class LinkedNode {
        private int value;
        private LinkedNode next;
        private LinkedNode pre;

        public LinkedNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "LinkedNode[value=" + value + "]";
        }
    }

    public static void main(String[] args) {
        LinkedNode head = createLinkedList(5, 4, 1, 3, 8, 9, 7);
        System.out.print("① 双向链表：");
        printLinkedList(head);
        LinkedNode head2 = createLinkedListByOrder(6, 8, 1, 4, 9, 3, 2);
        System.out.print("② 有序的双向链表：");
        printLinkedList(head2);
//        LinkedNode newHead = deleteNode(head, 5);
        LinkedNode newHead = deleteNode(head, 3);
//        LinkedNode newHead = deleteNode(head, 7);
        System.out.print("③ 双向链表删除：");
        printLinkedList(newHead);
    }

    /**
     * 双向链表创建（默认方式）
     */
    private static LinkedNode createLinkedList(int... values) {
        LinkedNode head = null, cursor = null;
        for (int value : values) {
            LinkedNode node = new LinkedNode(value);
            if (head == null) {
                head = node;
            } else {
                cursor.next = node;
                node.pre = cursor;
            }
            cursor = node;
        }
        return head;
    }

    /**
     * 双向链表创建（有序）
     */
    private static LinkedNode createLinkedListByOrder(int... values) {
        if (values == null || values.length == 0) {
            return null;
        }
        // 创建一个头结点
        LinkedNode tempHead = new LinkedNode(0);

        for (int value : values) {
            addByOrder(tempHead, new LinkedNode(value));
        }

        // 返回不带头结点的头结点
        LinkedNode head = tempHead.next;
        tempHead.next = null;
        return head;
    }

    private static void addByOrder(LinkedNode tempHead, LinkedNode node) {
        LinkedNode cursor = tempHead;
        // cursor的下一个结点
        LinkedNode next;
        while (cursor.next != null) {
            next = cursor.next;
            if (cursor.next.value > node.value) {
                // 位置找到，进行结点插入
                node.next = next;
                next.pre = node;
                cursor.next = node;
                node.pre = next;
                break;
            }
            cursor = next;
        }
        if (cursor.next == null) {
            cursor.next = node;
            node.pre = cursor;
        }
    }

    /**
     * 删除结点
     */
    private static LinkedNode deleteNode(LinkedNode head, int value) {
        if (head == null) {
            System.out.println("链表空，不能删除~~");
            return null;
        }
        // 如果删除的是头结点
        if (head.value == value) {
            head = head.next;
            head.pre = null;
            return head;
        }
        LinkedNode cursor = head;
        while (cursor != null) {
            if (cursor.value == value) {
                cursor.pre.next = cursor.next;
                // 如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
                if (cursor.next != null) {
                    cursor.next.pre = cursor.pre;
                }
                break;
            }
            cursor = cursor.next;
        }
        return head;
    }

    /**
     * 打印链表
     * @param head
     */
    private static void printLinkedList(LinkedNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        LinkedNode cursor = head;
        System.out.print("null <- ");
        while (cursor != null) {
            System.out.printf(cursor.next == null ? "%d -> " : "%d <=> ", cursor.value);
            cursor = cursor.next;
        }
        System.out.println("null");
    }
}
