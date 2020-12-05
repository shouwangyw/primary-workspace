package com.yw.ds.L07_tree.L05;

/**
 * @author yangwei
 * @date 2020-06-10 22:12
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 测试二叉排序树添加
        for (int a : arr) {
            binarySortTree.add(new Node(a));
        }
        System.out.println("二叉排序树创建完成，中序遍历输出：");
        binarySortTree.infixOrder();

        // 测试二叉排序树的删除叶子结点
        binarySortTree.delete(2);
        binarySortTree.delete(5);
        binarySortTree.delete(1);
        binarySortTree.delete(10);
        binarySortTree.delete(9);
        binarySortTree.delete(12);
        binarySortTree.delete(7);
        binarySortTree.delete(3);
        System.out.println("删除结点后：");
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    /**
     * 根结点
     */
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
    }

    /**
     * 结点删除
     */
    public void delete(int targetVal) {
        if (root == null) {
            return;
        }
        // 查找目标结点
        Node targetNode = find(targetVal);
        if (targetNode == null) {
            return;
        }
        // 如果当前二叉树只有一个结点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        // 找到targetNode的父结点
        Node parentNode = findParent(targetVal);

        // 删除：分三种情况
        if (targetNode.left == null && targetNode.right == null) {
            // 1、要删除的结点是叶子结点
            deleteLeafNode(parentNode, targetVal);
        } else if (targetNode.left != null && targetNode.right != null) {
            // 2、删除有两颗子树的结点
            deleteOwnTwoSubTree(targetNode);
        } else {
            // 3、删除只有一颗子树的结点
            deleteOwnOneSubTree(targetNode, parentNode, targetVal);
        }
    }

    /**
     * 删除叶子结点
     */
    private void deleteLeafNode(Node parentNode, int targetVal) {
        // 判断targetNode是parentNode的左子结点还是右子节点
        if (parentNode.left != null && parentNode.left.value == targetVal) {
            parentNode.left = null;
        } else if (parentNode.right != null && parentNode.right.value == targetVal) {
            parentNode.right = null;
        }
    }

    /**
     * 删除只有一颗子树的结点
     */
    private void deleteOwnOneSubTree(Node targetNode, Node parentNode, int targetVal) {
        if (targetNode.left != null) {
            // 1、如果targetNode有左子结点
            if (parentNode != null) {
                if (parentNode.left.value == targetVal) {
                    // targetNode是parentNode的左子结点
                    parentNode.left = targetNode.left;
                } else {
                    // targetNode是parentNode的右子结点
                    parentNode.right = targetNode.left;
                }
            } else {
                root = targetNode.left;
            }
        } else {
            // 2、如果targetNode有右子结点
            if (parentNode != null) {
                if (parentNode.left.value == targetVal) {
                    parentNode.left = targetNode.right;
                } else {
                    parentNode.right = targetNode.right;
                }
            } else {
                root = targetNode.right;
            }
        }
    }

    /**
     * 删除有两颗子树的结点
     * 方法1：从targetNode的右子树找最小结点删除，并将targetNode的value设置为该最小结点的值
     * 方法2：从targetNode的做子树找最大结点删除，并将targetNode的value设置为该最大结点的值
     */
    private void deleteOwnTwoSubTree(Node targetNode) {
        Node target = targetNode.right;
        while (target.left != null) {
            target = target.left;
        }
        int minVal = target.value;
        delete(minVal);
        targetNode.value = minVal;
    }

    /**
     * 根据targetVal查找结点
     */
    public Node find(int targetVal) {
        return find(root, targetVal);
    }

    private Node find(Node node, int targetVal) {
        if (node == null) {
            return null;
        }
        if (node.value == targetVal) {
            return node;
        } else if (node.value > targetVal) {
            return find(node.left, targetVal);
        } else {
            return find(node.right, targetVal);
        }
    }

    /**
     * 根据targetVal查找结点的父结点
     */
    public Node findParent(int targetVal) {
        if (root == null || root.value == targetVal) {
            return null;
        } else {
            return findParent(root, targetVal);
        }
    }

    private Node findParent(Node node, int targetVal) {
        if (node == null) {
            return null;
        }
        boolean flag = (node.left != null && node.left.value == targetVal)
                || (node.right != null && node.right.value == targetVal);
        if (flag) {
            return node;
        } else {
            if (node.value > targetVal && node.left != null) {
                return findParent(node.left, targetVal);
            } else if (node.value < targetVal && node.right != null) {
                return findParent(node.right, targetVal);
            } else {
                return null;
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
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
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{value=" + value + "}";
    }
}

