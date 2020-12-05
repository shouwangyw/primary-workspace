package test;

/**
 * @author yangwei
 * @date 2020-07-07 22:48
 */
public class Solution {
    static class Node {
        public int val;
        public Node next;
        Node(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        node1.next = node2;
        node2.next = node1;

        System.out.println(isCircle(node1));
    }

    private static boolean isCircle(Node head) {
        Node tmp = head;
        while (tmp != null) {
            tmp = tmp.next;
            if (tmp == head) {
                return true;
            }
        }
        return false;
    }
}
