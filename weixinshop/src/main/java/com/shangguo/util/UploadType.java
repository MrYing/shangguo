package com.shangguo.util;

import java.util.List;

import com.shangguo.util.MyUploadUtil.State;

public class UploadType {
	private State state;
	private List list;

	public UploadType(State state, List list) {
		this.state = state;
		this.list = list;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
