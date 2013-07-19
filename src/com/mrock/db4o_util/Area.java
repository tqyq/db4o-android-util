package com.mrock.db4o_util;

import com.google.gson.Gson;

public class Area {

	public String name;
	public int code;

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
