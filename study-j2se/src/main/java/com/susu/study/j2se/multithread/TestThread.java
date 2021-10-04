package com.susu.study.j2se.multithread;

public class TestThread {
	/**
	 * 测试 wait、notify 和  sleep
	 */
	public void testThread() throws InterruptedException {
		new Thread(new Thread1(), "Thread1").start();
			Thread.sleep(100);
		new Thread(new Thread2(), "Thread2").start();
			Thread.sleep(10000);

	}

	//只有在 synchronized 语句块中，才能够使用 wait、notify、notifyAll 的方法
	private class Thread1 implements Runnable {
		@Override
		public void run() {
			//虚拟机中 Test1.class 是同一个对象
			synchronized (TestThread.class) {
				System.out.println("enter thread1...");
				System.out.println("thread1 is waiting");
				// 释放锁有两种方式，第一种方式是程序自然离开监视器的范围，也就是离开了synchronized关键字管辖的代码范围，
				// 另一种方式就是在synchronized关键字管辖的代码内部调用监视器对象的wait方法。这里，使用wait方法释放锁。
				try {
					TestThread.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread1 is going on...");
				System.out.println("thread1 is being over!");
			}

		}
	}

	private class Thread2 implements Runnable {
		@Override
		public void run() {
			System.out.println("enter thread2...");
			System.out.println("thread2 is sleeping");
			synchronized (TestThread.class) {
				//由于notify方法并不释放锁，即使thread2调用下面的sleep方法休息了10毫秒，但thread1仍然不会执行，
				//因为thread2没有释放锁，所以Thread1无法得不到锁。
				TestThread.class.notify();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("thread2 is going on...");
			System.out.println("thread2 is being over!");
		}
	}
}
