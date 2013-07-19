package com.mrock.db4o_util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class DemoActivity extends Activity {

	private Random rand = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// delete all user data
		Db4oUtil.delAll(User.class);

		// add new user
		User u = new User();
		u.name = "testname";
		u.passwd = "testpw";

		Area a = new Area();
		a.name = "test";
		Db4oUtil.save(u, a);

		// query user
		Query q = Db4oUtil.getQuery(User.class);
		q.descend("name").equals("testname");
		ObjectSet<User> users = q.execute();
		if (!users.isEmpty()) {
			u = users.get(0);
			System.err.println("after query: " + u);
		}

		// update user data
		if (u != null) {
			u.passwd = "testxxx";
			u.infos = new ArrayList<Map>();
			Map m = new HashMap();
			m.put(rand.nextInt(10), rand.nextInt(10));
			u.infos.add(m);
			m = new HashMap();
			m.put(rand.nextInt(10), rand.nextInt(10));
			u.infos.add(m);
			Db4oUtil.save(u);
		}
		q.descend("name").equals("testname");
		users = q.execute();
		System.err.println("after update: " + users);

		// clear user data
		if (u != null) {
			Db4oUtil.del(u);
		}
		q.descend("name").equals("testname");
		users = q.execute();
		System.err.println("after delete: " + users);

		// test key/value put/get function
		Db4oUtil.put("test", u);
		System.err.println("after put: " + Db4oUtil.get("test"));
		Db4oUtil.put("test", "string");
		System.err.println("after put: " + Db4oUtil.get("test"));
		Db4oUtil.put("test", null);
		System.err.println("after put: " + Db4oUtil.get("test"));

		// test key/value put/get async function
		Db4oUtil.putAsync("test", "async");
		System.err.println("after put async: " + Db4oUtil.get("test"));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Db4oUtil.getAsync("test", new Db4oUtil.Callback() {
			@Override
			public void done(Object obj) {
				System.err.println("after get async: " + obj);
			}
		});

		finish();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
