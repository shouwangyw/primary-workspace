package com.yw.prototype;

/**
 * 原型类
 */
public class Prototype implements Cloneable {
	@Override
	public Object clone() throws CloneNotSupportedException {
		Prototype prototype = (Prototype) super.clone();
		return prototype;
	}
}