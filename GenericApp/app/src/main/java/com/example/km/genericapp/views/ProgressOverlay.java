package com.example.km.genericapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.km.genericapp.R;

/**
 * Progress overlay.
 */
public class ProgressOverlay extends RelativeLayout {

    public ProgressOverlay(Context context) {
        this(context, null);
    }

    public ProgressOverlay(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressOverlay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.progress_overlay, this);
        hide();
        setClickable(true);
    }

    public void hide() {
        setVisibility(GONE);
    }

    public void show() {
        setVisibility(VISIBLE);
    }
}
