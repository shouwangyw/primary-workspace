package com.ds.L03_linkedlist;

import java.util.Scanner;

/**
 * 单向循环链表
 *
 * @author yangwei
 * @date 2020-05-07 23:31
 */
public class CircleSingleLinkedListDemo {
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
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入环形链表的大小(n)：");
        int n = sc.nextInt();
        LinkedNode first = createCircleSingleLinkedList(n);
        System.out.print("生成环形链表：");
        printLinkedList(first);
        System.out.print("请输入编号(k)：");
        int k = sc.nextInt();
        System.out.print("请输入数字(m)：");
        int m = sc.nextInt();
        System.out.print("打印输出约瑟夫出队编号序列：");
        printJosephuQueue(first, n, k, m);
    }

    private static LinkedNode createCircleSingleLinkedList(int n) {
        if (n <= 0) {
            return null;
        }
        LinkedNode first = null, cursor = null;
        for (int i = 1; i <= n; i++) {
            LinkedNode node = new LinkedNode(i);
            if (first == null) {
                first = node;
                // 形成环
                first.next = first;
                cursor = first;
            } else {
                cursor.next = node;
                node.next = first;
                cursor = node;
            }
        }
        return first;
    }

    private static void printLinkedList(LinkedNode first) {
        if (first == null) {
            System.out.println("链表空~~");
            return;
        }
        LinkedNode cursor = first;
        while (cursor.next != first) {
            System.out.printf("%d\t", cursor.value);
            cursor = cursor.next;
        }
        System.out.printf("%d\t", cursor.value);
        System.out.println();
    }

    private static void printJosephuQueue(LinkedNode first, int n, int k, int m) {
        if (first == null || k < 1 || k > n || m < 1) {
            System.out.println("输入参数有误~~");
            return;
        }
        // 1、创建一个辅助指针，并指向环形链表的最后一个节点
        LinkedNode cursor = first;
        while (cursor.next != first) {
            cursor = cursor.next;
        }
        // 2、将first、cursor移动k-1次
        for (int i = 0; i < k - 1; i++) {
            first = first.next;
            cursor = cursor.next;
        }
        // 3、开始数m下，即移动m-1次，找到要出队的结点，并完成出队
        while (true) {
            if (cursor == first) {
                // 说明环形链表只剩下最后一个结点
                System.out.print(cursor.value);
                break;
            }
            // 移动m-1次
            for (int i = 0; i < m - 1; i++) {
                first = first.next;
                cursor = cursor.next;
            }
            System.out.printf("%d\t", first.value);
            first = first.next;
            cursor.next = first;
        }
    }
}
