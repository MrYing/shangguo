package com.shangguo.test;

import com.shangguo.weixin.util.Bt;

/**
 *
 * @author JabnMai
 * @date 2015年7月14日
 * 
 */
public class SetName {
	 /* Lock lock = new ReentrantLock();// 锁
	  private static String access_token;
	    private static int expiresIn;
	    public static String getAccess_token() {
			return access_token;
		}
		public static void setAccess_token(String access_token) {
			lock.lock();// 取得锁
			SetName.access_token = access_token;
		}
		public static int getExpiresIn() {
			return expiresIn;
		}
		public static void setExpiresIn(int expiresIn) {
			SetName.expiresIn = expiresIn;
		}
		*/
	  public void setName(String name){
		  Bt bt = new Bt();
		  bt.setName(name);;
	  }
	 
}
