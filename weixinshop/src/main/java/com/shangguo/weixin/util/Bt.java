package com.shangguo.weixin.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bt {
	Lock lock = new ReentrantLock();// 锁
	private static String name;

	public String getName() {
		return name;
	}

	public  void setName(String name) {
		lock.lock();// 取得锁
		Bt.name = name;
		try {  
            Thread.sleep(5000);  
        } catch (InterruptedException e) {  
        }
		lock.unlock();// 释放锁
	}

	
}
