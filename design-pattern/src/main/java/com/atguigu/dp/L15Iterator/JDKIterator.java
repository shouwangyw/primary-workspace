package com.atguigu.dp.L15Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器模式在JDK-ArrayList集合应用的源码分析
 * @author yangwei
 * @date 2020-06-28 21:03
 */
public class JDKIterator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("jack");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
