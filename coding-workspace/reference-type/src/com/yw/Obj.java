package com.yw;

/**
 * @author yangwei
 * @date 2020-04-23 09:40
 */
public class Obj {
    private Integer id;
    private String name;

    public Obj() {
    }

    public Obj(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + "]";
    }

    /**
     * 在GC垃圾回收时被调用
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("==>> finalize 被打印，说明该对象" + toString() + "被回收");
    }
}
