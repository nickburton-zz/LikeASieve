/**
 * Copyright (c) 2011 Nick Burton.
 */

package com.likeasieve;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * @date   Apr 19, 2011
 * @author Nick Burton (thatsburto.com)
 */
public class ImageActivity extends ListActivity {
    
    private ListView mListView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mListView = getListView();
        mListView.setAdapter(new ImageAdapter(this));
    }
}