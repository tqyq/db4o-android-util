db4o-android-util
=================

## Introduction

a util class which simplify the usage of db4o in android

## Quick Start

Init in android `Application`:

````java
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
````

Delete data

````java
		// delete all user data
		Db4oUtil.delAll(User.class);
````

Add data
````java
		// add new user
		User u = new User();
		u.name = "testname";
		u.passwd = "testpw";

		Area a = new Area();
		a.name = "test";
		Db4oUtil.save(u, a);
````

Query data
````java
		// query user
		Query q = Db4oUtil.getQuery(User.class);
		q.descend("name").equals("testname");
		ObjectSet<User> users = q.execute();
		if (!users.isEmpty()) {
			u = users.get(0);
			System.err.println("after query: " + u);
		}
````		

Update data
````java
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
````		

Delete one

````java
		if (u != null) {
			Db4oUtil.del(u);
		}
		q.descend("name").equals("testname");
		users = q.execute();
		System.err.println("after delete: " + users);
````

Use as key/value DB
````java
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
````		

