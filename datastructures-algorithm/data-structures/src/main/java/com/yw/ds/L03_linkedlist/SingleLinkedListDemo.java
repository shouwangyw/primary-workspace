package com.yw.ds.L03_linkedlist;

/**
 * 单向链表
 *
 * @author yangwei
 * @date 2020-05-03 17:07
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
//        // 1、创建链表
//        linkedList.add(new HeroNode(1, "宋江", "及时雨"));
//        linkedList.add(new HeroNode(2, "卢俊义", "玉麒麟"));
//        linkedList.add(new HeroNode(3, "吴用", "智多星"));
//        linkedList.add(new HeroNode(4, "林冲", "豹子头"));
//        linkedList.print();
        // 2、按编号顺序创建链表
        linkedList.addByOrder(new HeroNode(1, "宋江", "及时雨"));
        linkedList.addByOrder(new HeroNode(3, "吴用", "智多星"));
        linkedList.addByOrder(new HeroNode(2, "卢俊义", "玉麒麟"));
        linkedList.addByOrder(new HeroNode(4, "林冲", "豹子头"));
        linkedList.addByOrder(new HeroNode(4, "林冲", "豹子头"));
        linkedList.print();
        // 3、修改结点
        linkedList.updateByNo(new HeroNode(2, "小卢", "麒麟~~"));
        System.out.println("修改后的链表情况：");
        linkedList.print();
        // 4、删除结点
        linkedList.deleteByNo(1);
//        linkedList.deleteByNo(2);
//        linkedList.deleteByNo(3);
        linkedList.deleteByNo(4);
        System.out.println("修改后的链表情况：");
        linkedList.print();
    }
}

/**
 * 对水浒英雄排行榜进行管理
 */
class SingleLinkedList {
    /**
     * 初始化头结点
     */
    private HeroNode head = new HeroNode(0, "", "");
    /**
     * 添加：当不考虑编号顺序时
     * 1、找到当前链表的最后一个结点
     * 2、将最后这个结点的next指向新的结点
     */
    public void add(HeroNode node) {
        // 因为 head 节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
        // 遍历链表找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
        // 当退出 while 循环时，temp 就指向了链表的最后，将最后这个节点的 next 指向 新的节点
        temp.next = node;
    }
    /**
     * 有序添加：根据排名将英雄插入到指定位置
     * 如果有这个排名，则添加失败，并给出提示
     */
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;

        while (temp.next != null) {
            if (temp.next.no > node.no) {
                // 位置找到，就在 temp 的后面插入
                node.next = temp.next;
                temp.next = node;
                break;
            } else if (temp.next.no == node.no) {
                System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", node.no);
                return;
            }
            temp = temp.next;
        }

        if (temp.next == null) {
            temp.next = node;
        }
    }
    /**
     * 修改结点的信息：根据 no 编号来修改，即 no 编号不能改
     */
    public void updateByNo(HeroNode node) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点, 根据 no 编号
        // 定义一个辅助变量
        HeroNode temp = head.next;
        while (temp != null) {
            if (temp.no == node.no) {
                // 找到
                temp.name = node.name;
                temp.nickname = node.nickname;
                return;
            }
            temp = temp.next;
        }

        System.out.printf("没有找到 编号 %d 的节点，不能修改\n", node.no);
    }
    /**
     * 删除结点
     * 思路：head 不能动，因此我们需要一个 temp 辅助节点找到待删除节点的前一个节点
     * 说明我们在比较时，是 temp.next.no 和 需要删除的节点的 no 比较
     */
    public void deleteByNo(int no) {
        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no == no) {
                // 找到了待删除节点的前一个节点 temp
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
        System.out.printf("要删除的 %d 节点不存在\n", no);
    }
    /**
     * 打印链表
     */
    public void print() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        while (temp.next != null) {
            System.out.println(temp.next);
            temp = temp.next;
        }
    }
}

/**
 * 定义水浒英雄结点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    // 为了显示方便，重写toString()方法
    @Override
    public String toString() {
        return "HeroNode[no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}