package com.yw.ds.L07_tree.L02;

/**
 * 顺序存储二叉树案例
 * @author yangwei
 * @date 2020-06-03 15:03
 */
public class ArrayBinaryTreeDemo {
    /**
     * 定义数组顺序存储二叉树
     */
    private static class ArrayBinaryTree {
        /**
         * 存储数据结点的数组
         */
        private int[] arr;
        public ArrayBinaryTree(int[] arr) {
            this.arr = arr;
        }

        /**
         * 前序遍历
         */
        public void preOrder() {
            if (arr == null || arr.length == 0) {
                System.out.println("数组为空，不能遍历~~");
            }
            System.out.print("前序遍历：");
            preOrder(0);
            System.out.println();
        }
        /**
         * 编写一个方法，完成顺序存储二叉树的前序遍历
         * @param index 表示数组的下标
         */
        private void preOrder(int index) {
            // 输出当前这个元素
            System.out.printf("%d\t", arr[index]);
            // 向左递归遍历
            int leftIndex = 2 * index + 1;
            if (leftIndex < arr.length) {
                preOrder(leftIndex);
            }
            // 向右递归遍历
            int rightIndex = 2 * index + 2;
            if (rightIndex < arr.length) {
                preOrder(rightIndex);
            }
        }
        /**
         * 中序遍历
         */
        public void infixOrder() {
            if (arr == null || arr.length == 0) {
                System.out.println("数组为空，不能遍历~~");
            }
            System.out.print("中序遍历：");
            infixOrder(0);
            System.out.println();
        }
        private void infixOrder(int index) {
            // 向左递归遍历
            int leftIndex = 2 * index + 1;
            if (leftIndex < arr.length) {
                infixOrder(leftIndex);
            }
            // 输出当前这个元素
            System.out.printf("%d\t", arr[index]);
            // 向右递归遍历
            int rightIndex = 2 * index + 2;
            if (rightIndex < arr.length) {
                infixOrder(rightIndex);
            }
        }
        /**
         * 后序遍历
         */
        public void postOrder() {
            if (arr == null || arr.length == 0) {
                System.out.println("数组为空，不能遍历~~");
            }
            System.out.print("后序遍历：");
            postOrder(0);
            System.out.println();
        }
        private void postOrder(int index) {
            // 向左递归遍历
            int leftIndex = 2 * index + 1;
            if (leftIndex < arr.length) {
                postOrder(leftIndex);
            }
            // 向右递归遍历
            int rightIndex = 2 * index + 2;
            if (rightIndex < arr.length) {
                postOrder(rightIndex);
            }
            // 输出当前这个元素
            System.out.printf("%d\t", arr[index]);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
        arrayBinaryTree.infixOrder();
        arrayBinaryTree.postOrder();
    }
}
