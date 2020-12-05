package com.yw.ds.L07_tree.L03;

/**
 * 线索化二叉树案例
 *
 * @author yangwei
 * @date 2020-06-03 17:09
 */
public class ThreadedBinaryTreeDemo {
    /**
     * 定义二叉树结点
     */
    private static class Node {
        private int value;
        private Node left;
        private Node right;
        /**
         * leftType = 0表示指向的是左子树，=1表示指向前驱结点
         */
        private int leftType;
        /**
         * rightType = 0表示指向的是右子树，=1表示指向后继结点
         */
        private int rightType;
        /**
         * 父节点的指针（为了后序线索化使用）
         */
        private Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    private static class ThreadedBinaryTree {
        private Node root;
        /**
         * 为了实现线索化，需要创建指向当前结点的前驱结点的指针
         * 在递归进行线索化时，pre总是保留前一个结点
         */
        private Node pre = null;

        public ThreadedBinaryTree(Node root) {
            this.root = root;
        }

        /**
         * 编写对二叉树进行中序线索化的方法
         */
        public void infixOrderThreaded() {
            infixOrderThreaded(root);
        }
        private void infixOrderThreaded(Node node) {
            if (node == null) {
                return;
            }
            // 1、线索化左子树
            infixOrderThreaded(node.left);

            // 2、线索化当前结点
            threadedCurNode(node);

            // 3、线索化右子树
            infixOrderThreaded(node.right);
        }

        /**
         * 中序线索化遍历
         */
        public void infixOrder() {
            // 定义一个变量存储当前遍历的结点，从root开始
            Node node = root;
            while (node != null) {
                // 循环找到leftType = 1的结点，后面随着遍历而变化，
                // leftType = 1说明该结点是按照线索化处理后的有效结点
                while (node.leftType == 0) {
                    node = node.left;
                }
                // 打印当前结点的值
                System.out.printf("%d\t", node.value);
                // 如果当前结点的右指针指向的是后继结点，就一直输出
                while (node.rightType == 1) {
                    node = node.right;
                    System.out.printf("%d\t", node.value);
                }
                // 替换这个遍历的结点
                node = node.right;
            }
        }

        /**
         * 前序线索化
         */
        public void preOrderThreaded() {
            preOrderThreaded(root);
        }
        private void preOrderThreaded(Node node) {
            if (node == null) {
                return;
            }
            // 1、线索化当前结点
            threadedCurNode(node);

            // 2、线索化左子树
            if (node.leftType == 0) {
                preOrderThreaded(node.left);
            }
            // 3、线索化右子树
            if (node.rightType == 0) {
                preOrderThreaded(node.right);
            }
        }

        /**
         * 前序线索化遍历（按照后继线索遍历）
         */
        public void preOrder() {
            // 定义一个变量存储当前遍历的结点，从root开始
            Node node = root;
            while (node != null) {
                while (node.leftType == 0) {
                    System.out.printf("%d\t", node.value);
                    node = node.left;
                }
                System.out.printf("%d\t", node.value);
                node = node.right;
            }
        }

        /**
         * 后序线索化
         */
        public void postOrderThreaded() {
            postOrderThreaded(root);
        }
        private void postOrderThreaded(Node node) {
            if (node == null) {
                return;
            }

            // 1、线索化左子树
            if (node.leftType == 0) {
                postOrderThreaded(node.left);
            }
            // 2、线索化右子树
            if (node.rightType == 0) {
                postOrderThreaded(node.right);
            }
            // 3、线索化当前结点
            threadedCurNode(node);
        }

        /**
         * 后序线索化遍历
         */
        public void postOrder() {
            Node node = root;
            while (node != null && node.leftType == 0) {
                node = node.left;
            }
            Node preNode = null;
            while (node != null) {
                // 右节点是线索
                if (node.rightType == 1) {
                    System.out.printf("%d\t", node.value);
                    preNode = node;
                    node = node.right;
                } else {
                    // 如果上个处理的节点是当前节点的右节点
                    if (node.right == preNode) {
                        System.out.printf("%d\t", node.value);
                        if (node == root) {
                            return;
                        }
                        preNode = node;
                        node = node.parent;
                    } else {
                        // 如果从左节点的进入则找到有子树的最左节点
                        node = node.right;
                        while (node != null && node.leftType == 0) {
                            node = node.left;
                        }
                    }
                }
            }
        }

        private void threadedCurNode(Node node) {
            // 1、处理当前结点的前驱结点
            if (node.left == null) {
                // 让当前结点的左指针指向前驱结点
                node.left = pre;
                // 修改当前结点的左指针类型：指向前驱结点
                node.leftType = 1;
            }
            // 2、处理当前结点的后继结点
            if (pre != null && pre.right == null) {
                // 让前驱结点的右指针指向当前结点
                pre.right = node;
                // 修改前驱结点的右指针类型为1
                pre.rightType = 1;
            }
            // 3、!!!很重要的一句：每处理一个结点后，让当前结点是下一个结点的前驱结点
            pre = node;
        }
    }

    public static void main(String[] args) {
        // 先创建一颗二叉树
        Node root = createBinaryTree();
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);

//        // 中序线索二叉树
//        threadedBinaryTree.infixOrderThreaded();
//        System.out.print("遍历中序线索二叉树：");
//        threadedBinaryTree.infixOrder();

        // 前序线索二叉树
        threadedBinaryTree.preOrderThreaded();
        System.out.print("遍历前序线索二叉树：");
        threadedBinaryTree.preOrder();

//        // 后序线索二叉树
//        threadedBinaryTree.postOrderThreaded();
//        System.out.print("遍历后序线索二叉树：");
//        threadedBinaryTree.postOrder();
    }

    private static Node createBinaryTree() {
//        Node root = new Node(1);
//        Node n2 = new Node(3);
//        Node n3 = new Node(6);
//        Node n4 = new Node(8);
//        Node n5 = new Node(10);
//        Node n6 = new Node(14);
//        root.left = n2;
//        root.right = n3;
//        n2.left = n4;
//        n2.right = n5;
//        n3.left = n6;
//        return root;
        return createBinaryTree(new int[]{1, 3, 6, 8, 10, 14}, 0);
    }
    /**
     * 通过数组构造一个二叉树（完全二叉树）
     */
    private static Node createBinaryTree(int[] arr, int index) {
        Node node = null;
        if (index < arr.length) {
            node = new Node(arr[index]);
            node.left = createBinaryTree(arr, index * 2 + 1);
            node.right = createBinaryTree(arr, index * 2 + 2);
        }
        //记录节点的父节点（后序线索化遍历时使用）
        if(node != null && node.left != null) {
            node.left.parent = node;
        }

        if(node != null && node.right != null) {
            node.right.parent = node;
        }
        return node;
    }
}
