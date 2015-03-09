package com.niceapp.lib.tagview;

import java.util.ArrayList;
import java.util.List;
import com.niceapp.lib.tagview.widget.Tag;
import com.niceapp.lib.tagview.widget.TagListView;
import com.niceapp.lib.tagview.widget.TagListView.OnTagCheckedChangedListener;
import com.niceapp.lib.tagview.widget.TagListView.OnTagClickListener;
import com.niceapp.lib.tagview.widget.TagView;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	private TagListView mTagListView;
	private final List<Tag> mTags = new ArrayList<Tag>();
	private final String[] titles = { "安全必备", "音乐", "父母", "上班", 
			"360手机卫士", "QQ","输入法", "微信", "最美应用", "AndevUI", "蘑菇街" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_tag_activity);

		mTagListView = (TagListView) findViewById(R.id.tagview);
		setUpData();
		mTagListView.setTags(mTags);
		mTagListView.setOnTagCheckedChangedListener(new OnTagCheckedChangedListener() {
			
			@Override
			public void onTagCheckedChanged(TagView tagView, Tag tag) {
				// TODO Auto-generated method stub
				System.out.println("Tag change:"+tag.getTitle());
			}
		});
		mTagListView.setOnTagClickListener(new OnTagClickListener() {
			
			@Override
			public void onTagClick(TagView tagView, Tag tag) {
				System.out.println("Tag click:"+tag.getTitle());
				// TODO Auto-generated method stub
				mTagListView.removeTag(tag);
				mTagListView.invalidate();
				
			}
		});
	}
	
	private void setUpData() {
		for (int i = 0; i < 10; i++) {
			Tag tag = new Tag();
			tag.setId(i);
			tag.setChecked(true);
			tag.setTitle(titles[i]);
			mTags.add(tag);
		}
	}
}
