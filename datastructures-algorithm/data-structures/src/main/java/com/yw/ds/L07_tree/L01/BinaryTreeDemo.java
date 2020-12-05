package com.yw.ds.L07_tree.L01;

/**
 * 二叉树：
 * 1
 * 2   3
 * 4   5
 *
 * @author yangwei
 * @date 2020-06-01 22:50
 */
public class BinaryTreeDemo {
    /**
     * 定义二叉树结点
     */
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 定义二叉树
     */
    private static class BinaryTree {
        private Node root;

        public BinaryTree(Node root) {
            this.root = root;
        }

        /**
         * 前序遍历
         */
        public void preOrder() {
            if (this.root != null) {
                System.out.print("前序遍历输出：");
                preOrder(this.root);
            } else {
                System.out.println("二叉树为空~~");
            }
            System.out.println();
        }

        private void preOrder(Node node) {
            System.out.printf("%d\t", node.value);
            if (node.left != null) {
                preOrder(node.left);
            }
            if (node.right != null) {
                preOrder(node.right);
            }
        }

        /**
         * 中序遍历
         */
        public void infixOrder() {
            if (this.root != null) {
                System.out.print("中序遍历输出：");
                infixOrder(this.root);
            } else {
                System.out.println("二叉树为空~~");
            }
            System.out.println();
        }

        private void infixOrder(Node node) {
            if (node.left != null) {
                infixOrder(node.left);
            }
            System.out.printf("%d\t", node.value);
            if (node.right != null) {
                infixOrder(node.right);
            }
        }

        /**
         * 后序遍历
         */
        public void postOrder() {
            if (this.root != null) {
                System.out.print("后序遍历输出：");
                postOrder(this.root);
            } else {
                System.out.println("二叉树为空~~");
            }
            System.out.println();
        }

        private void postOrder(Node node) {
            if (node.left != null) {
                postOrder(node.left);
            }
            if (node.right != null) {
                postOrder(node.right);
            }
            System.out.printf("%d\t", node.value);
        }

        /**
         * 前序遍历查找
         */
        public Node preOrderSearch(int findVal) {
            if (root != null) {
                return preOrderSearch(root, findVal);
            } else {
                return null;
            }
        }

        private Node preOrderSearch(Node node, int findVal) {
            System.out.println("==>> preOrderSearch() ...");
            // 比较当前结点是不是
            if (node.value == findVal) {
                return node;
            }
            Node findNode = null;
            // 则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
            // 如果左递归前序查找，找到结点，则返回
            if (node.left != null) {
                findNode = preOrderSearch(node.left, findVal);
                if (findNode != null) {
                    return findNode;
                }
            }
            // 当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
            if (node.right != null) {
                findNode = preOrderSearch(node.right, findVal);
            }
            return findNode;
        }

        /**
         * 中序遍历查找
         */
        public Node infixOrderSearch(int findVal) {
            if (root != null) {
                return infixOrderSearch(root, findVal);
            } else {
                return null;
            }
        }

        private Node infixOrderSearch(Node node, int findVal) {
            Node findNode = null;
            if (node.left != null) {
                findNode = infixOrderSearch(node.left, findVal);
                if (findNode != null) {
                    return findNode;
                }
            }
            System.out.println("==>> infixOrderSearch() ...");
            if (node.value == findVal) {
                return node;
            }
            if (node.right != null) {
                findNode = infixOrderSearch(node.right, findVal);
            }
            return findNode;
        }

        /**
         * 后序遍历查找
         */
        public Node postOrderSearch(int findVal) {
            if (root != null) {
                return postOrderSearch(root, findVal);
            } else {
                return null;
            }
        }

        private Node postOrderSearch(Node node, int findVal) {
            Node findNode;
            if (node.left != null) {
                findNode = postOrderSearch(node.left, findVal);
                if (findNode != null) {
                    return findNode;
                }
            }
            if (node.right != null) {
                findNode = postOrderSearch(node.right, findVal);
                if (findNode != null) {
                    return findNode;
                }
            }
            System.out.println("==>> postOrderSearch() ...");
            if (node.value == findVal) {
                return node;
            }
            return null;
        }

        /**
         * 删除结点
         */
        public void deleteNode(int delVal) {
            if (root == null) {
                System.out.println("树空，不能删除");
                return;
            }
            if (root.value == delVal) {
                root = null;
            } else {
                // 调用递归删除
                deleteNode(root, delVal);
            }
        }

        /**
         * 递归删除结点
         */
        private void deleteNode(Node node, int delVal) {
            if (node.left != null) {
                // 如果当前结点的左子结点不为空
                if (node.left.value == delVal) {
                    // 并且左子结点就是要删除结点，就将 node.left = null; 并且就返回(结束递归删除)
                    node.left = null;
                    return;
                } else {
                    // 否则，就需要向左子树进行递归删除
                    deleteNode(node.left, delVal);
                }
            }
            if (node.right != null) {
                // 如果当前结点的右子结点不为空
                if (node.right.value == delVal) {
                    // 并且右子结点就是要删除结点，就将 node.right = null; 并且就返回(结束递归删除)
                    node.right = null;
                    return;
                } else {
                    // 否则，就需要向右子树进行递归删除
                    deleteNode(node.right, delVal);
                }
            }
        }
    }

    public static void main(String[] args) {
        // 先创建一颗二叉树
        Node root = createBinaryTree();
        BinaryTree binaryTree = new BinaryTree(root);
        // 前序遍历
        binaryTree.preOrder();
        // 中序遍历
        binaryTree.infixOrder();
        // 后序遍历
        binaryTree.postOrder();

        // 前序遍历查找
        binaryTree.preOrderSearch(5);
        // 中序遍历查找
        binaryTree.infixOrderSearch(5);
        // 后序遍历查找
        binaryTree.postOrderSearch(5);

        // 删除指定结点7
        System.out.print("删除前，");
        binaryTree.preOrder();
        binaryTree.deleteNode(7);
        System.out.print("删除后，");
        binaryTree.preOrder();
    }

    private static Node createBinaryTree() {
        Node root = new Node(1);
        Node n2 = new Node(3);
        Node n3 = new Node(5);
        Node n4 = new Node(7);
        Node n5 = new Node(9);
        root.left = n2;
        root.right = n3;
        n3.left = n4;
        n3.right = n5;
        return root;
    }
}
