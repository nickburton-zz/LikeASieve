/**
 * Copyright (c) 2011 Nick Burton.
 */

package com.likeasieve.test;

import com.jayway.android.robotium.solo.Solo;
import com.likeasieve.ImageActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.util.Log;

/**
 * @date   Apr 19, 2011
 * @author Nick Burton (thatsburto.com)
 */
public class MockImageActivity extends
        ActivityInstrumentationTestCase2<ImageActivity> {

    private static final String TAG = "MockImageActivity";
    private Solo solo;
    private static final int NUM_ROTATIONS = 10;
    private static final int NUM_LIST_ITEMS = 4;
    
    public MockImageActivity() {
        super("com.likeasieve", ImageActivity.class);
    }
    
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Smoke
    public void testOrientation() throws Exception {
        int position = 0;
        for(int i = 0; i < NUM_ROTATIONS; i++){
            Log.d(TAG, "Rotation Test: "+i);
            changeOrientationThenTest();
            solo.scrollDownList(position);
            position += 1;
            if(position == NUM_LIST_ITEMS){
                position = 0;
            }
        }
    }

    private void changeOrientationThenTest() throws Exception {
        solo.setActivityOrientation(Solo.LANDSCAPE);
        Thread.sleep(3000);
        solo.setActivityOrientation(Solo.PORTRAIT);
        Thread.sleep(3000);
    }
}
