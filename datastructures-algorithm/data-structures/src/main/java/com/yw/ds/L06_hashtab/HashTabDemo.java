package com.ds.L06_hashtab;

import java.util.Scanner;

/**
 * 需求：
 * 有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,住址..),
 * 当输入该员工的id时,要求查找到该员工的 所有信息.
 *
 * @author yangwei
 * @date 2020-05-18 23:16
 */
public class HashTabDemo {
    public static void main(String[] args) {
        final HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key;
        Scanner scanner = new Scanner(System.in);
        System.out.println("add: 添加雇员");
        System.out.println("list: 显示雇员");
        System.out.println("find: 查找雇员");
        System.out.println("exit: 退出系统");
        while (true) {
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入 id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    //创建 雇员
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的 id");
                    id = scanner.nextInt();
                    hashTab.findById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

/**
 * 哈希表
 */
class HashTab {
    private int size;
    private EmpLinkedList[] empLinkedLists;

    /**
     * 构造器
     *
     * @param size
     */
    public HashTab(int size) {
        this.size = size;
        // 初始化
        empLinkedLists = new EmpLinkedList[size];
        // 这时不要忘了分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加
     */
    public void add(Emp emp) {
        // 根据员工的 id ,得到该员工应当添加到哪条链表
        int hashVal = hash(emp.id);
        empLinkedLists[hashVal].add(emp);
        System.out.println("添加 " + emp.toString() + " 成功");
    }

    /**
     * 遍历
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i+1);
        }
    }

    /**
     * 根据id查找
     */
    public void findById(int id) {
        int hashVal = hash(id);
        Emp emp = empLinkedLists[hashVal].findById(hashVal);
        if (emp != null) {
            System.out.printf("在第 %d 条链表中找到 雇员 id = %d\n", (hashVal + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }
    private int hash(int id) {
        return id % size;
    }
}

/**
 * EmpLinkedList
 */
class EmpLinkedList {
    /**
     * 头指针，指向第一个Emp，因此我们这个链表的head是直接指向第一个Emp
     */
    private Emp head;

    /**
     * 添加：假定，当添加雇员时，id 是自增长，即 id 的分配总是从小到大
     * 因此我们将该雇员直接加入到本链表的最后即可
     */
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    /**
     * 遍历
     */
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "条链表空~~");
            return;
        }
        System.out.print("第" + no + "条链表的信息为：");
        Emp curEmp = head;
        while (curEmp != null) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            curEmp = curEmp.next;
        }
        System.out.println();
    }
    /**
     * 根据 id 查找雇员
     * 如果查找到，就返回 Emp, 如果没有找到，就返回 null
     */
    public Emp findById(int id) {
        if (head == null) {
            System.out.println("链表为空~~");
            return null;
        }
        Emp curEmp = head;
        while (curEmp.next != null) {
            if (curEmp.id == id) {
                // 找到了，此时 curEmp 就指向要查找的雇员
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}

/**
 * 雇员类
 */
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{id=" + id + ", name=" + name + "}";
    }
}
