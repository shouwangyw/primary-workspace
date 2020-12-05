package com.ds.L07_tree.L06;

/**
 * @author yangwei
 * @date 2020-06-12 22:17
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        // 测试二叉排序树添加
        for (int a : arr) {
            avlTree.add(new Node(a));
        }
//        System.out.println("在没有进行平衡处理前，中序遍历输出：");
//        System.out.println("在进行了左旋转处理前，中序遍历输出：");
        System.out.println("在进行了右旋转处理前，中序遍历输出：");
        avlTree.infixOrder();
        System.out.println("树的高度：" + avlTree.getHeight());
        System.out.println("树的左子树高度：" + avlTree.getLeftHeight());
        System.out.println("树的右子树高度：" + avlTree.getRightHeight());
    }
}

/**
 * 平衡二叉树
 */
class AVLTree {
    private Node root;

    /**
     * 结点添加
     */
    public void add(Node node) {
        if (node == null || root == null) {
            root = node;
            return;
        }
        add(root, node);
    }

    private void add(Node curNode, Node node) {
        if (node.value < curNode.value) {
            if (curNode.left == null) {
                curNode.left = node;
            } else {
                add(curNode.left, node);
            }
        } else {
            if (curNode.right == null) {
                curNode.right = node;
            } else {
                add(curNode.right, node);
            }
        }
        // 当添加完一个结点后
        // 1、若右子树高度-左子树高度 > 1，则左旋转
        if (getRightHeight() - getLeftHeight() > 1) {
            Node right = curNode.right;
            // 如果当前结点的右子结点的 左子树高度 > 其右子树高度
            if (right != null && getLeftHeight(right) > getRightHeight(right)) {
                // 则先要对当前结点的右子树进行右旋转
                rightRotate(right);
            }
            leftRotate();
            // !!!! 需要 return
            return;
        }
        // 2、若左子树高度-右子树高度 > 1，则右旋转
        if (getLeftHeight() - getRightHeight() > 1) {
            Node left = curNode.left;
            // 如果当前结点的左子结点的 右子树高度 > 其左子树高度
            if (left != null && getRightHeight(left) > getLeftHeight(left)) {
                // 则先要对当前结点的左子树进行左旋转
                leftRotate(left);
            }
            rightRotate();
        }
    }

    /**
     * 左旋转
     */
    private void leftRotate() {
        leftRotate(root);
    }

    private void leftRotate(Node node) {
        // 1、创建一个新结点，其值等于当前根结点的值
        Node newNode = new Node(node.value);
        // 2、把新结点的左子树 设置为 当前结点的左子树
        newNode.left = node.left;
        // 3、把新结点的右子树 设置为 当前结点的右子结点的左子树
        newNode.right = node.right.left;
        // 4、把当前结点的值替换为右子结点的值
        node.value = node.right.value;
        // 5、把当前结点的左子树 设置为 新结点
        node.left = newNode;
        // 6、把当前结点的右子树 设置为 右子结点的右子树
        node.right = node.right.right;
    }

    /**
     * 右旋转
     */
    private void rightRotate() {
        rightRotate(root);
    }

    private void rightRotate(Node node) {
        // 1、创建一个新结点，其值等于当前根结点的值
        Node newNode = new Node(node.value);
        // 2、把新结点的左子树 设置为 当前结点的左子结点的右子树
        newNode.left = node.left.right;
        // 3、把新结点的右子树 设置为 当前结点的右子数
        newNode.right = node.right;
        // 4、把当前结点的值替换为左子结点的值
        node.value = node.left.value;
        // 5、把当前结点的左子树 设置为 左子结点的左子树
        node.left = node.left.left;
        // 6、把当前结点的右子树 设置为 新结点
        node.right = newNode;
    }

    /**
     * 查询root结点树的高度
     *
     * @return
     */
    int getHeight() {
        return getHeight(root);
    }

    /**
     * 左子树高度
     */
    int getLeftHeight() {
        return getLeftHeight(root);
    }

    private int getLeftHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            return getHeight(node.left);
        }
    }

    /**
     * 右子树高度
     */
    int getRightHeight() {
        return getRightHeight(root);
    }

    private int getRightHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            return getHeight(node.right);
        }
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(node.left == null ? 0 : getHeight(node.left),
                node.right == null ? 0 : getHeight(node.right)) + 1;
    }

    /**
     * 中序遍历
     */
    void infixOrder() {
        if (root == null) {
            System.out.println("树空，不能遍历~~");
        } else {
            infixOrder(root);
        }
        System.out.println();
    }

    private void infixOrder(Node node) {
        if (node.left != null) {
            infixOrder(node.left);
        }
        System.out.printf("%s\t", node);
        if (node.right != null) {
            infixOrder(node.right);
        }
    }
}

/**
 * 结点
 */
class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{value=" + value + "}";
    }
}