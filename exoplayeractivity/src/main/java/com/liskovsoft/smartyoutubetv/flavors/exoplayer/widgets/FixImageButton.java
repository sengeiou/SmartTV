package com.liskovsoft.smartyoutubetv.flavors.exoplayer.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

import com.liskovsoft.sharedutils.helpers.Helpers;
import com.liskovsoft.sharedutils.mylogger.Log;
import com.liskovsoft.smartyoutubetv.prefs.SmartPreferences;

public class FixImageButton extends ImageButton {
    private static final String TAG = FixImageButton.class.getSimpleName();
    private boolean mFirstRun = true;

    public FixImageButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public FixImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FixImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixImageButton(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d(TAG, "FixImageButton->click: " + event);

        // OK pause fix for the first video
        if (mFirstRun) {
            boolean okPause = SmartPreferences.instance(getContext()).getEnableOKPause();
            if (okPause && event.getAction() == KeyEvent.ACTION_UP) {
                KeyEvent downEvent = Helpers.newEvent(event, KeyEvent.ACTION_DOWN);
                super.dispatchKeyEvent(downEvent);
            }
        }

        mFirstRun = false;

        return super.dispatchKeyEvent(event);
    }
}
