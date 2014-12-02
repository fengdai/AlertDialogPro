package com.alertdialogpro.holo.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

/**
 * This class is used to avoid the appcompat-v7:r21 to replace CheckedTextView with TintCheckedTextView.
 */
public class NoTintCheckedTextView extends CheckedTextView {
    public NoTintCheckedTextView(Context context) {
        super(context);
    }

    public NoTintCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NoTintCheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
