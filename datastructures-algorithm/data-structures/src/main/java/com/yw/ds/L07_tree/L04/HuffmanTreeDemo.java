package com.yw.ds.L07_tree.L04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yangwei
 * @date 2020-06-05 07:58
 */
public class HuffmanTreeDemo {
    /**
     * 定义结点类
     * 说明：为了让Node对象支持Collections集合排序，让Node实现Comparable接口
     */
    private static class Node implements Comparable<Node> {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "{value = " + value + "}";
        }

        @Override
        public int compareTo(Node o) {
            // 从小到大排序
            return this.value - o.value;
        }
    }

    private static class HuffmanTree {
        private int[] arr;

        public HuffmanTree(int... arr) {
            this.arr = arr;
        }

        public Node build() {
            // 为了操作方便
            // 1、遍历arr数据；2、将arr的每个元素构建成一个Node；3、将Node放入ArrayList中
            List<Node> nodes = new ArrayList<>();
            for (int value : arr) {
                nodes.add(new Node(value));
            }
            while (nodes.size() > 1) {
                // 排序：从小到大
                Collections.sort(nodes);
                //        System.out.println(nodes);
                // 取出根结点权值最小的两颗二叉树
                Node left = nodes.get(0);
                Node right = nodes.get(1);
                // 构建一颗新的二叉树
                Node parent = new Node(left.value + right.value);
                parent.left = left;
                parent.right = right;
                // 从ArrayList中删除掉处理过的二叉树
                nodes.remove(left);
                nodes.remove(right);
                // 将parent加入到nodes
                nodes.add(parent);
                //        System.out.println(nodes);
            }
            // 返回赫夫曼树的root结点
            return nodes.get(0);
        }

        /**
         * 前序遍历
         */
        private void preOrder(Node node) {
            System.out.printf("%d\t", node.value);
            if (node.left != null) {
                preOrder(node.left);
            }
            if (node.right != null) {
                preOrder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree(13, 7, 8, 3, 29, 6, 1);
        Node root = huffmanTree.build();

        huffmanTree.preOrder(root);
    }
}
