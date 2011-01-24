package org.inkwang.two.helper;

import java.util.ArrayList;

import org.inkwang.two.model.Word;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.eunchul.helper.DBHelper;

public class DBAdapter {

	private static final String DB_PATH = "/data/data/org.inkwang.two/databases/";
	private static final String DB_NAME = "word_reciter.sqlite";
	
	private static final String WORDS_TABLE = "words";
	private static final int DATABASE_VERSION = 1;

	private final int ID_COLUNN = 0;
	private final int SUBJECT_COLUMN = 1;
	private final int PARAGRAPH_COLUMN = 2;
	private final int WORDS_COLUMN = 3;
	private final int TAG_COLUMN = 4;
	
	
	private SQLiteDatabase db;
	
	private final Context context;
	private DBHelper dbHelper;
	
	public DBAdapter(Context _context) {
		context = _context;
		dbHelper = new DBHelper(context, DB_PATH, DB_NAME, null, 1);

		try {
			dbHelper.openDataBase();
		} catch (SQLException e) {
			Log.e("SQLException", e.toString());
		}
		
	}
	
	public DBAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		db.close();
	}
	
	public ArrayList<Word> wordListByKey(String key) {
		
		ArrayList<Word> wordList = new ArrayList<Word>();
		
		String[] columns = { "id", "subject", "paragraph", "words", "tag" };
		String cond = "tag LIKE '" + key + "%'";
		
		Cursor cursor = db.query(WORDS_TABLE, columns, cond, null, null, null, "id asc");
		Log.i("row count", Integer.toString(cursor.getCount()));
		
		if (cursor.moveToFirst()) {
			while (!cursor.isAfterLast()) {
				
				wordList.add(new Word(
						cursor.getInt(this.ID_COLUNN),
						cursor.getString(this.SUBJECT_COLUMN),
						cursor.getString(this.PARAGRAPH_COLUMN),
						cursor.getString(this.WORDS_COLUMN),
						cursor.getString(this.TAG_COLUMN)
				));
				
				cursor.moveToNext();
			}
		}
		
		return wordList;
	}
	
}
