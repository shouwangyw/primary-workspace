package com.yw.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by hp on 2019/5/4.
 */
public class InputUtil {
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));
    private InputUtil(){}

    /**
     * 实现键盘数据的输入操作，可以返回的数据类型是String
     * @param prompot   提示信息
     * @return  输入的数据返回
     */
    public static String getString(String prompot){
        boolean flag = true; // 数据接收标记
        String str = null;
        while (flag){
            System.out.println(prompot);
            try{
                str = KEYBOARD_INPUT.readLine(); // 读取一行
                if(str==null || "".equals(str)){
                    System.out.println("数据输入错误，该内容不允许为空：");
                }else {
                    flag = false;
                }
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("数据输入错误，该内容不允许为空：");
            }
        }
        return str;
    }
}
