package com.susu.study.j2se.reflect;

import java.io.Serializable;
import java.util.List;

/**
 * 调用 clone 时
 * 如果没有重写clone方法，则会报错：方法不可见（clone 是 Object中的protected方法）
 * 如果重写了clone方法没有实现接口，则会抛出 java.lang.CloneNotSupportedException:
 */
public class Book implements Serializable, Cloneable {
    private static final long serialVersionUID = -6212470156629515269L;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private List<String> authors;

    /**
     * ISBN
     */
    private String isbn;

    /**
     * 价格
     */
    private float price;

    public Book() {
    }

    /**
     * @param name
     * @param authors
     * @param isbn
     * @param price
     */
    public Book(String name, List<String> authors, String isbn, float price) {
        this.name = name;
        this.authors = authors;
        this.isbn = isbn;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book [name=" + name + ", authors=" + authors + ", isbn=" + isbn + ", price=" + price + "]";
    }

    @Override
    public Book clone() throws CloneNotSupportedException {
        return (Book) super.clone();
    }
}