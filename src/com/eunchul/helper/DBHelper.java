package com.eunchul.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private String dbPath;
	private String dbName;
	private Context context;
	private SQLiteDatabase db;
	
	public DBHelper(Context context, String path, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = context;
		this.dbPath = path;
		this.dbName = name;
		createDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//db.execSQL(sql)
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// do something.
	}
	
	
	private void createDatabase() {
		
		if (!dbExists()) {
			try {
				copyDatabase();
			} catch (IOException e) {
				Log.e("IOException", e.toString());
			}
		}
		
	}

	public void openDataBase() throws SQLException {
		String path = this.dbPath + this.dbName;
		db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
	}
	
	
	private boolean dbExists() {
		
		String path = this.dbPath + this.dbName;
		
		File dbFile = new File(path);
		return dbFile.exists();
		
	}
	
	private void copyDatabase() throws IOException {
		InputStream is = context.getAssets().open(dbName);
		
		String outFilename = dbPath + dbName;
		
		File dir = new File(dbPath);
		if (!dir.exists()) {
			if (dir.mkdir()) {
				Log.i("mkdir", "successed!!");
			} else {
				Log.e("mkdir", "Faield")	;
			}
		}
		
		OutputStream os = new FileOutputStream(outFilename);
		
		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		
		os.flush();
		os.close();
		is.close();
	}

}
