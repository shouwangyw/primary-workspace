package com.yw.abcd;

import java.util.Scanner;
public class Main {
    static class Node {
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(plus(s1, s2));
    }

    private static String plus(String s1, String s2) {
        if (s1 == null || "".equals(s2)) {
            return s2;
        }
        if (s2 == null || "".equals(s2)) {
            return s1;
        }
        char[] c1 = s1.trim().toCharArray();
        char[] c2 = s2.trim().toCharArray();

        Node link1 = getResLinkNode(c1);
        Node link2 = getResLinkNode(c2);

        return getResult(plusList(link1, link2));
    }


    private static String getResult(Node node) {
        if (node == null) {
            return "";
        }
        Node p = node;
        StringBuffer sb = new StringBuffer();
        do {
            sb.append(p.val);
            p = p.next;
        } while (p != null);
        return sb.toString();
    }


    private static Node plusList(Node node1, Node node2) {
        // 边界值判断
        if (node1 == null) {
            return node2;
        } else if (node2 == null) {
            return node1;
        }

        Node resNodeHead = null;
        int flag = 0;
        while (node1 != null || node2 != null) {
            int a = node1 == null ? 0 : node1.val;
            int b = node2 == null ? 0 : node2.val;

            int ab = a + b + flag;
            Node node = new Node(ab % 10);
            flag = ab / 10;

            if (resNodeHead != null) {
                node.next = resNodeHead;
            }
            resNodeHead = node;

            if (node1 != null) {
                node1 = node1.next;
            }
            if (node2 != null) {
                node2 = node2.next;
            }
        }

        if (flag == 1) {
            Node node = new Node(1);
            node.next = resNodeHead;
            resNodeHead = node;
        }

        return resNodeHead;
    }

    private static Node getResLinkNode(char[] cs) {
        Node head = null;
        for (char c : cs) {
            Node node = new Node(Integer.parseInt(String.valueOf(c)));
            if (head != null) {
                node.next = head;
            }
            head = node;
        }
        return head;
    }
}