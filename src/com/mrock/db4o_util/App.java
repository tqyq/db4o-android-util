package com.mrock.db4o_util;

import android.app.Application;

public class App extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Db4oUtil.init(getApplicationContext());
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		Db4oUtil.close();
	}

}
