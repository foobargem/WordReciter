package org.inkwang.two;

import java.util.ArrayList;

import org.inkwang.two.helper.DBAdapter;
import org.inkwang.two.model.Word;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class WordListActivity extends Activity {

	private String tagName;
	private String tagKey;
	
	private TextView tagNameTextView;
	private ListView wordListView;
	
	private ArrayAdapter<Word> wordArrayAdapter;
	private ArrayList<Word> wordList;
	
	private DBAdapter dba;
	
	private final int WORD_DETAIL_DIALOG = 1;
	private Word selectedWord;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.word_list);

		tagNameTextView = (TextView)findViewById(R.id.word_list_tag_name);
		wordListView = (ListView)findViewById(R.id.word_list_view);
		
		dba = new DBAdapter(this);
		dba.open();
		
		getIntentExtras();
		
		setTagNameToView();
		generateWordArrayAdapter();
		
		dba.close();
		
		
		wordListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView _av, View _v, int index, long arg3) {
				selectedWord = wordList.get(index);
				showDialog(WORD_DETAIL_DIALOG);
			}
			
		});
		
    }
	

   @Override
    protected Dialog onCreateDialog (int id) {
    	switch (id) {
    	case (WORD_DETAIL_DIALOG):
    		LayoutInflater li = LayoutInflater.from(this);
    		View wordDetailDialogView = li.inflate(R.layout.word_detail_dialog, null);
    		
    		AlertDialog.Builder wordDetailDialog = new AlertDialog.Builder(this);
    		//wordDetailDialog.setTitle("Detail");
    		wordDetailDialog.setView(wordDetailDialogView);
    		
    		return wordDetailDialog.create();
    	}
    	return null;
    }
	    
	    
    @Override
    protected void onPrepareDialog (int id, Dialog dialog) {
    	switch (id) {
    	case(WORD_DETAIL_DIALOG):
    		AlertDialog wordDetailDialog = (AlertDialog)dialog;
    		wordDetailDialog.setTitle(selectedWord.getSubject());
    		
    		TextView subjectTextView = (TextView)wordDetailDialog.findViewById(R.id.word_detail_dialog_subject);
    		TextView paragraphTextView = (TextView)wordDetailDialog.findViewById(R.id.word_detail_dialog_paragraph);
    		TextView wordsTextView = (TextView)wordDetailDialog.findViewById(R.id.word_detail_dialog_words);
    		TextView tagTextView = (TextView)wordDetailDialog.findViewById(R.id.word_detail_dialog_tag);
    		
    		
    		subjectTextView.setText(selectedWord.getSubject());
    		paragraphTextView.setText(selectedWord.getParagraph());
    		wordsTextView.setText(selectedWord.getWords());
    		tagTextView.setText(selectedWord.getTag());
    		
    		break;
    	}
    }

	
	
	private void getIntentExtras() {
		tagName = this.getIntent().getStringExtra("org.inkwang.two.tagName");
		tagKey = this.getIntent().getStringExtra("org.inkwang.two.tagKey");
	}
	
	private void setTagNameToView() {
		tagNameTextView.setText(tagName);
	}
	
	private void generateWordList() {

		Log.i("tagKey", tagKey);
		wordList = dba.wordListByKey(tagKey);

	}
	
	private void generateWordArrayAdapter() {
		
		generateWordList();

		int layoutId = android.R.layout.simple_list_item_1;
		
		this.wordArrayAdapter = new ArrayAdapter<Word>(this, layoutId, wordList);
		this.wordListView.setAdapter(wordArrayAdapter);
		
	}

}
