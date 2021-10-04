package com.study.J2SE.basic.reflect;

import org.junit.Test;

public class TestClass {
	
	
	/**
	 * @throws ClassNotFoundException
	 * 基本类型可以用 int.class 唯一方式获取其 Class 对象
	 * 基本类型数组也有其 Class 对象,分不同基本类型和维度
	 */
	@Test
	public void testBaseType() throws ClassNotFoundException{
		//	Array array = new Array
		System.out.println(int.class.getName());

		System.out.println(int[].class.getName());
		System.out.println(int[][].class.getName());
		System.out.println(int[][][].class.getName());
		System.out.println(short[].class.getName());
		System.out.println(double[].class.getName());
		System.out.println();
	}
	
	/**
	 * 获取 Class 对象的几种方式
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testGetClass() throws ClassNotFoundException{
		Integer aInteger = 1;
		System.out.println(Integer.class.getName());
		System.out.println(aInteger.getClass().getName());
		System.out.println(Class.forName("java.lang.Integer"));
	}
}
