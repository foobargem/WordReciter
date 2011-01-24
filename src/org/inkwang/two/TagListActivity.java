package org.inkwang.two;

import java.util.ArrayList;

import org.inkwang.two.helper.DBAdapter;
import org.inkwang.two.model.Tag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TagListActivity extends Activity {

	private ArrayAdapter<Tag> tagArrayAdapter;
	private ArrayList<Tag> tags;	
	
	private ListView tagListView;
	
	private Intent intent;
	
	private static final int WORD_LIST_ACTIVITY = 1;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		tagListView = (ListView)findViewById(R.id.tag_list);
		
		generateTagList();
		
		intent = new Intent(this, WordListActivity.class);
		
		tagListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView _av, View _v, int _index,	long arg3) {
				startWordListActivity(intent, _index);
			}
			
		});


    }
	
	private void startWordListActivity(Intent intent, int index) {
		
		String tagName = "";
		String tagKey = "";
		
		switch (index) {
		case 0:
			tagName = this.getString(R.string.word_tag_a);
			tagKey = "A";
			break;
		case 1:
			tagName = this.getString(R.string.word_tag_b);
			tagKey = "B";
			break;
		case 2:
			tagName = this.getString(R.string.word_tag_c);
			tagKey = "C";
			break;
		case 3:
			tagName = this.getString(R.string.word_tag_d);
			tagKey = "D";
			break;
		case 4:
			tagName = this.getString(R.string.word_tag_e);
			tagKey = "E";
			break;
		}
		
		intent.putExtra("org.inkwang.two.tagName", tagName);
		intent.putExtra("org.inkwang.two.tagKey", tagKey);
		
		this.startActivity(intent);
	}
	
	private void initTags() {
		tags = new ArrayList<Tag>();

		tags.add(new Tag(this.getString(R.string.word_tag_a)));
		tags.add(new Tag(this.getString(R.string.word_tag_b)));
		tags.add(new Tag(this.getString(R.string.word_tag_c)));
		tags.add(new Tag(this.getString(R.string.word_tag_d)));
		tags.add(new Tag(this.getString(R.string.word_tag_e)));
	}
	
	private void generateTagList() {
		
		initTags();
		
    	int layoutId = android.R.layout.simple_list_item_1;
    	
    	this.tagArrayAdapter = new ArrayAdapter<Tag>(this, layoutId, tags);
    	this.tagListView.setAdapter(tagArrayAdapter);
		
	}
	
	
}