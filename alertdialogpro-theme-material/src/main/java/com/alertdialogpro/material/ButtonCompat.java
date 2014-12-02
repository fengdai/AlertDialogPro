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
        this(context, attrs, 0);
    }

    public ButtonCompat(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        boolean allCaps = false;

        TypedArray style = context
                .obtainStyledAttributes(attrs, R.styleable.ButtonCompat, defStyle, 0);
        allCaps = style.getBoolean(R.styleable.ButtonCompat_textAllCaps, false);
        style.recycle();

        // Framework impl also checks TextAppearance for textAllCaps. This isn't needed for our
        // purposes so has been omitted.

        if (allCaps) {
            setTransformationMethod(new AllCapsTransformationMethod(context));
        }
    }
}
