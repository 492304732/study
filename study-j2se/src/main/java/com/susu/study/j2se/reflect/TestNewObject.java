package com.susu.study.j2se.reflect;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author Su
 * 
 * java 创建对象的几种方法
 * 1、使用New关键字
 * 2、使用Class类的newInstance方法
 * 3、使用Constructor类的newInstance方法
 * 4、使用Clone方法
 * 5、使用反序列化。
 *
 */
public class TestNewObject {

	public static void main(String[] args) {
		testClassInstance();
		testConstructorInstance();
		testClone();
		testDeserialization();
	}

	/**
	 * new 对象 clone 方法创建对象
	 */
	public static void testClone() {
		System.out.println("testClone:");
		Book book1 = new Book("Redis", Arrays.asList("Eric", "John"),
				"ABBBB-QQ677868686-HSDKHFKHKH-2324234", 59.00f);
		System.out.println("book1: " + book1);
		try {
			Book book2 = book1.clone();
			System.out.println("book1.clone: " + book2);
			System.out.println();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 3. 使用Class.newInstance();
	 */
	public static void testClassInstance() {
		try {
			System.out.println("testClassInstance");
			Book book3 = (Book) Class.forName("com.susu.study.j2se.reflect.Book")
					.newInstance();
			System.out.println("Class.forName(\"com.susu.study.j2se.reflect.Book\")" +
					".newInstance(): " + book3);

			book3 = Book.class.newInstance();
			System.out.println("Book.class.newInstance(): " + book3);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 4. 使用Constructor.newInstance();
	 */
	public static void testConstructorInstance() {
		try {
			System.out.println("testConstructorInstance:");
			// 选择第一个构造器创建Book
			Book book4 = (Book) Book.class.getConstructors()[0].newInstance();
			// Book [name=null, authors=null, isbn=null, price=0.0]
			System.out.println("Book.class.getConstructors()[0]: " + book4);

			/**
			 * 调用指定构造函数创建对象
			 */
			book4 = Book.class.getConstructor(String.class, List.class, String.class, float.class)
					.newInstance("New Instance Example", Arrays.asList("Wang", "Eric"), "abc1111111-def-33333", 60.00f);
			// Book [name=New Instance Example, authors=[Wang, Eric],
			// isbn=abc1111111-def-33333, price=60.0]
			System.out.println("Book.class.getConstructors()[1]: " + book4);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 5. 使用反序列化
	 */
	public static void testDeserialization() {
		System.out.println("testDeserialization:");
		Book book1 = new Book("Redis", Arrays.asList("Eric", "John"),
				"ABBBB-QQ677868686-HSDKHFKHKH-2324234", 59.00f);
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("book.dat"));
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("book.dat"))) {
			outputStream.writeObject(book1);
			Book book5 = (Book) inputStream.readObject();
			System.out.println(book5);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
