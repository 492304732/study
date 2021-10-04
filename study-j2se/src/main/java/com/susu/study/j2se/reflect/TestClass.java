package com.susu.study.j2se.reflect;

public class TestClass {
	public static void main(String[] args) throws ClassNotFoundException {
		testBaseType();
		testGetClass();
	}
	
	/**
	 * @throws ClassNotFoundException
	 * 基本类型可以用 int.class 唯一方式获取其 Class 对象
	 * 基本类型数组也有其 Class 对象,分不同基本类型和维度
	 */
	public static void testBaseType() throws ClassNotFoundException{
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
	public static void testGetClass() throws ClassNotFoundException{
		Integer aInteger = 1;
		System.out.println(Integer.class.getName());
		System.out.println(aInteger.getClass().getName());
		System.out.println(Class.forName("java.lang.Integer"));
	}
}
