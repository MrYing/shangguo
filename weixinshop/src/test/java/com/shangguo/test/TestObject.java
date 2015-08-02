package com.shangguo.test;

public class TestObject {
	public static void main(String[] args) {
		SetName sn = new SetName();
		sn.setName("test1");
		sn.setName("test3");
		sn.setName("test4");
		GetName gn = new GetName();
		SetName sn1 = new SetName();
		gn.getbtName();
		sn1.setName("test2");
		gn.getbtName();
		}
}
