package com.mrock.db4o_util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class User {

	public String name;
	public String passwd;
	public Date birth;
	public int gender;
	public float credit;
	public List<Map> infos;

	public transient String birthStr;

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
