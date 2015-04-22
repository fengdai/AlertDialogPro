package com.alertdialogpro.material;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.internal.text.AllCapsTransformationMethod;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Introduce "textAllCaps" to Button for platform below API-14.
 */
public class ButtonCompat extends Button {
    public ButtonCompat(Context context) {
        this(context, null);
    }

    public ButtonCompat(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.buttonStyle);
    }

    public ButtonCompat(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray style = context
                .obtainStyledAttributes(attrs, R.styleable.AppCompatTextView, defStyle, 0);
        boolean allCaps = style.getBoolean(R.styleable.AppCompatTextView_textAllCaps, false);
        style.recycle();

        if (allCaps) {
            setTransformationMethod(new AllCapsTransformationMethod(context));
        }
    }
}
