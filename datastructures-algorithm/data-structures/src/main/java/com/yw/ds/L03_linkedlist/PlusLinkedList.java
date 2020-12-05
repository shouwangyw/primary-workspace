package com.yw.ds.L03_linkedlist;

import java.util.Scanner;

/**
 * 两个链表生成相加链表
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
 * 输入描述
 * 第一行两个整数 n 和 m，分别表示两个链表的长度。
 * <p>
 * 第二行 n 个整数 ai 表示第一个链表的节点。
 * <p>
 * 第三行 m 个整数 bi 表示第二个链表的节点。
 * 输出描述
 * 输出一行整数表示结果链表。
 * 示例1
 * 输入
 * 3 2
 * 9 3 7
 * 6 3
 * 输出
 * 1 0 0 0
 * <p>
 * 测试用例1：
 * 40 40
 * 5 9 7 5 7 1 2 6 4 2 7 8 9 6 1 6 6 1 1 4 2 9 5 5 0 4 6 3 0 4 3 5 6 7 0 5 5 4 4 0
 * 1 3 2 5 0 6 0 2 1 4 3 9 3 0 9 9 0 3 1 6 5 7 8 6 2 3 8 5 0 9 7 9 4 5 9 9 4 9 3 6
 * 结果输出：
 * 7 3 0 0 7 7 2 8 5 7 1 8 2 7 1 5 6 4 3 0 8 7 4 1 2 8 4 8 1 4 1 5 1 3 0 5 0 3 7 6
 * <p>
 * 测试用例2：
 * 33 26
 * 5 9 2 3 7 4 9 9 0 2 6 6 1 3 8 3 2 1 9 8 4 3 1 3 3 7 5 3 9 3 1 3 1
 * 4 2 8 3 5 1 0 5 7 4 5 0 2 5 0 3 9 7 3 6 8 4 4 9 7 1
 * 结果输出：
 * 5 9 2 3 7 5 0 3 3 1 0 1 2 4 4 0 6 7 0 0 9 3 5 3 1 1 2 2 3 8 1 0 2
 *
 * @author yangwei
 * @date 2020-05-06 11:04
 */
public class PlusLinkedList {
    static class LinkNode {
        /**
         * 节点值
         */
        private int value;
        /**
         * 下一个节点
         */
        private LinkNode next;

        public LinkNode(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 得到逆序的链表
        LinkNode nLinkHead = getResLink(n, sc);
        LinkNode mLinkHead = getResLink(m, sc);

        // 链表相加
        printLink(plusList(nLinkHead, mLinkHead));
    }

    /**
     * 得到反序的链表
     */
    private static LinkNode getResLink(int n, Scanner sc) {
        LinkNode headNode = null;
        for (int i = 0; i < n; i++) {
            LinkNode node = new LinkNode(sc.nextInt());
            if (headNode != null) {
                node.next = headNode;
            }
            headNode = node;
        }
        return headNode;
    }

    /**
     * 两个链表生成相加链表
     *
     * @param nNode n链表的头节点
     * @param mNode m链表的头节点
     * @return
     */
    private static LinkNode plusList(LinkNode nNode, LinkNode mNode) {
        // 边界值判断
        if (nNode == null) {
            return mNode;
        } else if (mNode == null) {
            return nNode;
        }

        LinkNode resNodeHead = null;
        int flag = 0;
        while (nNode != null || mNode != null) {
            int a = nNode == null ? 0 : nNode.value;
            int b = mNode == null ? 0 : mNode.value;

            int ab = a + b + flag;
            LinkNode node = new LinkNode(ab % 10);
            flag = ab / 10;

            if (resNodeHead != null) {
                node.next = resNodeHead;
            }
            resNodeHead = node;

            if (nNode != null) {
                nNode = nNode.next;
            }
            if (mNode != null) {
                mNode = mNode.next;
            }
        }

        if (flag == 1) {
            LinkNode node = new LinkNode(1);
            node.next = resNodeHead;
            resNodeHead = node;
        }

        return resNodeHead;
    }

    /**
     * 打印链表节点值
     */
    private static void printLink(LinkNode node) {
        if (node == null) {
            return;
        }
        LinkNode p = node;
        do {
            System.out.print(p.value + " ");
            p = p.next;
        } while (p != null);
        System.out.println();
    }
}
