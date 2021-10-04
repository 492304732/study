package com.susu.study.j2se.basic;

/**
 * final
 * 修饰基础数据成员: 表示改成员为常量。
 * 修饰类或对象的引用的: 表示引用不能被改变，但是对象内数据可以被改变。
 * 修饰方法: 表示该方法不能被子类重写
 * 修饰类: 表示该类不能被继承，如 Integer
 *
 * 此外，当一个方法被修饰为final方法时，意味着编译器可能将该方法用内联(inline)方式载入，
 * 所谓内联方式，是指编译器不用像平常调用函数那样的方式来调用方法，而是直接将方法内的代码通过一定的修改后copy到原代码中
 * 这样可以让代码执行的更快（因为省略了调用函数的开销），
 * 比如在int[] arr = new int[3]调用arr.length()等。
 * 另一方面，私有方法也被编译器隐式修饰为final，这意味着 private final void f()和private void f()并无区别。
 */
public class TestFinal {
	public void test1() {
		StringBuffer sbuffer = new StringBuffer("apple ");
		test(sbuffer);
		System.out.println(sbuffer.toString());
	}

	private void test(final StringBuffer a) {
		// 报错，不能改变引用： a=new StringBuffer("immutable"); 
		// 可以对值进行改变
		a.append(" broken!");
		
		//报错，这句话相当于 i = new Integer(3); 自动装箱
		//		final Integer i = 2;
		//		i=3;
				
		//报错，相当于用stringBuffer返回一个新的string对象：builder.append("abc");  builder.append("def");
		//		final String string ="abc";
		//		string = string + "def";
				
		//java 不支持运算符重载
		//例外：String中的+号，编译时会被转换为StringBuffer的append()方法来操作。
	}
	
}
