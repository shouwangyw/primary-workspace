package com.yw.linkedlist;

/**
 * @author yangwei
 * @date 2020-06-13 14:21
 */
public class P01_Test {
    static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
        }
    }
    public static void main(String[] args) {
//        Node head = addByHead(1, 3, 5, 7, 9);
//        printLinkedList(head);
        Node head = addByTail(1, 3, 5, 7, 9);
        printLinkedList(head);
    }

    /**
     * 头插法
     */
    private static Node addByHead(int... arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Node head = null;
        for (int a : arr) {
            Node node = new Node(a);
            if (head != null) {
                node.next = head;
            }
            head = node;
        }
        return head;
    }

    /**
     * 尾插发
     */
    private static Node addByTail(int... arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Node head = null;
        Node cursor = null;
        for (int a : arr) {
            Node node = new Node(a);
            if (head == null) {
                head = node;
            } else {
                cursor.next = node;
            }
            cursor = node;
        }
        return head;
    }

    private static void printLinkedList(Node node) {
        Node cursor = node;
        while (cursor != null) {
            System.out.printf("%d\t", cursor.value);
            cursor = cursor.next;
        }
    }
}

