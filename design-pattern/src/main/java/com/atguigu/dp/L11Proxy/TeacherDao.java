package com.atguigu.dp.L11Proxy;

class TeacherDao implements ITeacherDao {
    @Override
    public void teach() {
        System.out.println("老师在上课...");
    }
}