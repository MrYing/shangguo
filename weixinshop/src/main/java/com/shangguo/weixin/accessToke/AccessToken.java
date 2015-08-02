package com.shangguo.weixin.accessToke;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccessToken {
    private static String access_token;
    private static int expiresIn;
    Lock lock = new ReentrantLock();// 锁
    
    public  String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		lock.lock();// 取得锁
		AccessToken.access_token = access_token;
		lock.unlock();// 释放锁
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		AccessToken.expiresIn = expiresIn;
	}
	
}
