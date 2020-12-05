package com.yw.proxy;

import com.yw.proxy.staticed.Proxy;
import com.yw.proxy.staticed.Subject;

/**
 * 测试
 */
public class TestStaticProxy {
	public static void main(String[] args) {
		Subject subject = new Proxy();
		subject.request();
	}
//	结果输出
//	before request ...
//	RealSubject request ...
//	after request ...
}