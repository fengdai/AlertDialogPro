package com.alertdialogpro.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Used by dialogs to change the font size and number of lines to try to fit
 * the text to the available space.
 */
public class DialogTitle extends TextView {

    private static final int[] TEXT_APPEARANCE_ATTRS = new int[]{android.R.attr.textSize};

    public DialogTitle(Context context,
                       AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public DialogTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DialogTitle(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final Layout layout = getLayout();
        if (layout != null) {
            final int lineCount = layout.getLineCount();
            if (lineCount > 0) {
                final int ellipsisCount = layout.getEllipsisCount(lineCount - 1);
                if (ellipsisCount > 0) {
                    setSingleLine(false);
                    setMaxLines(2);

                    final TypedArray a = getContext().obtainStyledAttributes(null,
                            TEXT_APPEARANCE_ATTRS,
                            android.R.attr.textAppearanceMedium,
                            android.R.style.TextAppearance_Medium);
                    final int textSize = a.getDimensionPixelSize(0, 0);
                    if (textSize != 0) {
                        // textSize is already expressed in pixels
                        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                    }
                    a.recycle();

                    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                }
            }
        }
    }
}